package server.controller;

import database.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

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

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(inMemoryUserDetailsManager());
//    }



//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        long now = System.currentTimeMillis();
//
//        String jwtToken = database.manager.UserManager.getUser().getToken();
//        long expDate = CreateJwt.getExpirationDate(jwtToken);
//
//        if (now > expDate) {
//
//    }
//        auth.inMemoryAuthentication();

//    protected Boolean hasRole(String username) {
//        long now = System.currentTimeMillis();
//
//        String jwtToken = database.manager.UserManager.getUser(username).getToken();
//        long expDate = CreateJwt.getExpirationDate(jwtToken);
//
//        if (now > expDate) {
//            return true;
//        }
//
//        return false;
//    }
}
