package server.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransportList {
    private List<Transport> transport;

    public TransportList() {
        this.transport = new ArrayList<>();
    }

    public TransportList(List<Transport> transport) {
        this.transport = transport;
    }

    public void addTransport(Transport transport) {
        this.transport.add(transport);
    }

    public void removeTransport(Transport transport) {
        this.transport.remove(transport);
    }

    public List<Transport> getTransport() {
        return this.transport;
    }

    public void setTransport(List<Transport> transport) {
        this.transport = transport;
    }

    public int size() {
        return transport.size();
    }

    public Transport get(int index) {
        return transport.get(index);
    }

    /**
     * Creates a string representation of the object.
     *
     * @return string representation of the object
     */
    public String toString() {
        String result = "trip: ";
        for (Transport transportation : transport) {
            result += transportation.getName() + " " + transportation.getDistance() + "\n";
        }
        return result;
    }


    /**
     * Parses json strings into a MealList.
     *
     * @param json string translated into a list of meals
     */
    public void jsonConverter(String json) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            transport = objectMapper.readValue(json, new TypeReference<List<Transport>>() {
            });

        } catch (IOException e) {
            System.out.println("string not json");
        }

    }




}
