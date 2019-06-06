package fr.gosecuri.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

    private static final String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final String appProperties = rootPath + "application.properties";

    private static final Properties properties = new Properties();

    public static String getProperty(String key) {
        String property = null;
        try {
            properties.load(new FileInputStream(appProperties));

            property = properties.getProperty(key);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return property;
    }
}
