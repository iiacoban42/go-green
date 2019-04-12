package server.entity;


public class Transport {
    private String name;
    private double distance;

    public Transport() {

    }

    public Transport(String name, double distance) {
        this.name = name;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String toString() {
        return name + " " + distance;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Transport)) {
            return false;
        }
        Transport transport = (Transport) other;
        return Double.compare(transport.getDistance(), getDistance()) == 0
                && getName().equals(transport.getName());
    }

}
