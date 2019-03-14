package server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity register(@RequestBody RegisterCredentials credentials) {
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        if (credentials.getUsername().equals("admin")) {
            response = new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return response;
    }
}
