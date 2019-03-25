package client.entities;

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
}
