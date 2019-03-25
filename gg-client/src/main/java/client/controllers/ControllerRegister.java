package client.controllers;

import static client.requests.RegisterRequests.sendRegisterCredentials;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControllerRegister extends ControllerGeneral {

    @FXML
    Button submitButton;

    @FXML
    TextField usernameRegister;

    @FXML
    PasswordField passwordRegister;

    @FXML
    TextField emailRegister;

    @FXML
    Label errorMessage;

    private String username;
    private String email;
    private String password;

    /**
     * When the submit button is fired if username and password are valid then it closes the window.
     * Otherwise an error message appears.
     * @param event created by button interaction
     */

    public void submitButtonPressed(ActionEvent event) throws JsonProcessingException {

        if (!validText(username) || !validText(email) || !validText(password)) {
            errorMessage.setVisible(true);
        } else if (validText(username) && validText(email) && validText(password)) {
            errorMessage.setVisible(false);
            sendRegisterCredentials(username , password , email);
            submitButton.getScene().getWindow().hide();
        }


    }

    /**
     * Takes the username from the corresponding textField.
     * @param event created by textfield interaction
     */
    public void usernameEntered(ActionEvent event) {
        username = usernameRegister.getText();
        System.out.println(username);
        passwordRegister.requestFocus();
    }

    /**
     * Takes the email from the corresponding textField.
     * @param event created by textfield interaction
     */
    public void emailEntered(ActionEvent event) {
        email = emailRegister.getText();
        System.out.println(email);
        submitButton.requestFocus();

    }

    /**
     * Takes the password from the corresponding TextField.
     * @param event created by textfield interaction
     */
    public void passwordEntered(ActionEvent event) {
        password = passwordRegister.getText();
        System.out.println(password);
        emailRegister.requestFocus();
    }


}