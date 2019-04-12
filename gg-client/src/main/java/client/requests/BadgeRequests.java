package client.requests;

import client.entities.BadgeList;
import javafx.util.Pair;

public class BadgeRequests extends GeneralRequests {

    private  BadgeList badgeList;

    public BadgeRequests(){}

    public BadgeList getBadgeList() {
        return badgeList;
    }

    /**
     * Get badges from the server.
     * @return server response status.
     */
    public String getBadges() {

        String url = "http://localhost:8080/api/badges";

        Pair pair = doGetRequest(url , BadgeList.class);

        badgeList = (BadgeList) pair.getValue();

        return (String) pair.getKey();

    }
}
