package com.quintex.objects;

import com.quintex.helpers.Logger;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Steven Burgart
 */
public abstract class DatabaseObject {

    class NoSuchDBSelectType extends Error {

        NoSuchDBSelectType(String type) {
            super(type);
        }
    }
    private Connection conn = null;
    private static String dbUrl = "jdbc:mysql://localhost/quintex";
    private static String dbUser = "quintex";
    private static String dbPassword = "quintex123";

    protected DatabaseObject() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception exp) {
            Logger.logError(exp);
        }

    }

    private PreparedStatement prepare(String query, Object... args) throws SQLException {

        PreparedStatement stmt = conn.prepareStatement(query);
        int i = 1;

        for (Object o : args) {
            String type = o.getClass().getName();

            if (type.equals("java.lang.String")) {
                stmt.setString(i, (String) o);
            } else if (type.equals("java.lang.Integer")) {
                stmt.setInt(i, (Integer) o);
            } else {
                throw new NoSuchDBSelectType(type);
            }

            i += 1;
        }

        return stmt;
    }

    protected ResultSet select(String query, Object... args) {

        ResultSet rs = null;

        try {
            PreparedStatement stmt = prepare(query, args);

            rs = stmt.executeQuery();

        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return rs;
    }

    protected int update(String query, Object... args) {

        int result = 0;

        try {
            PreparedStatement stmt = prepare(query, args);

            result = stmt.executeUpdate();

        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return result;
    }
}
