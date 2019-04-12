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
    @SuppressWarnings("CheckStyle")
    @FXML
    void initialize() {

        BadgeRequests badgeRequests = new BadgeRequests();

        badgeRequests.getBadges();

        BadgeList badgeList = badgeRequests.getBadgeList();

        for (int i = 0; i < badgeList.getBadgeList().size(); i++) {

            Badge current = badgeList.getBadgeList().get(i);

            if (current.getBadgeName().equals("localProduceStreakBadge")) {

                if (current.getLevel() == 1) {
                    Image image = new Image("images/badges/localFBronze.jpg");
                    localF.setImage(image);
                }

                if (current.getLevel() == 2) {
                    Image image = new Image("images/badges/localFSilver.jpg");
                    localF.setImage(image);
                }

                if (current.getLevel() == 3) {
                    Image image = new Image("images/badges/localFGold.jpg");
                    localF.setImage(image);
                }
            }

            if (current.getBadgeName().equals("transportStreakBadge")) {

                if (current.getLevel() == 1) {
                    Image image = new Image("images/badges/transportFBronze.jpg");
                    transportF.setImage(image);
                }

                if (current.getLevel() == 2) {
                    Image image = new Image("images/badges/transportFSilver.jpg");
                    transportF.setImage(image);
                }

                if (current.getLevel() == 3) {
                    Image image = new Image("images/badges/transportFGold.jpg");
                    transportF.setImage(image);
                }
            }

            if (current.getBadgeName().equals("vegiemealStreakBadge")) {

                if (current.getLevel() == 1) {
                    Image image = new Image("images/badges/mealFBronze.jpg");
                    veganF.setImage(image);
                }

                if (current.getLevel() == 2) {
                    Image image = new Image("images/badges/mealFSilver.jpg");
                    veganF.setImage(image);
                }

                if (current.getLevel() == 3) {
                    Image image = new Image("images/badges/mealFGold.jpg");
                    veganF.setImage(image);
                }
            }


            if (current.getBadgeName().equals("vegiemealCo2SavedBadge")) {

                if (current.getLevel() == 1) {
                    Image veganMeal = new Image("images/badges/veggieMealBronze.jpg");
                    vegan.setImage(veganMeal);
                }

                if (current.getLevel() == 2) {
                    Image veganMeal = new Image("images/badges/veggieMealSilver.jpg");
                    vegan.setImage(veganMeal);
                }

                if (current.getLevel() == 3) {
                    Image veganMeal = new Image("images/badges/veggieMealGold.jpg");
                    vegan.setImage(veganMeal);
                }
            } else


                if (current.getBadgeName().equals("localProduceCo2SavedBadge")) {

                    if (current.getLevel() == 1) {
                        Image local = new Image("images/badges/localProduceBronze.jpg");
                        localProduce.setImage(local);
                    }

                    if (current.getLevel() == 2) {
                        Image local = new Image("images/badges/localProduceSilver.jpg");
                        localProduce.setImage(local);
                    }

                    if (current.getLevel() == 3) {
                        Image local = new Image("images/badges/localProduceGold.jpg");
                        localProduce.setImage(local);
                    }
                } else

                    if (current.getBadgeName().equals("transportCo2SavedBadge")) {

                        if (current.getLevel() == 1) {
                            Image transport = new Image("images/badges/transportationBronze.jpg");
                            transportation.setImage(transport);
                        }

                        if (current.getLevel() == 2) {
                            Image transport = new Image("images/badges/transportationSilver.jpg");
                            transportation.setImage(transport);
                        }

                        if (current.getLevel() == 3) {
                            Image transport = new Image("images/badges/transportationGold.jpg");
                            transportation.setImage(transport);
                        }
                    }

        }


        int score = MealRequests.getScore();

        if (score <= 10000) {

            Image general = new Image("images/badges/ScoreBronze.jpg");
            level.setImage(general);

        } else

            if (score <= 100000) {

                Image general = new Image("images/badges/ScoreSilver.jpg");
                level.setImage(general);

            } else {
                Image general = new Image("images/badges/ScoreGold.jpg");
                level.setImage(general);
            }




    }
}


