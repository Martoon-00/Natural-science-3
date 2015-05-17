package ru.ifmo.server;

import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;

public class ServerLauncher {

    public static void main(String[] args) throws IOException {
        final Server server = new Server(1234);
        HttpServer httpServer = server.startServer();
        System.out.println(String.format("Try %ssolve/<solver canonical class name>?<params in get query>", server.BASE_URI));
        System.in.read();
        httpServer.stop();
    }
}
