package server.controller;

import database.manager.ActionManager;
import database.manager.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.entity.MealList;
import server.entity.Score;
import server.entity.TransportList;
import server.meal.MealCalculator;
import server.transportation.TransportationCalculator;

import java.util.List;

@RestController
@RequestMapping("/action")
public class Action {

    /**
     * Returns all actions from a user.
     * @return list
     */
    @GetMapping("/manage/actions")
    @ResponseBody
    public List getAllActions() {

        List actionList = ActionManager.listActionsUser("admin");

        return actionList;
    }

    /**
     * Removes a action done by a user.
     * @param actionId of action
     * @return new list
     */
    @RequestMapping(value = {"/manage/delete","manage/remove"})
    public List removeAction(@RequestParam(value = "id") long actionId) {

        database.entity.Action action = ActionManager.getAction(actionId);
        ActionManager.deleteAction(actionId);
        List actionList = ActionManager.listActionsUser("admin");

        return actionList;
    }

    /**
     * Parse meal user ate.
     * @param mealList to check if valid
     * @return true or false
     */
    @PostMapping("/meal")
    public ResponseEntity meal(@RequestBody MealList mealList) {
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        int score = (int)MealCalculator.getAmountCo2(mealList);
        // System.out.println("score: " + score);
        ActionManager.addAction("meal", "admin", score);

        return response;
    }

    /**
     * Parse transport user did.
     * @param transportList to check if valid
     * @return true or false
     */
    @PostMapping("/transport")
    public ResponseEntity meal(@RequestBody TransportList transportList) {
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        int score = (int) TransportationCalculator.getAmountCo2(transportList);
        ActionManager.addAction("transport", "admin", score);

        return response;
    }

    /**
     * Returns score of user to user.
     * @return score
     */
    @GetMapping("/score")
    @ResponseBody
    public Score score() {
        Score score = new Score();

        score.setTotalScore(UserManager.getUser("admin").gettotalScore());

        return score;
    }

}
