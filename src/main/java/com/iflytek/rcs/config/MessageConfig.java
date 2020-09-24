package com.iflytek.rcs.config;


import com.iflytek.rcs.utils.PropertiesUtil;

import java.io.IOException;
import java.util.Properties;

/**
 * @author
 * @Description 读取配置
 */
public class MessageConfig {

    private static MessageConfig INSTANCE = new MessageConfig();
    private Properties properties;
    private static final String RESOURCEPATH = "config/application.properties";

    public MessageConfig() {
        try {
            ClassLoader classLoader = Class.forName("com.iflytek.rcs.config.MessageConfig").getClassLoader();
            this.properties = PropertiesUtil.loadAllProperties(RESOURCEPATH, classLoader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static MessageConfig getInstance() {
        return INSTANCE;
    }

    public String getProperty(String key) {
        String property = this.properties.getProperty(key);

        int index = property.indexOf("${");

        while (index !=-1) {
            int end = property.indexOf("}", index);
            String prop = property.substring(index + 2, end);
            property = property.replace(String.format("${%s}", prop), this.getProperty(prop));
            index = property.indexOf("${", index);
        }
        return property;
    }
}
