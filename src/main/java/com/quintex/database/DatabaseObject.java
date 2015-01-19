package com.quintex.database;

import com.quintex.utility.Logger;
import java.sql.*;

/**
 *
 * @author Steven Burgart
 */
public abstract class DatabaseObject {

    protected Connection conn = null;

    class NoSuchDBSelectType extends Error {

        NoSuchDBSelectType(String type) {
            super(type);
        }
    }

    protected DatabaseObject() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    DBProperties.getProperty("dbUrl"),
                    DBProperties.getProperty("dbUser"),
                    DBProperties.getProperty("dbPassword"));
        } catch (Exception exp) {
            Logger.logError(exp);
        }
    }

    protected DatabaseObject(Connection conn) {
        this.conn = conn;
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

    protected int singleInt(String query, Object... args) {

        int count = 0;

        ResultSet rs = select(query, args);
        try {
            if (rs.first()) {
                count = rs.getInt(1);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return count;
    }
}
