package server.meal;

public class Meal {
    private final String product;
    private final double co2;
    private int quantity;
    private final boolean vegetarian;

    /**
     * Constructor of the Meal object.
     *
     * @param product    contained in the meal.
     * @param co2        of the product/g.
     * @param quantity   of the product in g
     * @param vegetarian product or not.
     */
    public Meal(String product, double co2, int quantity, boolean vegetarian) {
        this.product = product;
        this.co2 = co2;
        this.quantity = quantity;
        this.vegetarian = vegetarian;
    }

    public String getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCo2() {
        return co2;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
}
