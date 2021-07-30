package api.Handlers;

import api.Handlers.DataBaseClient.DataBaseClient;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class BeerHandler implements HttpHandler {
    public DataBaseClient dataBaseClient;

    public BeerHandler(DataBaseClient dataBaseClient) {
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
                response = dataBaseClient.getAllBeer();
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
            return;
        }
        else if (method.equals("POST")) {
            if(!dataBaseClient.isConnected()){
                exchange.sendResponseHeaders(500, -1);
                return;
            }
            try {
                InputStream stream = exchange.getRequestBody();
                StringBuilder sb = new StringBuilder();
                for (int ch; (ch = stream.read()) != -1; )
                    sb.append((char) ch);

                stream.close();
                String result = validateJSONArray(sb.toString());

                dataBaseClient.putBeerToDatabase(result);
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
            exchange.sendResponseHeaders(201, -1);
        }
        else {
            exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
        }
        exchange.close();
    }

    private String validateJSONArray(String s) {
        if(s.startsWith("["))
            return s;
        else
            return '[' + s + ']';
    }
}
