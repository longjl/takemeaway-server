package com.takemeaway.util;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by basil on 14-4-5.
 */
public class PropertiesContent {
    public static Logger log = Logger.getLogger(PropertiesContent.class);
    public static Map<String, Object> config = new HashMap<String, Object>();
    public static Properties properties = new Properties();

    public PropertiesContent() {
    }

    /**
     * 配置文件的名称
     *
     * @param configName
     */
    public PropertiesContent(String configName) {
        ResourceBundle rb = ResourceBundle.getBundle(configName);
        Enumeration<String> cfgs = rb.getKeys();
        while (cfgs.hasMoreElements()) {
            String key = cfgs.nextElement();
            String val = rb.getString(key);

            config.put(key, val);
        }
        properties.putAll(config);
    }

//    static {
//        ResourceBundle rb = ResourceBundle.getBundle("config");
//        Enumeration<String> cfgs = rb.getKeys();
//        while (cfgs.hasMoreElements()) {
//            String key = cfgs.nextElement();
//            String val = rb.getString(key);
//
//            config.put(key, val);
//        }
//        properties.putAll(config);
//    }

    public static String get(String key) {
        return (String) config.get(key);
    }

    public static Object getObj(String key) {
        return config.get(key);
    }

    public static Boolean getToBool(String key, Boolean def) {
        try {
            return Boolean.valueOf((String) config.get(key));
        } catch (Exception e) {
            return def;
        }
    }

    public static Integer getToInteger(String key, Integer def) {
        try {
            return Integer.valueOf((String) config.get(key));
        } catch (Exception e) {
            return def;
        }
    }

    public static Long getToLong(String key, Long def) {
        try {
            return Long.valueOf((String) config.get(key));
        } catch (Exception e) {
            return def;
        }
    }
}