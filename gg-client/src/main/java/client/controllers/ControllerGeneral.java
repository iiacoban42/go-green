package client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerGeneral {

    /**
     * Changes the window.
     * @param fxmlFile fxml file name.
     * @throws IOException if something goes wrong.
     */

    public void changeWindow(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/" + fxmlFile));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("GoGreen");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);

    }

    /**
     * Changes the scene.
     * @param fxmlFile fxml file name
     * @throws IOException if something goes wrong.                .
     * @param anchorPane anchor pane.
     */
    public void changeScene(String fxmlFile , AnchorPane anchorPane) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/" + fxmlFile ));
        anchorPane.getChildren().setAll(pane);

    }

    /**
     * Test if given message is valid.
     *
     * @param message to check if valid number
     * @return boolean
     */
    public boolean validNumber(String message) {

        try {
            double number = Double.parseDouble(message);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + message + " is NAN");
            return false;
        }
    }

    /**
     * Checks if input is valid.
     * @param text with string of textfield
     * @return true if the input is not null and not empty . Otherwise it returns false.
     */

    public static boolean validText(String text) {
        return text != null && !text.isEmpty();
    }



}
