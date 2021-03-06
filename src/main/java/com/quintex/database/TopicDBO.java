package com.quintex.database;

import com.quintex.utility.Logger;
import com.quintex.value.TopicVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven Burgart
 */
public class TopicDBO extends DatabaseObject {

    public TopicVO get(int topicid) {
        String query = "SELECT * FROM topic WHERE topicid=?";

        ArrayList<TopicVO> topics = parseResultSet(select(query, topicid));

        if (!topics.isEmpty()) {
            return topics.get(0);
        }

        return null;
    }

    public ArrayList<TopicVO> getFromBoardid(int boardid) {
        String query = "SELECT topicid, userid, username AS creator, title, (SELECT COUNT(*) FROM message WHERE topicid=t.topicid) AS messages, (SELECT MAX(timestamp) FROM message WHERE topicid=t.topicid) AS latest FROM topic AS t NATURAL JOIN user WHERE boardid=? ORDER BY latest DESC";

        return parseResultSet(select(query, boardid));
    }

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

    public int delete(int topicid) {
        String query = "DELETE FROM topic WHERE topicid=?";

        return update(query, topicid);
    }

    private ArrayList<TopicVO> parseResultSet(ResultSet rs) {
        ArrayList<TopicVO> topics = new ArrayList<TopicVO>();

        try {
            while (rs.next()) {
                TopicVO topic = new TopicVO();

                try {topic.setTopicid(rs.getInt("topicid"));} catch (SQLException e) {}
                try {topic.setBoardid(rs.getInt("boardid"));} catch (SQLException e) {}
                try {topic.setUserid(rs.getInt("userid"));} catch (SQLException e) {}
                try {topic.setTitle(rs.getString("title"));} catch (SQLException e) {}
                try {topic.setTimestamp(rs.getTimestamp("timestamp"));} catch (SQLException e) {}
                try {topic.setLatest(rs.getTimestamp("latest"));} catch (SQLException e) {}
                try {topic.setTitle(rs.getString("title"));} catch (SQLException e) {}
                try {topic.setCreator(rs.getString("creator"));} catch (SQLException e) {}
                try {topic.setMessages(rs.getInt("messages"));} catch (SQLException e) {}

                topics.add(topic);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return topics;
    }
}
