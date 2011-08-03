package com.quintex.objects;

import com.quintex.helpers.SHA;
import com.quintex.helpers.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author steve
 */
public class UserDBO extends DatabaseObject {

    class NoSuchUser extends Error {

        NoSuchUser(int userid) {
            super(Integer.toString(userid));
        }
    }

    public int add(String username, String password) {
        String query = "INSERT INTO user(username, password, registered) VALUES (?, ?, NOW())";
        int result = 0;

        if (getFromUsername(username) == null) {
            result = update(query, username, SHA.getSHAOne(password));
        } else {
            result = 2; // Username exists
        }

        return result;
    }

    public UserVO get(int userid) {

        String query = "SELECT * FROM user WHERE userid=?";
        ArrayList<UserVO> users = parseResultSet(select(query, userid));

        if (users.isEmpty()) {
            throw new NoSuchUser(userid);
        }

        return users.get(0);
    }

    public UserVO getFromUsername(String username) {

        String query = "SELECT * FROM user WHERE username=?";
        ArrayList<UserVO> users = parseResultSet(select(query, username));

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
            if (SHA.getSHAOne(password).equals(user.getPassword())) {
                result = 1; // valid login
            } else {
                result = 2; // password incorrect
            }
        } else {
            result = 0; // invalid username
        }

        return result;
    }

    public void logLogin(int userid, String ip) {

        String query = "INSERT INTO user_login_history(userid, ip, timestamp) VALUES (?, ?, NOW())";
        update(query, userid, ip);

    }

    public boolean isAdmin(int userid) {
        return getFlags(userid).contains("a");
    }

    private String getFlags(int userid) {
        UserVO user = get(userid);

        return user.getFlags();
    }

    private ArrayList<UserVO> parseResultSet(ResultSet rs) {
        ArrayList<UserVO> users = new ArrayList<UserVO>();

        try {
            while (rs.next()) {
                UserVO tmp = new UserVO();

                tmp.setUserid(rs.getInt("userid"));
                tmp.setUsername(rs.getString("username"));
                tmp.setPassword(rs.getString("password"));
                tmp.setRegistered(rs.getTimestamp("registered"));
                tmp.setFlags(rs.getString("flags"));

                users.add(tmp);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return users;
    }
}
