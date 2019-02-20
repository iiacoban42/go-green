package server.resources;

import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class AuthenticationTest {
    @Test
    public void testUser() {
        Form form = new Form();
        form.param("username", "test")
                .param("password", "test");

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/api/authentication");

        Future<String> response = target.
                request(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.TEXT_PLAIN)
                .buildPost(Entity.form(form)).submit(String.class);

        try {
            System.out.println(response.get());
        }
        catch (Exception e) {

        }
    }
}