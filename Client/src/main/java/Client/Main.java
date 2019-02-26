package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

import java.util.Scanner;



@SpringBootApplication
public class Main extends Application {

private ConfigurableApplicationContext springContext;
private Parent root;

    public static void main(String[] args) {

       Application.launch(args);
    }

    @Override
    public void init() throws  Exception{
        springContext = SpringApplication.run(Main.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
        root = fxmlLoader.load();
    }
    @Override
    public void start(Stage stage) throws Exception {
             stage.setScene(new Scene(root));
             stage.show();
    }

    @Override
    public void stop() {
        springContext.stop();
    }
}

