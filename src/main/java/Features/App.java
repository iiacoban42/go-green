package Features;

public class App {

    public static void main(String[] args) {
        VeggieMeal veg = new VeggieMeal();
        veg.get(16).setQuantity(500);
        veg.get(16).setSelected(true);
        veg.get(15).setQuantity(500);
        veg.get(15).setSelected(true);
        veg.get(5).setQuantity(500);
        veg.get(5).setSelected(true);
        System.out.println(veg.calculator());
        System.out.println(veg.toString());
        veg.setVeggieMeal("beans",10000);
        System.out.println(veg.toString());

    }
}
