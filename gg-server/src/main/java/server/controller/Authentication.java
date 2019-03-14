package server.controller;

import database.manager.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.entity.LoginCredentials;
import server.entity.RegisterCredentials;

import java.util.List;

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

        if (database.manager.getUser(credentials.getUsername()) != null) {
            if (database.manager.getUser(credentials.getUsername()).getHashPassword().equals(credentials.getPassword())) {
                response = new ResponseEntity(HttpStatus.OK);
                CreateJWT.createJWT(credentials.getUsername());
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
<<<<<<< HEAD
    public ResponseEntity register(@RequestBody RegisterCredentials credentials) {
=======
        public ResponseEntity register(@RequestBody RegisterCredentials credentials) {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();


        List<User> users = session.createQuery("from User").list();
        RegisterCredentials register = new RegisterCredentials("mail", "user", "pass");
>>>>>>> c443bf291d154821c75d37c0fc2c99722719c0c7
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        if (credentials.getUsername().equals("admin")) {
            response = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return response;
    }
}
