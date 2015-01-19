package com.quintex.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Steve
 */
public class DBProperties {

    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream("database.properties"));
        } catch (IOException ex) {
            prop.setProperty("dbUrl", "jdbc:mysql://localhost/quintex");
            prop.setProperty("dbUser", "quintex");
            prop.setProperty("dbPassword", "quintex123");
            try {
                prop.store(new FileOutputStream("database.properties"), "Database Properties");
            } catch (IOException ex1) {
                Logger.getLogger(DBProperties.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
