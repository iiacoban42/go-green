package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static client.ServerControllers.LoginRequests.sendCredentials;


@Component
public class ControllerLogin  {

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Button loginButton;

    @FXML
    Button registerButton;

    @FXML
    Label errorMessage;

    private String username;
    private String password;

    /**
     * When the login Button is fired if password and username are valid it closes the window.
     * Otherwise an error message appears.
     * @param event created by button interaction
     */

    public void loginButtonPressed(ActionEvent event ) throws IOException {

        if (!valid(username) || !valid(password)) {
            errorMessage.setVisible(true);
        } else if (valid(username) && valid(password)) {
            sendCredentials(username , password);
            errorMessage.setVisible(false);
            loginButton.getScene().getWindow().hide();
        }

    }

    /**
     * When the register button is fired , a new window with the registration form appears.
     * @param event created by button interaction
     */
    public void registerButtonPressed(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/register.fxml"));
        Parent root1 = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("GoGreen");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * Takes the username from the corresponding Textfield.
     * @param event created by textfield interaction
     */
    public void usernameEntered(ActionEvent event) {
        username = usernameTextField.getText();
        System.out.println(username );

    }

    /**
     * Takes password from the corresponding TextField.
     * @param event created by textfield interaction
     */
    public void passwordEntered(ActionEvent event) {
        password = passwordTextField.getText();
        System.out.println(password);
    }

    /**
     * Checks if input is valid.
     * @param text with string of textfield
     * @return true if input string is not empty or null. Otherwise it returns false.
     */
    private static boolean valid(String text) {
        return text != null && !text.isEmpty();
    }


}

