package com.example.util;

import com.example.config.prop.PropertyConfig;

import java.util.Properties;

/**
 * Created by zhongr on 2017/7/31.
 */

public class PropertyUtil {


    public static String getProperty(String key, String def) {
        Properties properties = PropertyConfig.properties;
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return properties.getProperty(key);
    }


    public static boolean getProperty(String key, boolean def) {
        Properties properties = PropertyConfig.properties;
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static int getProperty(String key, int def) {
        Properties properties = PropertyConfig.properties;
        if (properties == null || properties.get(key) == null) {
            return def;
        }
        return Integer.parseInt(properties.getProperty(key));
    }

}
