package com.quintex.database;

import com.quintex.utility.Logger;
import com.quintex.value.BoardVO;
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

        return parseBoard(select(query, boardid));
    }

    public int add(String title, String description) {
        String query = "INSERT INTO board(title, description) VALUES(?, ?)";

        return update(query, title, description);
    }

    public ArrayList<BoardVO> getAll() {
        String query = "SELECT boardid, timestamp, title, description, (SELECT COUNT(*) FROM topic WHERE boardid=b.boardid) AS topics,(SELECT COUNT(*) FROM message WHERE topicid IN (SELECT topicid FROM topic WHERE boardid=b.boardid)) AS messages FROM board AS b";

        return parseResultSet(select(query));
    }

    public int delete(int boardid) {
        String query = "DELETE FROM board WHERE boardid=?";

        return update(query, boardid);
    }

    private BoardVO parseBoard(ResultSet rs) {
        BoardVO board = new BoardVO();

        try {
            if (rs.first()) {
                board.setTitle(rs.getString("title"));
                board.setBoardid(rs.getInt("boardid"));
            } else {
                board = null;
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return board;
    }

    private ArrayList<BoardVO> parseResultSet(ResultSet rs) {
        ArrayList<BoardVO> boards = new ArrayList<BoardVO>();

        try {
            while (rs.next()) {
                BoardVO board = new BoardVO();

                board.setBoardid(rs.getInt("boardid"));
                board.setTimestamp(rs.getTimestamp("timestamp"));
                board.setTitle(rs.getString("title"));
                board.setDescription(rs.getString("description"));
                board.setTopics(rs.getInt("topics"));
                board.setMessages(rs.getInt("messages"));

                boards.add(board);
            }
        } catch (SQLException exp) {
            Logger.logError(exp);
        }

        return boards;
    }
}
