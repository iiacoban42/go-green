package server.transportation;

import server.entity.Transport;
import server.entity.TransportList;

/**
 * Reference to Co2 values.
 * https://www.delijn.be/en/overdelijn/organisatie/zorgzaam-ondernemen/milieu/co2-uitstoot-voertuigen.html
 * bike:  https://ecf.com/news-and-events/news/how-much-co2-does-cycling-really-
 */

public class TransportationCalculator {

    public static final Transportation[] vehicle = {

        new Transportation("bike", 5),
        new Transportation("bus", 101),
        new Transportation("tram", 23),
        new Transportation("metro", 30.5),
        new Transportation("train", 28),
        new Transportation("scooter", 77)
    };

    /**
     * Calculates the Co2 saved by taking public transportation.
     * @param transportList contains the means of transportation the user selected
     * @return the Co2 saved compared to a car
     */
    public static double getAmountCo2(TransportList transportList) {
        double co2 = 0;
        double result;
        double totalDistance = 0;
        double carCo2 = 127;
        for (Transport listTransport : transportList.getTransport()) {
            for (Transportation transportation : vehicle) {
                if (transportation.getName().equals(listTransport.getName())) {
                    co2 += transportation.getCo2() * listTransport.getDistance();
                    totalDistance += listTransport.getDistance();
                }
            }
        }

        result = carCo2 * totalDistance - co2;
        return result;
    }


}