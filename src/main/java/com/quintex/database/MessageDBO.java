package com.quintex.database;

import com.quintex.utility.Logger;
import com.quintex.value.MessageVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven Burgart
 */
public class MessageDBO extends DatabaseObject {

    public MessageDBO() {
        super();
    }

    public MessageDBO(Connection conn) {
        super(conn);
    }

    public ArrayList<MessageVO> getFromTopicid(int topicid) {
        String query = "SELECT userid, username, body, timestamp FROM message NATURAL JOIN user WHERE topicid=? ORDER BY timestamp ASC";

        return parseResultSet(select(query, topicid));
    }

    public int add(int topicid, int userid, String body) {
        String query = "INSERT INTO message(topicid, userid, body) VALUES(?, ?, ?)";

        return update(query, topicid, userid, body);
    }

    public int addWithTopic(int userid, String body) {
        int topicid = singleInt("SELECT LAST_INSERT_ID()");
        String query = "INSERT INTO message(topicid, userid, body) VALUES(?, ?, ?)";

        if (update(query, topicid, userid, body) > 0) {
            return topicid;
        }

        return 0;
    }

    public int delete(int messageid) {
        String query = "DELETE FROM message WHERE messageid=?";

        return update(query, messageid);
    }

    private ArrayList<MessageVO> parseResultSet(ResultSet rs) {
        ArrayList<MessageVO> messages = new ArrayList<MessageVO>();

        try {
            while (rs.next()) {
                MessageVO message = new MessageVO();

                try {message.setUserid(rs.getInt("userid"));} catch (SQLException e) {}
                try {message.setTimestamp(rs.getTimestamp("timestamp"));} catch (SQLException e) {}
                try {message.setBody(rs.getString("body"));} catch (SQLException e) {}
                try {message.setUsername(rs.getString("username"));} catch (SQLException e) {}

                messages.add(message);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return messages;
    }
}
