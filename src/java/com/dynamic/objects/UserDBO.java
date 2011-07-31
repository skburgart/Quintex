package com.dynamic.objects;

import com.dynamic.helpers.Utility;
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

    public int add(UserVO user) {
        int result = 0;
        PreparedStatement stmt = null;

        try {
            stmt = prepare("INSERT INTO user(username, password, registered) VALUES (?, ?, NOW())");

            stmt.setString(1, user.username);
            stmt.setString(2, user.password);

            stmt.executeUpdate();
        } catch (SQLException exp) {
            Utility.logError(exp);
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
            Utility.logError(exp);
        }

        users = parseResultSet(rs);

        if (users.isEmpty()) {
            throw new NoSuchUser();
        }

        return users.get(0);
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
            Utility.logError(exp);
        }

        return users;
    }

    public static void main(String[] args) {
        UserDBO udbo = new UserDBO();
        UserVO user = new UserVO();

        user.username = "Bob";
        user.password = "secret!";

        udbo.add(user);
    }
}
