package server.controller;

import database.entity.User;

import database.manager.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import server.entity.Friend;

@Controller
@RequestMapping("/friends")
public class FriendController {

    /**
     * Add friend to user.
     * @param friend to add
     * @return ResponseEntity
     */
    @PostMapping("/add")
    public ResponseEntity addFriend(@RequestBody Friend friend) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);

        User user = UserManager.getUser(friend.getName());
        if (user != null) {
            response = new ResponseEntity(HttpStatus.OK);
        }

        return response;
    }
}
