package com.dynamic.db;

/**
 *
 * @author steve
 */
import com.dynamic.helpers.Utility;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseObject {

    private Connection connect = null;
    private static String dbUrl = "jdbc:mysql://localhost/dynamic";
    private static String dbUser = "dynamic";
    private static String dbPassword = "dynamic123";

    public DatabaseObject() {
        try {
            connect = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

        } catch (Exception exp) {
            Utility.logError(exp);
        } finally {
            closeConnection();
        }

    }

    private void closeConnection() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (Exception exp) {
            Utility.logError(exp);
        }
    }
}
