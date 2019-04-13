package client.controllers;

import client.entities.Badge;
import client.entities.BadgeList;
import client.requests.BadgeRequests;
import client.requests.MealRequests;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ControllerBadges {

    @FXML
    ImageView vegan;

    @FXML
    ImageView transportation;

    @FXML
    ImageView localProduce;

    @FXML
    ImageView level;

    @FXML
    ImageView veganF;

    @FXML
    ImageView transportF;

    @FXML
    ImageView localF;


    // it needs if statements so we can decide what to display.
    @FXML
    void initialize() {
        BadgeRequests badgeRequests = new BadgeRequests();

        badgeRequests.getBadges();

        BadgeList badgeList = badgeRequests.getBadgeList();

        for (int i = 0; i < badgeList.getBadgeList().size(); i++) {

            Badge current = badgeList.getBadgeList().get(i);

            String badgeName = current.getBadgeName();
            String imageName = null;
            int imageNum = 0;

            if (badgeName.contains("localProduce")) {
                imageName = "localProduce";
                imageNum = 0;
            } else if (badgeName.contains("transport")) {
                imageName = "transportation";
                imageNum = 1 * 2;
            } else if (badgeName.contains("veggieMeal")) {
                imageName = "veggieMeal";
                imageNum = 2 * 2;
            }

            if (badgeName.contains("StreakBadge")) {
                imageName += "F";
                imageNum++;
            }

            String[] levels = {"Bronze", "Silver", "Gold"};
            imageName += levels[current.getLevel() - 1];

            System.out.println("images/badges/" + imageName + ".jpg");
            Image image = new Image("images/badges/" + imageName + ".jpg");

            ImageView[] imageViews = {
                localProduce, localF,
                transportation, transportF,
                vegan, veganF
            };
            imageViews[imageNum].setImage(image);
        }

        int score = MealRequests.getScore();

        Image general;
        if (score <= 10000) {
            general = new Image("images/badges/ScoreBronze.jpg");
        } else if (score <= 100000) {
            general = new Image("images/badges/ScoreSilver.jpg");
        } else {
            general = new Image("images/badges/ScoreGold.jpg");
        }
        level.setImage(general);

    }
}


