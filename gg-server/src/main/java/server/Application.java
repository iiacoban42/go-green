package server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Application {

    /**
     * Main function of the server project.
     *
     * */
    public static void main(String [] args) throws Exception {

        Server server = new Server(8080);
        server.setHandler(getJerseyHandler());

        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

    private static Handler getJerseyHandler() {
        ServletContextHandler context
            = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/api/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
            "jersey.config.server.provider.packages",
            "server.resources");

        return context;
    }

}
