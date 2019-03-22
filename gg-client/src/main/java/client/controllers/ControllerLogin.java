package client.controllers;

import static client.requests.LoginRequests.sendLoginCredentials;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ControllerLogin  {

    @FXML
    Text errorCredentials;

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
     * Otherwise an error message appears. Sends credentials to the server.
     * @param event created by button interaction
     */

    public void loginButtonPressed( ActionEvent event ) throws Exception {
        username = usernameTextField.getText();
        password = passwordTextField.getText();

        if (valid(username) && valid(password)) {
            String result = "";

            try {
                result =  sendLoginCredentials(username, password);
            } catch (IOException e) {
                System.out.println("Wrong credentials");
            }

            if  (result.equals("200 OK")) {
                errorCredentials.setVisible(false);

                Parent root2 = FXMLLoader.load(getClass().getResource("/mainPage.fxml"));
                Stage app = (Stage)((Node) event.getSource()).getScene().getWindow();
                app.setScene(new Scene(root2));
                app.show();

            } else {
                errorCredentials.setVisible(true);
            }
        } else {
            errorCredentials.setVisible(true);
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
        stage.setResizable(false);
    }

    /**
     * Takes the username from the corresponding Textfield.
     * @param event created by textfield interaction
     */
    public void usernameEntered(ActionEvent event) {
        username = usernameTextField.getText();
        System.out.println(username );
        passwordTextField.requestFocus();
    }

    /**
     * Takes password from the corresponding TextField.
     * @param event created by textfield interaction
     */
    public void passwordEntered(ActionEvent event) {
        password = passwordTextField.getText();
        System.out.println(password);
        loginButton.fire();
    }

    /**
     * Checks if input is valid.
     * @param text with string of textfield
     * @return true if input string is not empty or null. Otherwise it returns false.
     */
    private static boolean valid(String text) {
        return text != null && !text.isEmpty() && (text.length() <= 20);
    }


}
