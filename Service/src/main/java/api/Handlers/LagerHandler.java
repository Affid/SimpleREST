package api.Handlers;

import api.Handlers.DataBaseClient.DataBaseClient;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class LagerHandler implements HttpHandler {
    public DataBaseClient dataBaseClient;

    public LagerHandler(DataBaseClient dataBaseClient) {
        this.dataBaseClient = dataBaseClient;
    }
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        if (method.equals("GET")) {
            if(!dataBaseClient.isConnected()){
                exchange.sendResponseHeaders(500, -1);
                return;
            }
            String response;
            try {
                response = dataBaseClient.getAllBeerByType("Lager");
            }
            catch (RuntimeException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(500, -1);
                return;
            }
            catch (SQLException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(400, -1);
                return;
            }
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(response.getBytes());
            output.flush();
        }
        else {
            exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
        }
        exchange.close();
    }
}
