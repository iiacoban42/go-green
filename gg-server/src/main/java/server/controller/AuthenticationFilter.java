package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import java.util.logging.Level;


public class AuthenticationFilter implements ContainerRequestFilter {

    @Autowired
    UsersService usersService;

    @Autowired
    AuthenticationUtil authenticationUtil;

    @Override
    public static void filter(ContainerRequestContext requestContext) {
        String tokenHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer")) {
            throw new NotAuthorizedException("header must be provided");
        }

        String jwtToken = tokenHeader.substring("Bearer".length()).trim();

        try {
            CreateJwt.validateToken(jwtToken);

        } catch (AuthenticationServiceException ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
            requestContext.abortWith(
            Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
