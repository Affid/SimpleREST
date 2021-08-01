package api;

import api.Handlers.*;
import api.DataBaseClient.DataBaseClient;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Application {
    private HttpServer server;
    private DataBaseClient dataBaseClient;
    private static int port = 5000;
    private static String dbURL = "jdbc:postgresql://127.0.0.1:5432/javatest";
    private static String dbUser = "javauser";
    private static String dbPassword = "javauser";

    public Application() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        this.dataBaseClient = new DataBaseClient(dbURL, dbUser, dbPassword);
    }

    public static void main(String[] args) throws IOException {
        Application app = new Application();
        app.server.createContext("/api/ping", new ConnectionTestHandler());
        app.server.createContext("/api/db", new HelloFromDBHandler(app.dataBaseClient));
        app.server.createContext("/api/db/beer", new BeerHandler(app.dataBaseClient));
        app.server.createContext("/api/db/beer/ale", new AleHandler(app.dataBaseClient));
        app.server.createContext("/api/db/beer/lager", new LagerHandler(app.dataBaseClient));
        app.server.setExecutor(null);
        app.server.start();
    }


}
