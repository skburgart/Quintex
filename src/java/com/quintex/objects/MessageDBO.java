package com.quintex.objects;

import com.quintex.helpers.Logger;


/**
 *
 * @author Steven Burgart
 */
public class MessageDBO extends DatabaseObject {

    public int add(int topicid, int userid, String body) {
        String query = "INSERT INTO message(topicid, userid, body) VALUES(?, ?, ?)";

        return update(query, topicid, userid, body);
    }

    public int delete(int messageid) {
        String query = "DELETE FROM message WHERE messageid=?";

        return update(query, messageid);
    }
}
