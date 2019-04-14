package client.requests;

import client.entities.SolarPanels;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.util.Pair;

public class SolarPanelRequests extends GeneralRequests {

    /**
     * Send amount of solar panels to the user.
     * @param amount of solar panels.
     * @return server's response.
     * @throws JsonProcessingException .
     */
    public static String sendSolarPanels(int amount) throws JsonProcessingException {

        SolarPanels solarPanels = new SolarPanels(amount);
        String response =  doPostRequest(solarPanels , "http://localhost:8080/api/action/setSolarPanels");

        return response;

    }

    /**
     * Get amount of solar panels installed.
     * @return amount of solar panels installed.
     */
    public static int getSolarPanels() {

        Pair response = doGetRequest("http://localhost:8080/api/action/solarPanels" ,SolarPanels.class );

        SolarPanels solarPanels = (SolarPanels) response.getValue();

        return solarPanels.getamount();
    }


}
