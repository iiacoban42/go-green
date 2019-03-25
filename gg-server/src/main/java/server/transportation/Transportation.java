package server.transportation;

public class Transportation {
    private final String name;
    private final double co2;

    public Transportation(String name, double co2) {
        this.name = name;
        this.co2 = co2;
    }

    public String getName() {
        return name;
    }

    public double getCo2() {
        return co2;
    }
}