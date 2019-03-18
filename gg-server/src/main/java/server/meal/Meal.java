package server.meal;

public class Meal {
    private final String product;
    private final double co2;
    private final boolean vegetarian;

    /**
     * Constructor of the Meal object.
     *
     * @param product    contained in the meal.
     * @param co2        of the product/g.
     * @param vegetarian product or not.
     */
    public Meal(String product, double co2, boolean vegetarian) {
        this.product = product;
        this.co2 = co2;
        this.vegetarian = vegetarian;
    }

    public String getProduct() {
        return product;
    }

    public double getCo2() {
        return co2;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
}
