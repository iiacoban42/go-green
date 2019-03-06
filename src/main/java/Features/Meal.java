package Features;

public class Meal {
    private String product;
    private double co2;

    public Meal(String product, double co2) {
        this.product = product;
        this.co2 = co2;
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
}
