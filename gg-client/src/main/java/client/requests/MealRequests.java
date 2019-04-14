package client.requests;

import client.entities.MealList;

import client.entities.Score;
import javafx.util.Pair;

import java.io.IOException;

public class MealRequests extends GeneralRequests {

    /**
     * Sends Meal List to the server.
     *
     * @param mealList list with meals
     * @return responseEntity message from the server
     * @throws IOException input output exception.
     */
    public static String sendMealList(MealList mealList ,  String url) throws IOException {

        String response = doPostRequest(mealList , url);

        return response;
    }

    /**
     * Request score from the server.
     *
     * @return responseEntity message from the server.
     */
    public static int getScore() {

        String url = "http://localhost:8080/api/action/score";

        Pair pair = doGetRequest(url , Score.class);

        Score score = (Score) pair.getValue();

        return score.getTotalScore();
    }
}