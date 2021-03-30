package com.codecool.shop.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ReadPropertyValues {
    private HashMap<String, String> result = new HashMap<>();
    private InputStream inputStream;

    public HashMap<String, String> getPropertyValues() throws IOException {

        try {
            Properties properties = new Properties();
            String propertiesFileName = "connection.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propertiesFileName + "' not found in the classpath");
            }

            result.put("url", properties.getProperty("url"));
            result.put("database", properties.getProperty("database"));
            result.put("user", properties.getProperty("user"));
            result.put("password", properties.getProperty("password"));
            result.put("dao", properties.getProperty("dao"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
