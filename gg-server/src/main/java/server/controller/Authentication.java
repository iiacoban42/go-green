package server.controller;

import database.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.entity.LoginCredentials;
import server.entity.RegisterCredentials;
import server.security.CreateJwt;

@RestController
@RequestMapping("/authentication")
public class Authentication {
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public Authentication(BCryptPasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }

    /**
     * Check if login credentials are valid.
     *
     * @param credentials to check if valid
     * @return true or false
     */
    @PostMapping("/test/login")
    public ResponseEntity login(@RequestBody LoginCredentials credentials) {
        ResponseEntity response = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        database.entity.User user = database.manager.UserManager.getUser(credentials.getUsername());
        if ( user != null) {
            if (user.getHashPassword().equals(credentials.getPassword())) {
                response = new ResponseEntity(HttpStatus.OK);
                String createJwt = CreateJwt.createJwt(credentials.getUsername());
                UserManager.getUser(credentials.getUsername()).setToken(createJwt);
            }
        }

        return response;
    }

    /**
     * Takes credentials and returns true or false I guess.
     *
     * @param credentials to check if valid
     * @return boolean true or false
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterCredentials credentials) {
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        ResponseEntity response = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        if (database.manager.UserManager
                .getUser(credentials.getUsername()) == null) {
            UserManager.addUser(credentials.getUsername(),
                    credentials.getPassword(), credentials.getEmail());
            response = new ResponseEntity(HttpStatus.OK);
        }

        return response;
    }
}
