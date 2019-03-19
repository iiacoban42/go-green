package server.energy;

public class Temperature {
    private double surface;
    private double energy;
    private String system;

    public Temperature(double surface, double energy, String system) {
        this.surface = surface;
        this.energy = energy;
        this.system = system;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
