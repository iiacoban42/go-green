package server.entity;


public class Meal {
    private String product;
    private int quantity;

    public Meal() {

    }

    public Meal(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return product + " " + quantity;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Meal)) {
            return false;
        }
        Meal meal = (Meal) other;
        return getQuantity() == meal.getQuantity()
                && getProduct().equals(meal.getProduct());
    }

}
