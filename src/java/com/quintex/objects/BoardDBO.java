package com.quintex.objects;

import com.quintex.helpers.Logger;
import com.quintex.value.BoardVO;
import com.quintex.value.TopicVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Steven Burgart
 */
public class BoardDBO extends DatabaseObject {

    public BoardVO get(int boardid) {
        String query = "SELECT * FROM board WHERE boardid=?";

        ArrayList<BoardVO> boards = parseBoards(select(query, boardid));
        
        if (boards.size() > 0)
            return boards.get(0);
        else
            return null;
    }

    public int add(String title, String description) {
        String query = "INSERT INTO board(title, description) VALUES(?, ?)";

        return update(query, title, description);
    }

    public ArrayList<BoardVO> getBoards() {
        String query = "SELECT * FROM board";

        return parseBoards(select(query));
    }

    public ArrayList<TopicVO> getTopics(int boardid) {
        String query = "SELECT topicid AS tid, username AS creator, title, (SELECT COUNT(*) FROM message WHERE topicid=tid) AS messages, (SELECT MAX(timestamp) FROM message WHERE topicid=tid) AS latest FROM topic NATURAL JOIN user WHERE boardid=?";

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
                topic.setTopicid(rs.getInt("tid"));
                topic.setTitle(rs.getString("title"));
                topic.setMessages(rs.getInt("messages"));
                topic.setCreator(rs.getString("creator"));
                topics.add(topic);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return topics;
    }
}
