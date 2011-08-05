package com.quintex.objects;

import com.quintex.helpers.Logger;
import com.quintex.value.BoardVO;
import com.quintex.value.TopicVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    public ArrayList<BoardVO> getBoards() {
        String query = "SELECT * FROM board";

        return parseBoards(select(query));
    }

    public ArrayList<TopicVO> getTopics(int boardid) {
        String query = "SELECT * FROM topic WHERE boardid=?";

        return parseTopics(select(query, boardid));
    }

    private int numTopics(int boardid) {
        String query = "SELECT count(*) FROM topic WHERE boardid=?";

        return aggregate(query, boardid);
    }

    private int numMessages(int boardid) {
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

    private ArrayList<BoardVO> parseBoards(ResultSet rs) {
        ArrayList<BoardVO> boards = new ArrayList<BoardVO>();

        try {
            while (rs.next()) {
                BoardVO board = new BoardVO();
                board.setBoardid(rs.getInt("boardid"));
                board.setTimestamp(rs.getTimestamp("timestamp"));
                board.setTitle(rs.getString("title"));
                board.setDescription(rs.getString("description"));
                board.setTopics(numTopics(board.getBoardid()));
                board.setMessages(numMessages(board.getBoardid()));
                boards.add(board);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return boards;
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
