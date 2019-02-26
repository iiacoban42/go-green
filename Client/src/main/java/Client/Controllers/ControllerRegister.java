package Client.Controllers;


import Client.Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class ControllerRegister {

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
     *
     * @param event
     *
     * When the submit button is fired if username and password are valid then it closes the window.
     * Otherwise an error message appears.
     *
     */

    public void submitButtonPressed (ActionEvent event){

        if(!valid(username) || !valid(email) || !valid(password)){
            errorMessage.setVisible(true);
            return;
        }
        else if(valid(username) && valid(email) && valid(password)){
            errorMessage.setVisible(false);
            User user = new User(username , password , email);
            submitButton.getScene().getWindow().hide();
        }


    }

    /**
     *
     * @param event
     * Takes the username from the corresponding textField.
     *
     */
    public void usernameEntered (ActionEvent event){
        username = usernameRegister.getText();
        System.out.println(username);
    }

    /**
     *
     * @param event
     * Takes the email from the corresponding textField.
     *
     */
    public void emailEntered (ActionEvent event){
        email = emailRegister.getText();
        System.out.println(email);

    }

    /**
     *
     * @param event
     * Takes the password from the corresponding TextField.
     *
     */
    public void passwordEntered (ActionEvent event){
        password = passwordRegister.getText();
        System.out.println(password);

    }

    /**
     *
     * @param text
     * @return true if the input is not null and not empty . Otherwise it returns false.
     *
     */
    public static boolean valid(String text) {
        if (text != null && !text.isEmpty())
            return true;
        else
            return false;
    }

}