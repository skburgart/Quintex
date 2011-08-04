package com.quintex.objects;

import com.quintex.helpers.Logger;
import com.quintex.value.TopicVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven Burgart
 */
public class BoardDBO extends DatabaseObject {

    public int add(String title, String description) {
        String query = "INSERT INTO board(title, description) VALUES(?, ?)";

        return update(query, title, description);
    }

    public ArrayList<TopicVO> getTopics(int boardid) {
        String query = "SELECT * FROM topic WHERE boardid=?";

        return parseTopics(select(query, boardid));
    }

    public int numTopics(int boardid) {
        String query = "SELECT count(*) FROM topic WHERE boardid=?";

        return count(query, boardid);
    }

    public int numMessages(int boardid) {
        int messages = 0;
        TopicDBO tdbo = new TopicDBO();
        ArrayList<TopicVO> topics = getTopics(boardid);

        for (TopicVO topic : topics) {
            messages += tdbo.numMessages(topic.getTopicid());
        }

        return messages;
    }

    public int delete(int boardid) {
        String query = "DELETE FROM board WHERE boardid=?";

        return update(query, boardid);
    }

    private ArrayList<TopicVO> parseTopics(ResultSet rs) {
        ArrayList<TopicVO> topics = new ArrayList<TopicVO>();

        try {
            while (rs.next()) {
                TopicVO topic = new TopicVO();
                topic.setTopicid(rs.getInt("topicid"));
                topic.setBoardid(rs.getInt("boardid"));
                topic.setUserid(rs.getInt("userid"));
                topic.setTimestamp(rs.getTimestamp("timestamp"));
                topic.setTitle(rs.getString("title"));
                topics.add(topic);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return topics;
    }
}
