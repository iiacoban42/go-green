package Features;

public class Meal {
    private String product;
    private double co2;
    private double quantity;
    private boolean selected;

    public Meal(String product, double co2, double quantity, boolean selected) {
        this.product = product;
        this.co2 = co2;
        this.quantity = quantity;
        this.selected = selected;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
