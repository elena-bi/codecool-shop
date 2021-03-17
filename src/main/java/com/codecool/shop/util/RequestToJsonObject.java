package com.codecool.shop.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestToJsonObject {
    public static JsonObject getJsonObjectFromRequest(HttpServletRequest req) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            content.append(inputLine);
        }
        br.close();
        System.out.println(content);
        Gson gson = new Gson();
        return gson.fromJson(content.toString(), JsonObject.class);
    }
}
