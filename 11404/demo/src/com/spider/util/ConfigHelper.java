package com.spider.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: demo
 * Date: 13-12-1
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
public class ConfigHelper {
    private static Logger logger = Logger.getLogger(ConfigHelper.class);
    private static Properties property = null;

    public ConfigHelper() {
    }

    /**
     * 获取参数
     *
     * @param key --参数的配置文件
     */
    public static String getParamValue(String key) {
        if (property == null) {
            InputStream inputStream = null;
            try {
                property = new Properties();
                inputStream = ConfigHelper.class
                        .getResourceAsStream("/system.properties");
                property.load(inputStream);

                //
                inputStream.close();
                inputStream = null;
            } catch (FileNotFoundException e1) {
                logger.error(e1);
            } catch (IOException e) {
                logger.error(e);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close(); 	
                    } catch (IOException e) {
                        logger.error(e);
                    }
                    inputStream = null;
                }
            }
        }

        return property.getProperty(key);
    }

    public static String getPath(String path) {
        String[] str = path.split("/");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i] + File.separator);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}

