package com.quintex.objects;

/**
 *
 * @author steve
 */
import com.quintex.helpers.Logger;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseObject {

    private Connection conn = null;

    private static String dbUrl = "jdbc:mysql://localhost/quintex";
    private static String dbUser = "quintex";
    private static String dbPassword = "quintex123";

    public DatabaseObject() {
        try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception exp) {
            Logger.logError(exp);
        }

    }

    public PreparedStatement prepare(String query) throws SQLException {
        return conn.prepareStatement(query);
    }
}
