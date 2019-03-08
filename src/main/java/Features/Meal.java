package Features;

public class Meal {
    private final String product;
    private final double co2;
    private  double quantity;
    private final boolean vegetarian;
    private boolean selected;

    public Meal(String product, double co2, double quantity, boolean vegetarian, boolean selected) {
        this.product = product;
        this.co2 = co2;
        this.quantity = quantity;
        this.vegetarian = vegetarian;
        this.selected = selected;
    }

    public String getProduct() {
        return product;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getCo2() {
        return co2;
    }

    public double getQuantity() {
        return quantity;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}