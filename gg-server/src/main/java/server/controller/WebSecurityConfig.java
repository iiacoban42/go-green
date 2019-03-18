package server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${security.enable-csrf}")
//    private boolean csrfEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

//        if(!csrfEnabled) {
            http.csrf().disable();
//        }

//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll();
    }
}
