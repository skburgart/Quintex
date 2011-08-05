package com.quintex.objects;

import java.sql.Connection;

/**
 *
 * @author Steven Burgart
 */
public class MessageDBO extends DatabaseObject {
    
    MessageDBO() {
        super();
    }
    
    MessageDBO(Connection conn) {
        super(conn);
    }
    
    public int add(int topicid, int userid, String body) {
        String query = "INSERT INTO message(topicid, userid, body) VALUES(?, ?, ?)";

        return update(query, topicid, userid, body);
    }
    
    public int addWithTopic(int userid, String body) {
        String query = "INSERT INTO message(topicid, userid, body) VALUES(LAST_INSERT_ID(), ?, ?)";

        return update(query, userid, body);
    }

    public int delete(int messageid) {
        String query = "DELETE FROM message WHERE messageid=?";

        return update(query, messageid);
    }

}
