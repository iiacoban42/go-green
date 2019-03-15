package server.controller;

import database.manager.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.entity.MealList;
import server.entity.Score;
import server.meal.MealCalculator;

@RestController
@RequestMapping("/action")
public class Action {

    /**
     * Parse meal user ate.
     * @param mealList to check if valid
     * @return true or false
     */
    @PostMapping("/meal")
    public ResponseEntity login(@RequestBody MealList mealList) {
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        int score = (int)MealCalculator.getAmountCo2(mealList);
        UserManager.getUser("admin").settotalScore(score);

        return response;
    }

    /**
     * Returns score of user to user.
     * @return score
     */
    @PostMapping("/score")
    public Score score() {
        Score score = new Score();

        score.setTotalScore(UserManager.getUser("admin").gettotalScore());

        return score;
    }

}
