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
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;




@Component
public class ControllerLogin extends ControllerGeneral  {

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

        if (validText(username) && validText(password)) {
            String result = "";

            try {
                result =  sendLoginCredentials(username, password);
            } catch (RestClientResponseException e) {
                System.out.println("Wrong credentials");
            }

            if  (result.equals("200 OK")) {
                errorCredentials.setVisible(false);

                Parent root2 = FXMLLoader.load(getClass().getResource("/homePage.fxml"));
                Stage app = (Stage)((Node) event.getSource()).getScene().getWindow();
                app.setTitle("GoGreen");
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
        changeWindow("register.fxml");
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



}

