package com.quintex.value;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Steven Burgart
 */
public class TopicVO {

    private int topicid;
    private int boardid;
    private int userid;
    private Timestamp timestamp;
    private Timestamp latest;
    private String title;
    private String creator;
    private int messages;

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getLatest() {
        if (latest != null) {
            return new SimpleDateFormat("d/M/yyyy h:mma z").format(latest);
        }

        return "no posts";
    }

    public void setLatest(Timestamp latest) {
        this.latest = latest;
    }

    public Timestamp getTimestamp() {
        return latest;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return filterTitle(title);
    }

    private String filterTitle(String body) {
        body = body.replace("<", "&lt;");
        body = body.replace(">", "&gt;");

        return body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }
}
