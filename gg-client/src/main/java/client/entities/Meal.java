package client.entities;

import java.util.Objects;

public class Meal {

    private String product;
    private int quantity;

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

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }
        if (object instanceof Meal) {
            Meal meal = (Meal) object;
            if (meal.quantity == this.quantity && meal.product.equals(this.product)) {
                return true;
            }
        }

        return false;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
