package ru.ifmo.server;


import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class Server {

    public final URI BASE_URI;

    public Server(int port) {
        BASE_URI = UriBuilder.fromUri("http://localhost/").port(port).build();
    }

    protected HttpServer startServer() throws IOException {
        System.out.println("Starting grizzly...");
        ResourceConfig rc = new PackagesResourceConfig("ru.ifmo.server");
        return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
    }

}
