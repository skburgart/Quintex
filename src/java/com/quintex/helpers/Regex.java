package com.quintex.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author steve
 */
public class Regex {

    public static final String email = "^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$";
    public static final String username = "^[A-Za-z]\\w+$";

    public static boolean match(String pattern, String source) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.matches();
    }

    public static boolean find(String pattern, String source) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    public static void main(String[] args) {
        if (Regex.match(Regex.username, "S5teve")) {
            System.out.println("Match");
        } else {
            System.out.println("No match");
        }
    }
}
