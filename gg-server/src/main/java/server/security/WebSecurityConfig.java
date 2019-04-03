package server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //private UserDetailsServiceImpl userDetailsService;
    //private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter(new AuthenticationFilter(authenticationManager()))
            .addFilter(new AuthorizationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder());
    }

    /*
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        long now = System.currentTimeMillis();

        String jwtToken = database.manager.UserManager.getUser().getToken();
        long expDate = CreateJwt.getExpirationDate(jwtToken);

        if (now > expDate) {

    }
        auth.inMemoryAuthentication();

    protected Boolean hasRole(String username) {
        long now = System.currentTimeMillis();

        String jwtToken = database.manager.UserManager.getUser(username).getToken();
        long expDate = CreateJwt.getExpirationDate(jwtToken);

        if (now > expDate) {
            return true;
        }

        return false;
    }
    */
}
