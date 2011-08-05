package com.quintex.objects;

import com.quintex.helpers.Logger;
import com.quintex.value.MessageVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven Burgart
 */
public class TopicDBO extends DatabaseObject {

    public int create(int boardid, int userid, String title, String body) {
        MessageDBO mdbo = new MessageDBO(this.conn);
        int result = add(boardid, userid, title);

        if (result > 0) {
            result = mdbo.addWithTopic(userid, body);
        }

        return result;
    }

    private int add(int boardid, int userid, String title) {
        String query = "INSERT INTO topic(boardid, userid, title) VALUES(?, ?, ?)";

        return update(query, boardid, userid, title);
    }

    public ArrayList<MessageVO> getMessages(int topicid) {
        String query = "SELECT * FROM messages WHERE topicid=?";

        return parseMessages(select(query, topicid));
    }

    public int numMessages(int topicid) {
        String query = "SELECT count(*) FROM message WHERE topicid=?";

        return count(query, topicid);
    }

    public int delete(int topicid) {
        String query = "DELETE FROM topic WHERE topicid=?";

        return update(query, topicid);
    }

    private ArrayList<MessageVO> parseMessages(ResultSet rs) {
        ArrayList<MessageVO> messages = new ArrayList<MessageVO>();

        try {
            while (rs.next()) {
                MessageVO message = new MessageVO();
                message.setMessageid(rs.getInt("messageid"));
                message.setTopicid(rs.getInt("topicid"));
                message.setUserid(rs.getInt("userid"));
                message.setTimestamp(rs.getTimestamp("timestamp"));
                message.setBody(rs.getString("body"));
                messages.add(message);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return messages;
    }
}
