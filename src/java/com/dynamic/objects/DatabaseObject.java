package com.dynamic.objects;

/**
 *
 * @author steve
 */
import com.dynamic.helpers.Utility;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseObject {

    private Connection conn = null;
    
    private static String dbUrl = "jdbc:mysql://localhost/dynamic";
    private static String dbUser = "dynamic";
    private static String dbPassword = "dynamic123";

    public DatabaseObject() {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception exp) {
            Utility.logError(exp);
        }

    }

    public PreparedStatement prepare(String query) throws SQLException {
        return conn.prepareStatement(query);
    }
}
