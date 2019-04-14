package server.controller;

import database.manager.BadgeManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.entity.BadgeList;

import java.security.Principal;

@RestController
@RequestMapping("/badges")
public class Badges {

    /**
     * Get all badges a user has.
     *
     * @param principal user details
     * @return list with all badges
     */
    @RequestMapping("")
    public BadgeList getAllBadges(Principal principal) {
        BadgeList badgeList;

        BadgeManager.checkBadges(principal.getName());

        badgeList = new BadgeList(BadgeManager.listBadgesUser(principal.getName()));

        return badgeList;
    }

}
