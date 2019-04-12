package client.requests;

import client.entities.TransportList;

import java.io.IOException;

public class TransportRequests extends GeneralRequests {


    /**
     * Sends transport List to server.
     * @param transportList transport List
     * @return server response.
     * @throws IOException if something goes wrong in the communication with the server.
     */
    public static String sendTransportList(TransportList transportList) throws IOException {

        String urlTransport = "http://localhost:8080/api/action/transport";

        String response  =  doPostRequest(transportList , urlTransport);

        return response;
    }

}
