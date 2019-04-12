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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Transportation)) {
            return false;
        }
        Transportation that = (Transportation) other;
        return Double.compare(that.getCo2(), getCo2()) == 0
                && getName().equals(that.getName());
    }

}