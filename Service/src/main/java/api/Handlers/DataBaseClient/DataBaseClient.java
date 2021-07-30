package api.Handlers.DataBaseClient;

import api.Handlers.DataBaseClient.DataStructures.AlcoholicDrink;
import api.Handlers.DataBaseClient.DataStructures.Ale;
import api.Handlers.DataBaseClient.DataStructures.Beer;
import api.Handlers.DataBaseClient.DataStructures.Lager;
import org.json.JSONArray;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseClient {
    private Connection connection;

    public DataBaseClient(String dbURL, String dbUser, String dbPassword) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            this.connection = connection;
        } else {
            System.out.println("Failed to make connection to database");
            this.connection = null;
        }
    }

    public boolean isConnected() {
        return this.connection != null;
    }

    public String getAllBeerByType(String type) throws RuntimeException, SQLException {
        String result;
        result = serializeObjects(getAllBeerObjectsByType(type));
        return result;
    }

    public String getAllBeer() throws RuntimeException, SQLException {
        String result;
        result = serializeObjects(getAllBeerObjects());
        return result;
    }

    public ArrayList<Object> getAllBeerObjects() throws RuntimeException, SQLException{
        if (!isConnected())
            throw new RuntimeException();

        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM beer");
        return processBeerFromSet(set);
    }

    public ArrayList<Object> getAllBeerObjectsByType(String type) throws RuntimeException, SQLException {
        if (!isConnected())
            throw new RuntimeException();

        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM beer WHERE type=" + wrapQuotes(type));
        ArrayList<Object> result = processBeerFromSet(set);
        set.close();
        return result;
    }

    public String serializeObjects(ArrayList<Object> list) {
        JSONArray array = new JSONArray(list);
        return array.toString();
    }

    private static ArrayList<Object> processBeerFromSet(ResultSet set) throws SQLException {
        ArrayList<Object> list = new ArrayList<>();
        while(set.next()){
            String label = set.getString("label");
            String type = set.getString("type");
            Integer price = set.getInt("price");
            String flavor = set.getString("flavor");
            if (type.equals("Ale"))
                list.add(new Ale(label, price, flavor));
            else if (type.equals("Lager"))
                list.add(new Lager(label, price, flavor));
        }
        return list;
    }

    private String wrapQuotes(String s) {
        return "'" + s + "'";
    }

}
