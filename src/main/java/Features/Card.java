package Features;

import java.awt.image.BufferedImage;

public class Card {

    private String name;
    private int value;
    private BufferedImage image;

    public Card(String name, int value, BufferedImage image) {
        this.name = name;
        this.value = value;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String toString() {
        return "card: " + name ;
    }


}
