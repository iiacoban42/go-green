package server.energy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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

    public Temperature() {

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

    public String toString() {
        return "surface: " + surface + " energy: " + energy + " heating system: " + system + "\n";

    }

    /**
     * Parses json strings into a Temperature object.
     *
     * @param json string translated into Temperature
     */
    public void jsonConverter(String json) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Temperature temp = objectMapper.readValue(json, Temperature.class);
            this.surface = temp.getSurface();
            this.system = temp.getSystem();
            this.energy = temp.getEnergy();

        } catch (IOException e) {
            System.out.println("string not json");
        }

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Temperature)) {
            return false;
        }
        Temperature that = (Temperature) other;
        return Double.compare(that.getSurface(), getSurface()) == 0
                && Double.compare(that.getEnergy(), getEnergy()) == 0
                && getSystem().equals(that.getSystem());
    }

}
