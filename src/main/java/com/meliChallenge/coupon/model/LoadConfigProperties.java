package com.meliChallenge.coupon.model;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfigProperties {

    public static Properties getPropertiesFile() {
        InputStream inputStream;
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = LoadConfigProperties.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
