package Features;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;


public class Main extends Application {
    private Stage window;
    private Scene game;

    public static void main(String[] args) throws IOException {


        launch(args);

    }

    public void start(Stage primaryStage) throws Exception {

        // Create images + set paths

        FileInputStream pathBike = new FileInputStream("src\\main\\java\\Features\\cards\\bike.jpg");
        ImageView iBike =new ImageView( new Image(pathBike));
        iBike.setPreserveRatio(true);
        iBike.setFitHeight(300);

        FileInputStream pathBus = new FileInputStream("src\\main\\java\\Features\\cards\\bus.jpg");
        ImageView iBus =new ImageView( new Image(pathBus));
        iBus.setPreserveRatio(true);
        iBus.setFitHeight(300);

        FileInputStream pathLocalProduce = new FileInputStream("src\\main\\java\\Features\\cards\\local.jpg");
        ImageView iLocal =new ImageView( new Image(pathLocalProduce));
        iLocal.setPreserveRatio(true);
        iLocal.setFitHeight(300);

        FileInputStream pathVeggieMeal = new FileInputStream("src\\main\\java\\Features\\cards\\veggie.jpg");
        ImageView iVeggie =new ImageView( new Image(pathVeggieMeal));
        iVeggie.setPreserveRatio(true);
        iVeggie.setFitHeight(300);

        FileInputStream pathSolar = new FileInputStream("src\\main\\java\\Features\\cards\\solar.jpg");
        ImageView iSolar =new ImageView( new Image(pathSolar));
        iSolar.setPreserveRatio(true);
        iSolar.setFitHeight(300);

        FileInputStream pathTemp = new FileInputStream("src\\main\\java\\Features\\cards\\temp.jpg");
        ImageView iTemp =new ImageView( new Image(pathTemp));
        iTemp.setPreserveRatio(true);
        iTemp.setFitHeight(300);

        // Create card objects

        Card bike = new Card("take the bike", 20, ImageIO.read(pathBike));
        Card bus = new Card("take public transportation", 10, ImageIO.read(pathBus));
        Card localProduce = new Card("buy local produce", 15, ImageIO.read(pathLocalProduce));
        Card veggieMeal = new Card("vegetarian meal", 15, ImageIO.read(pathVeggieMeal));
        Card solar = new Card("solar panel", 50, ImageIO.read(pathSolar));
        Card temp = new Card("temperature", 25, ImageIO.read(pathTemp));

        window = primaryStage;
        window.setTitle("goGreen");

        // Layout

        FileInputStream input = new FileInputStream("src\\main\\java\\Features\\cards\\bike.jpg");
        HBox layout = new HBox(20);
        Label gg = new Label("GoGreen");
        layout.getChildren().addAll(gg, iBike,iBus,iLocal,iSolar,iTemp,iVeggie);

        game = new Scene(layout);
        window.setScene(game);
        window.show();

        Deck deck = new Deck();
        //Events
        iBike.setOnMouseClicked(e -> {
            deck.add(bike);
            System.out.println(deck.toString());
        });

        iBus.setOnMouseClicked(e -> {
            deck.add(bus);
            System.out.println(deck.toString());
        });

        iSolar.setOnMouseClicked(e -> {
            deck.add(solar);
            System.out.println(deck.toString());
        });

        iVeggie.setOnMouseClicked(e -> {
            deck.add(veggieMeal);
            System.out.println(deck.toString());
        });

        iLocal.setOnMouseClicked(e -> {
            deck.add(localProduce);
            System.out.println(deck.toString());
        });
        iTemp.setOnMouseClicked(e -> {
            deck.add(temp);
            System.out.println(deck.toString());
        });




    }
}
