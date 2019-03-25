package server.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/authentication/login", "/authentication/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/authentication/login");
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }


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
