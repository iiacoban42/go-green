package client.entities;

import java.util.ArrayList;
import java.util.List;

public class BadgeList {

    private List<Badge> badgeList;

    public BadgeList() {
        this.badgeList = new ArrayList<>();
    }

    public BadgeList(List<Badge> badgeList) {
        this.badgeList = badgeList;
    }

    public void addBadge(Badge badge) {
        this.badgeList.add(badge);
    }

    public void removeBadge(Badge badge) {
        this.badgeList.remove(badge);
    }

    public List<Badge> getBadgeList() {
        return badgeList;
    }

    public void setBadgeList(List<Badge> badgeList) {
        this.badgeList = badgeList;
    }


}
