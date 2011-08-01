package com.quintex.objects;

import java.sql.Timestamp;

/**
 *
 * @author steve
 */
public class UserVO {

    public int userid;
    public String username;
    public String password;
    public Timestamp registered;

    public void print() {
        System.out.println("Userid: " + userid);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Registered: " + registered);
    }
}
