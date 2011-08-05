package com.quintex.value;

import java.sql.Timestamp;

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
    
    public Timestamp getLatest() {
        return latest;
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
        return title;
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
