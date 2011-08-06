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

        return update(query, topicid, userid, filterBody(body));
    }

    public int addWithTopic(int userid, String body) {
        int topicid = aggregate("SELECT LAST_INSERT_ID()");
        String query = "INSERT INTO message(topicid, userid, body) VALUES(?, ?, ?)";

        if (update(query, topicid, userid, filterBody(body)) > 0) {
            return topicid;
        }

        return 0;
    }
    
    private String filterBody(String body) {
        body = body.replace("<", "&lt;");
        body = body.replace(">", "&gt;");
        body = body.replace("\n", "<br />");
        
        return body;
    }

    public int delete(int messageid) {
        String query = "DELETE FROM message WHERE messageid=?";

        return update(query, messageid);
    }
}
