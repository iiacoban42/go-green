package server.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entity.SolPanelAction;
import database.manager.SolPanelActionManager;
import database.manager.UserManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import server.entity.LoginCredentials;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/users/login");
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {

        try {
            System.out.println("Attempting authentication!");
            LoginCredentials credentials = new ObjectMapper()
                .readValue(request.getInputStream(),
                    LoginCredentials.class);

            return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(),
                    credentials.getPassword(),
                    new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult) throws IOException, ServletException {

        // Do solar panel stuff
        SolPanelAction solPanelAction =
                SolPanelActionManager.getActiveSpByUser(authResult.getName());
        if (solPanelAction != null) {
            SolPanelActionManager.cashInSp(solPanelAction.getId());
        }

        System.out.println("Successful authentication Username: " + authResult.getName());
        String token = CreateJwt.createJwt(authResult.getName());
        UserManager.setToken(authResult.getName(), token);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
