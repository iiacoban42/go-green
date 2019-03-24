package client.controllers;

import static client.controllers.ControllerMainPage.dialogBoxOn;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class ControllerGeneral {

    /**
     * Show Dialog.
     * @param text text to display.
     * @param heading head text.
     * @param stackPane stackPane.
     */
    public void showDialog(String text, String heading , StackPane stackPane ) {
        dialogBoxOn = true;
        JFXDialogLayout content =  new JFXDialogLayout();
        content.setHeading(new Text(heading));
        content.setBody(new Text(text
        ));

        JFXButton button = new JFXButton("Close");

        JFXDialog dialog = new JFXDialog(stackPane , content , JFXDialog.DialogTransition.CENTER);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
                dialogBoxOn = false;
            }
        });

        content.setActions(button);
        dialog.show();

    }


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
     * @param anchorPane anchor pane.
     * @throws IOException if something goes wrong.
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

    /**
     * Changes the textfield.
     * @param event text field activated
     * @param grid grid which contains the TextField
     */
    public  void changeTextField(ActionEvent event , GridPane grid ) {


        boolean nextFound = false;
        TextField current = (TextField) event.getSource();

        for (Node node : grid.getChildren()) {

            if (nextFound == true && node instanceof TextField) {

                TextField next = (TextField) node;
                next.requestFocus();
                break;

            }

            if (node instanceof TextField && node.equals(current)) {

                nextFound = true;

            }


        }


    }


}
