package Features;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VeggieMealController {

    @FXML
    private Button submit;

    @FXML
    private VBox textFields;

    @FXML
    private TextField beans;

    @FXML
    private TextField veggieBurger;

    @FXML
    private TextField veggieBurgerCheese;

    @FXML
    private TextField quorn;

    @FXML
    private TextField nuts;

    @FXML
    private TextField tofu;

    @FXML
    private TextField egg;

    @FXML
    private TextField beefCroquette;

    @FXML
    private TextField chicken;

    @FXML
    private TextField pork;

    @FXML
    private TextField steak;

    @FXML
    private TextField lamb;

    @FXML
    private TextField hamburger;

    @FXML
    private TextField mincedMeat;

    @FXML
    private TextField mixedMincedMeat;

    @FXML
    private TextField insects;

    @FXML
    private TextField cheese;

    @FXML
    void sendVeggieMeal(ActionEvent event) {
        VeggieMeal veggieMeal = new VeggieMeal();

        for (Node node : textFields.getChildren()) {
            if (node instanceof TextField) {

                String quantity = ((TextField) node).getText();
                if(!quantity.isEmpty()) {
                    if (valid(quantity)) {
                        double quantityDouble = Double.parseDouble(quantity);
                        String ingredient = node.getId();
                        veggieMeal.setVeggieMeal(ingredient, quantityDouble);
                        System.out.println(quantity + " " + ingredient);
                    }

                    ((TextField) node).setText("");
                }
            }
        }
        System.out.println(veggieMeal.toString());

    }

    public boolean valid(String message) {
        try {
            double number = Double.parseDouble(message);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + message + "is NAN");
            return false;
        }
    }

}
