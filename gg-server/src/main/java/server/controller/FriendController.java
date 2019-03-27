package server.controller;

import database.entity.User;

import database.manager.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import server.entity.Friend;
import server.entity.FriendScore;

@Controller
@RequestMapping("/friends")
public class FriendController {

    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Add friend to user.
     *
     * @return ResponseEntity
     */
    @GetMapping("/friend")
    @ResponseBody
    public FriendScore getFriend() {
        String username = UserManager.getUser(getUsername()).getFriend();

        if (username == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No friends found");
        }

        User user = UserManager.getUser(username);
        return new FriendScore(user.getUsername(), user.gettotalScore());
    }

    /**
     * Add friend to user.
     *
     * @param friend to add
     * @return ResponseEntity
     */
    @RequestMapping("/add")
    public ResponseEntity addFriend(@RequestBody Friend friend) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);

        User user = UserManager.getUser(friend.getName());
        if (user != null) {
            UserManager.addFriend(getUsername(), friend.getName());
            response = new ResponseEntity(HttpStatus.OK);
        }

        return response;
    }
}
