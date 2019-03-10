package server.meal;

public class Meal {
    private final String product;
    private final double co2;
    private double quantity;
    private final boolean vegetarian;

    /**
     * Constructor of the Meal object.
     * @param product contained in the meal.
     * @param co2 of the product/g.
     * @param quantity of the product in g
     * @param vegetarian product or not.
     * @param selected in the meal or not.
     */
    public Meal(String product, double co2, double quantity, boolean vegetarian, boolean selected) {
        this.product = product;
        this.co2 = co2;
        this.quantity = quantity;
        this.vegetarian = vegetarian;
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
}