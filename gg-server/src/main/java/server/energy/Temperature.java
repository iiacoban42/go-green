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

    public void jsonConverter(String json) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Temperature temp = objectMapper.readValue(json, Temperature.class);
            this.surface = temp.getSurface();
            this.system = temp.getSystem();
            this.energy = temp.getEnergy();

        } catch (IOException e) {
            System.out.println("exception");
        }

    }

}
