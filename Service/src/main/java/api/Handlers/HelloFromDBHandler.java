package api.Handlers;

import api.DataBaseClient.DataBaseClient;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HelloFromDBHandler implements HttpHandler {
    public DataBaseClient dataBaseClient;

    public HelloFromDBHandler(DataBaseClient dataBaseClient) {
        this.dataBaseClient = dataBaseClient;
    }

    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        if (method.equals("GET")) {
            String response = "Postgres connected!";
            if (!dataBaseClient.isConnected())
                response = "No postgres connection";
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream output = exchange.getResponseBody();
            output.write(response.getBytes());
            output.flush();
        } else {
            exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
        }
        exchange.close();
    }
}
