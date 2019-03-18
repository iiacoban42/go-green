package server.controller;

<<<<<<< HEAD
import database.manager.UserManager;
=======
import database.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
>>>>>>> 28709164224bdc78e08765cee484b62e72ec0d81
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.entity.LoginCredentials;
import server.entity.RegisterCredentials;

@RestController
@RequestMapping("/authentication")
public class Authentication {

    /**
     * Check if login credentials are valid.
     * @param credentials to check if valid
     * @return true or false
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginCredentials credentials) {
        ResponseEntity response = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        database.manager.User user = database.manager.UserManager.getUser(credentials.getUsername());
        if ( user != null) {
            if (user.getHashPassword().equals(credentials.getPassword())) {
                response = new ResponseEntity(HttpStatus.OK);
                String createjwt = CreateJwt.createJwt(credentials.getUsername());
                user.setToken(createjwt);
            }
        }

        return response;
    }

    /**
     * Takes credentials and returns true or false I guess.
     * @param credentials to check if valid
     * @return boolean true or false
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterCredentials credentials) {
        ResponseEntity response = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        if (database.manager.UserManager
                .getUser(credentials.getUsername()) == null) {
            UserManager.addUser(credentials.getUsername(),
                    credentials.getPassword(), credentials.getEmail());
            response = new ResponseEntity(HttpStatus.OK);
            String createjwt = CreateJwt.createJwt(credentials.getUsername());
            UserManager.getUser(credentials.getUsername()).setToken(createjwt);
        }

        return response;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
    }
}
