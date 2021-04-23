package com.example.javawebapp;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONToDatabase {

    /**
     * Establishes a connection to locally hosted database
     * @return connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/betsdb","root", "1X2dbpassword!");
        return conn;
    }

    /**
     * main method that converts json data to database
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("bets.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("bets");
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO bettingsummary values (?,?,?,?,?,?,?)");

            for(Object object : jsonArray){

                JSONObject record = (JSONObject) object;

                //get variables from json object
                int id = Integer.parseInt((String) record.get("id"));
                int numbets = Integer.parseInt((String) record.get("numbets"));
                String game = (String) record.get("game");
                float stake = Float.parseFloat((String) record.get("stake"));
                float returns = Float.parseFloat((String) record.get("returns"));
                int clientID = Integer.parseInt((String) record.get("clientid"));
                String date = (String) record.get("date");

                //set variables to the prepared statement to enter into database
                ps.setInt(1,id);
                ps.setInt(2,numbets);
                ps.setString(3,game);
                ps.setFloat(4,stake);
                ps.setFloat(5,returns);
                ps.setInt(6,clientID);
                ps.setString(7,date);

                System.out.println("Database updated with new records...");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}

