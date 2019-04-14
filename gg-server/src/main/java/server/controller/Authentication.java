package server.controller;

import database.entity.User;
import database.manager.ActionManager;
import database.manager.BadgeManager;
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

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class Authentication {
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public Authentication(BCryptPasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }

    /**
     * Changes the users password to the password in LoginCredentials.password.
     *
     * @param credentials LoginCredentials
     * @param principal   filled behind the scenes
     */
    @PostMapping("/changePassword")
    public void changePassword(@RequestBody LoginCredentials credentials, Principal principal) {
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));

        UserManager.changePassword(principal.getName(), credentials.getPassword());
    }

    /**
     * Deletes the user account from which it is called.
     *
     * @param principal user details
     * @return Ok or NOT_FOUND
     */
    @PostMapping("/deleteUser")
    public ResponseEntity deleteAccount(Principal principal) {
        // Have to delete all actions in case there are any.
        List actions = ActionManager.listActionsUser(principal.getName());

        for (Object actionObj : actions) {
            database.entity.Action action = (database.entity.Action) actionObj;
            ActionManager.deleteAction(action.getId());
        }

        // Have to delete all badges in case there are any.
        List badges = BadgeManager.listBadgesUser(principal.getName());

        for (Object badgeObj : badges) {
            database.entity.Badge badge = (database.entity.Badge) badgeObj;
            BadgeManager.deleteBadge(badge.getId());
        }

        UserManager.deleteUser(principal.getName());

        return new ResponseEntity(HttpStatus.OK);
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

        User user = database.manager.UserManager.getUser(credentials.getUsername());
        if (user == null) {
            UserManager.addUser(credentials.getUsername(),
                    credentials.getPassword(),
                    credentials.getEmail());

            response = new ResponseEntity(HttpStatus.OK);
        }

        return response;
    }
}
