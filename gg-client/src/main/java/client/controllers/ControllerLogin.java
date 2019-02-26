package client.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

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
     *
     * @param event
     * When the login Button is fired if password and username are valid it closes the window.Otherwise an error message appears.
     *
     */

    public void loginButtonPressed (ActionEvent event ) {

        if (!valid(username) || !valid(password)) {
            errorMessage.setVisible(true);
            return;
        } else if(valid(username) && valid(password)){
            errorMessage.setVisible(false);
            loginButton.getScene().getWindow().hide();
        }

    }

    /**
     *
     * @param event
     * When the register button is fired , a new window with the registration form appears.
     *
     */
    public void registerButtonPressed (ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/register.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("GoGreen");
            stage.setScene(new Scene(root1));
            stage.show();


        }
        catch(Exception e){}

    }

    /**
     *
     * @param event
     * Takes the username from the corresponding Textfield.
     *
     */
    public void usernameEntered (ActionEvent event){
        username = usernameTextField.getText();
        System.out.println(username );

    }
    /*
     * @param event
     *  Takes password from the corresponding TextField.
     *
     */
    public void passwordEntered(ActionEvent event){
        password = passwordTextField.getText();
        System.out.println(password);
    }

    /*
     * @param text
     * @return true if input string is not empty or null. Otherwise it returns false.
     */
    public static boolean valid(String text) {
        if (text != null && !text.isEmpty())
            return true;
        else
            return false;
    }


}

