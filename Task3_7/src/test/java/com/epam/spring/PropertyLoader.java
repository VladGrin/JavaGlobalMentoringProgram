package com.epam.spring;

import java.io.IOException;
import java.util.Properties;

public final class PropertyLoader {

    private PropertyLoader() {

    }

    public static Properties loadProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyLoader.class.getClassLoader().getResourceAsStream(fileName));
        return properties;
    }

    public static Properties loadApplicationProperties() throws IOException {
        return loadProperties("application.properties");
    }
}
