package client.entities;

public class Temperature {
    private double surface;
    private double energy;
    private String system;

    /**
     * Temperature constructor.
     *
     * @param surface of the residence
     * @param energy  consumed
     * @param system  of heating
     */
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
