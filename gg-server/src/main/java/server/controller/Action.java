package server.controller;

import database.entity.SolPanelAction;
import database.manager.ActionManager;
import database.manager.SolPanelActionManager;
import database.manager.UserManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.energy.Temperature;
import server.energy.TemperatureCalculator;
import server.entity.MealList;
import server.entity.Score;
import server.entity.SolarPanels;
import server.entity.TransportList;
import server.meal.LocalProduceCalc;
import server.meal.MealCalculator;
import server.transportation.TransportationCalculator;

import java.util.List;

@RestController
@RequestMapping("/action")
public class Action {

    private String getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * Returns all actions from a user.
     *
     * @return list
     */
    @GetMapping("/manage/actions")
    @ResponseBody
    public List getAllActions() {

        return ActionManager.listActionsUser(getUser());
    }

    /**
     * Removes a action done by a user.
     *
     * @param actionId of action
     * @return new list
     */
    @RequestMapping(value = {"/manage/delete", "manage/remove"})
    public List removeAction(@RequestParam(value = "id") long actionId) {

        database.entity.Action action = ActionManager.getAction(actionId);
        ActionManager.deleteAction(actionId);

        return ActionManager.listActionsUser(getUser());
    }

    /**
     * Parse meal user ate.
     *
     * @param mealList to check if valid
     * @return true or false
     */
    @PostMapping("/meal")
    public ResponseEntity meal(@RequestBody MealList mealList) {
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        int score = (int) MealCalculator.getAmountCo2(mealList);
        ActionManager.addAction("meal", getUser(), score);

        return response;
    }

    /**
     * Parse meal user ate.
     *
     * @param mealList to check if valid
     * @return true or false
     */
    @PostMapping("/localProduce")
    public ResponseEntity localProduce(@RequestBody MealList mealList) {
        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        int score = (int) LocalProduceCalc.getAmountCo2(mealList);
        ActionManager.addAction("localProduce", getUser(), score);

        return response;
    }

    /**
     * Parse transport user did.
     *
     * @param transportList list of transport
     * @return OK
     */
    @PostMapping("/transport")
    public ResponseEntity transport(@RequestBody TransportList transportList) {
        int score = (int) TransportationCalculator.getAmountCo2(transportList);

        if (transportList.size() == 1 && transportList.get(0).getName().equals("bike")) {
            ActionManager.addAction("bike", getUser(), score);
        } else {
            ActionManager.addAction("transport", getUser(), score);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Parse temperature user did.
     *
     * @param temperature class
     * @return OK
     */
    @PostMapping("/temperature")
    public ResponseEntity temperature(@RequestBody Temperature temperature) {
        int score = (int) TemperatureCalculator.getAmountCo2(temperature);
        ActionManager.addAction("temperature", getUser(), score);

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Parse amount of solar panels user has.
     *
     * @param solarPanels class
     * @return OK
     */
    @PostMapping("/setSolarPanels")
    public ResponseEntity temperature(@RequestBody SolarPanels solarPanels) {
        SolPanelAction solPanelAction = SolPanelActionManager.getActiveSpByUser(getUser());
        if (solPanelAction != null) {
            if (solarPanels.getamount() != 0) {
                solPanelAction.setNumSolarPanels(solarPanels.getamount());
                SolPanelActionManager.update(solPanelAction);
            } else {
                SolPanelActionManager.deleteSp(solPanelAction.getId());
            }
        } else {
            SolPanelActionManager.createSp(getUser(), solarPanels.getamount());
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Returns score of user to user.
     *
     * @return score
     */
    @GetMapping("/score")
    @ResponseBody
    public Score score() {
        Score score = new Score();

        score.setTotalScore(UserManager.getUser(getUser()).gettotalScore());

        return score;
    }

    /**
     * Return the number of solar panels a user has.
     *
     * @return amount of solar panels
     */
    @GetMapping("/solarPanels")
    @ResponseBody
    public SolarPanels solarPanels() {
        SolarPanels solarPanels = new SolarPanels();

        SolPanelAction solPanelAction = SolPanelActionManager.getActiveSpByUser(getUser());
        solarPanels.setamount(solPanelAction.getNumSolarPanels());

        return solarPanels;
    }
}
