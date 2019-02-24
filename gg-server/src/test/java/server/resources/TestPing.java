package server.resources;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;

import static org.junit.Assert.*;

public class TestPing extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(Ping.class);
    }

    @Test
    public void test1() {
        String response = target("/ping").request().get(String.class);
        assertEquals(response, "Pong");
    }
}