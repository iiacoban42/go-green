package client.requests;

import client.entities.Temperature;

import java.io.IOException;

public class TemperatureRequests extends  GeneralRequests {

    /**
     * Send home Temperature.
     * @param temperature home temperature.
     * @return servers' response.
     * @throws IOException if something goes wrong.
     */
    public static String sendTemperature(Temperature temperature) throws IOException {

        String url = "http://localhost:8080/api/action/temperature";

        String response = doPostRequest(temperature  , url);

        return response;
    }


}
