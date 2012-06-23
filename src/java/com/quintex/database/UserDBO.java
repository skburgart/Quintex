package com.quintex.database;

import com.quintex.utility.Email;
import com.quintex.utility.Logger;
import com.quintex.utility.SHA;
import com.quintex.value.UserVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven Burgart
 */
public class UserDBO extends DatabaseObject {

    class NoSuchUser extends Error {

        NoSuchUser(int userid) {
            super(Integer.toString(userid));
        }
    }

    public int add(String username, String password, String email) {
        String query = "INSERT INTO user(username, password, email, lastaction) VALUES (?, ?, ?, NOW())";
        int result;

        if (getFromUsername(username) == null) {
            result = update(query, username, SHA.getSHAOne(password), email);
        } else {
            result = 2; // Username exists
        }

        return result;
    }

    public UserVO get(int userid) {

        String query = "SELECT *, (SELECT COUNT(*) FROM topic WHERE userid=u.userid) as topics, (SELECT COUNT(*) FROM message WHERE userid=u.userid) as messages FROM user AS u WHERE userid=?";
        ArrayList<UserVO> users = parseResultSet(select(query, userid));

        if (users.isEmpty()) {
            throw new NoSuchUser(userid);
        }

        return users.get(0);
    }

    public ArrayList<UserVO> getAll() {
        String query = "SELECT *, (SELECT COUNT(*) FROM topic WHERE userid=u.userid) as topics, (SELECT COUNT(*) FROM message WHERE userid=u.userid) as messages FROM user AS u";

        return parseResultSet(select(query));
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
                result = 3; // password incorrect
            }
        } else {
            result = 2; // invalid username
        }

        return result;
    }

    public int updateLastAction(int userid) {
        String query = "UPDATE user SET lastaction=NOW() WHERE userid=?";

        return update(query, userid);
    }

    public int resetPassword(String username) {
        int result;
        String query = "UPDATE user SET password=? WHERE username=?";
        String newPassword = generatePassword();

        UserVO user = getFromUsername(username);

        if (user != null) {
            result = update(query, SHA.getSHAOne(newPassword), username);
            if (result == 1) {
                Email.resetPassword(user.getEmail(), user.getUsername(), newPassword);
            }
        } else {
            result = 2; // invalid username
        }

        return result;
    }

    private String generatePassword() {
        int passowrdLength = 6;
        StringBuilder sb = new StringBuilder(passowrdLength);

        for (int i = 0; i < passowrdLength; i++) {
            int randomType = (int) (Math.random() * 3);
            char randomChar = '\0';
            switch (randomType) {
                case 0:
                    randomChar = (char) ('0' + (int) (Math.random() * 10));
                    break;
                case 1:
                    randomChar = (char) ('a' + (int) (Math.random() * 10));
                    break;
                case 2:
                    randomChar = (char) ('A' + (int) (Math.random() * 10));
                    break;
            }

            sb.append(randomChar);
        }

        return sb.toString();
    }

    public void logLogin(int userid, String ip) {

        String query = "INSERT INTO user_login_history(userid, ip) VALUES (?, ?)";
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
                tmp.setEmail(rs.getString("email"));
                tmp.setPassword(rs.getString("password"));
                tmp.setSignature(rs.getString("signature"));
                tmp.setRegistered(rs.getTimestamp("registered"));
                tmp.setLastAction(rs.getTimestamp("lastaction"));
                tmp.setFlags(rs.getString("flags"));
                tmp.setTopics(rs.getInt("topics"));
                tmp.setMessages(rs.getInt("messages"));

                users.add(tmp);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return users;
    }
}
