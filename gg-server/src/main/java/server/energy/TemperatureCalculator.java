package server.energy;

public class TemperatureCalculator {
    /**
     * References for Co2 values.
     * https://www.ovoenergy.com/guides/energy-guides/how-much-heating-energy-do-you-use.html
     * https://www.energuide.be/en/questions-answers/how-much-co2-does-my-home-emit/68/
     * https://www.energystar.gov/products/ask-the-expert/breaking-down-the-typical-utility-bill
     */

    public static double getAmountCo2(Temperature temp) {
        double averageEnergy = 185; //KWh/m2
        double result = temp.getSurface() * averageEnergy - temp.getEnergy() * 0.29;
        if (result > 0) {
            if (temp.getSystem().equals("electricity")) {
                return result * 230;
            }
            if (temp.getSystem().equals("naturalGas")) {
                return result * 198;
            }
            if (temp.getSystem().equals("heatingOil")) {
                return result * 264;
            }
        }
        return 0;
    }


}
