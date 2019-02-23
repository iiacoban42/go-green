package server.resources;

import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.*;

public class TestTest {

    @Test
    public void test1() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/api/test");

        String result = target.request().get(String.class);
        assertEquals(result, "Test");
    }
}