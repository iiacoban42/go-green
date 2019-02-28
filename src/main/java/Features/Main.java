package Features;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Card bike = new Card("take the bike", 20, ImageIO.read(new File("src\\main\\java\\Features\\cards\\bike.jpg")));
        Card bus = new Card("take public transportation", 10, ImageIO.read(new File("src\\main\\java\\Features\\cards\\bus.jpg")));
        Card localProduce = new Card("buy local produce", 15, ImageIO.read(new File("src\\main\\java\\Features\\cards\\local.jpg")));
        Card veggieMeal = new Card("vegetarian meal", 15, ImageIO.read(new File("src\\main\\java\\Features\\cards\\veggie.jpg")));
        Card solar = new Card("solar panel", 50, ImageIO.read(new File("src\\main\\java\\Features\\cards\\solar.jpg")));
        Card temp = new Card("temperature", 25, ImageIO.read(new File("src\\main\\java\\Features\\cards\\temp.jpg")));


        System.out.println(solar.toString());
        System.out.println(temp.toString());
        System.out.println(veggieMeal.toString());
        System.out.println(bike.toString());
        System.out.println(bus.toString());
        System.out.println(localProduce.toString());

    }
}
