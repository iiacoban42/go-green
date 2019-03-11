package server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.entity.MealList;
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

        MealCalculator.getAmountCo2(mealList);

        return response;
    }

}
