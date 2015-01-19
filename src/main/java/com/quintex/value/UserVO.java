package com.quintex.value;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Steven Burgart
 */
public class UserVO {

    private int userid;
    private String username;
    private String email;
    private String password;
    private String signature;
    private Timestamp registered;
    private Timestamp lastAction;
    private String flags;
    private int topics;
    private int messages;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        if (signature == null) {
            return "";
        }

        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistered() {
        return new SimpleDateFormat("dd/MM/yyyy hh:mma z").format(registered);
    }

    public void setRegistered(Timestamp registered) {
        this.registered = registered;
    }

    public String getLastAction() {
        return new SimpleDateFormat("dd/MM/yyyy hh:mma z").format(lastAction);
    }

    public void setLastAction(Timestamp lastAction) {
        this.lastAction = lastAction;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }
}
