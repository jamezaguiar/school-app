package server.connection;

import client.model.Student;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPHandler {
    private String url;


    public HTTPHandler() throws MalformedURLException {
        this.url = "http://localhost:3333/";

    }

    public String GETHandler( String query) throws IOException {
        URL route = new URL(this.url + query);
        HttpURLConnection con = (HttpURLConnection) route.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String input;
        StringBuilder content = new StringBuilder();

        while((input = in.readLine()) != null){
            content.append(input);
        }


        con.disconnect();
        return content.toString();
    }

    public String POSTHandler(String params, String query) throws IOException {
        URL route = new URL(this.url + query);
        HttpURLConnection con = (HttpURLConnection) route.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);


        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(params.getBytes());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String input;
        StringBuilder content = new StringBuilder();

        while((input = in.readLine()) != null){
            content.append(input);
        }

        con.disconnect();
        return content.toString();
    }

    public String DELETEHandler(String params, String query) throws IOException {
        URL route = new URL(this.url + query);
        HttpURLConnection con = (HttpURLConnection) route.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);


        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.write(params.getBytes());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String input;
        StringBuilder content = new StringBuilder();

        while((input = in.readLine()) != null){
            content.append(input);
        }

        con.disconnect();
        return content.toString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
