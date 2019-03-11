package features;

public class Meal {
    private final String product;
    private final Double co2;
    private Double quantity;
    private final boolean vegetarian;
    private boolean selected;

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
        this.selected = selected;
    }

    public String getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getCo2() {
        return co2;
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

//    @Override
//    public String toString() {
//        String toString = this.product;
//          toString += "-" + String.format(Locale.US, "%.3f", this.co2) + "-" + this.quantity;
//
//        if (this.isVegetarian()) {
//            toString += "-vegetarian";
//        } else {
//            toString += "-not-vegetarian";
//        }
//
//        if (this.isSelected()) {
//            toString += "-is-selected";
//        } else {
//            toString += "-not-selected";
//        }
//
//        return toString;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other instanceof Meal) {
//            Meal otherMeal = (Meal) other;
//            if (otherMeal.product.equals(this.product)) {
//                if (otherMeal.co2 == this.co2){
//                    if (otherMeal.quantity ==  this.quantity) {
//                        if(otherMeal.vegetarian == this.vegetarian) {
//                            if (otherMeal.selected == this.selected) {
//                                return true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//  return false;
//    }
}