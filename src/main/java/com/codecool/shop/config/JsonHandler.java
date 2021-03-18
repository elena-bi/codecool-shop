package com.codecool.hackernews;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class JsonHandler {
    private String siteUrl;

    public JsonHandler(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public JsonArray jsonArrayFromUrl() {
        JsonArray jsonarr = null;
        String inline = "";
        try {
            URL url = new URL(siteUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if(responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " +responsecode);
            } else {

                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext())
                {
                    inline+=sc.nextLine();
                }
                sc.close();

                JsonParser parse = new JsonParser();
                jsonarr = parse.parse(inline).getAsJsonArray();

            }
        } catch (IOException ex) {}
        return jsonarr;
    }
}