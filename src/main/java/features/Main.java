package features;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private Stage window;


    public static void main(String[] args) throws IOException {

        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../veggie_meal.fxml"));
        window.setScene(new Scene(root));
        window.show();


    }
}
