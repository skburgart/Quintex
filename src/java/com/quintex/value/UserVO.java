package com.quintex.value;

import java.sql.Timestamp;

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
    private String flags;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password ) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature ) {
        this.signature = signature;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email ) {
        this.email = email;
    }

    public Timestamp getRegistered() {
        return registered;
    }

    public void setRegistered(Timestamp registered ) {
        this.registered = registered;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }
}
