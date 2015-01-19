package com.quintex.value;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Steven Burgart
 */
public class MessageVO {

    private int messageid;
    private int topicid;
    private int userid;
    private Timestamp timestamp;
    private String username;
    private String body;

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTimestamp() {
        return new SimpleDateFormat("d/M/yyyy h:mma z").format(timestamp);
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return filterBody(body);
    }

    private String filterBody(String body) {
        body = body.replace("<", "&lt;");
        body = body.replace(">", "&gt;");
        body = body.replace("\n", "<br />");

        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
