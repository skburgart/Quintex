package com.quintex.objects;

import com.quintex.helpers.SHA;
import com.quintex.helpers.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author steve
 */
public class UserDBO extends DatabaseObject {

    class NoSuchUser extends Error {

        NoSuchUser() {
            super();
        }
    }

    public int add(String username, String password) {
        int result = 0;
        PreparedStatement stmt = null;

        if (getFromUsername(username) == null) {
            try {
                stmt = prepare("INSERT INTO user(username, password, registered) VALUES (?, ?, NOW())");

                stmt.setString(1, username);
                stmt.setString(2, SHA.getSHAOne(password));

                result = stmt.executeUpdate();
            } catch (SQLException exp) {
                Logger.logError(exp);
            }
        } else {
            result = 2;
        }

        return result;
    }

    public UserVO get(int userid) {

        ArrayList<UserVO> users = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = prepare("SELECT * FROM user WHERE userid=?");
            stmt.setInt(1, userid);
            rs = stmt.executeQuery();
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        users = parseResultSet(rs);

        if (users.isEmpty()) {
            throw new NoSuchUser();
        }

        return users.get(0);
    }

    public UserVO getFromUsername(String username) {

        ArrayList<UserVO> users = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = prepare("SELECT * FROM user WHERE username=?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        users = parseResultSet(rs);

        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public int validate(String username, String password) {
        int result;

        UserVO user = getFromUsername(username);

        if (user != null) {
            if (SHA.getSHAOne(password).equals(user.password)) {
                result = 1; // valid login
            } else {
                result = 2; // password incorrect
            }
        } else {
            result = 0; // invalid username
        }

        return result;
    }

    private ArrayList<UserVO> parseResultSet(ResultSet rs) {
        ArrayList<UserVO> users = new ArrayList<UserVO>();

        try {
            while (rs.next()) {
                UserVO tmp = new UserVO();

                tmp.userid = rs.getInt("userid");
                tmp.username = rs.getString("username");
                tmp.password = rs.getString("password");
                tmp.registered = rs.getTimestamp("registered");

                users.add(tmp);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return users;
    }

}
