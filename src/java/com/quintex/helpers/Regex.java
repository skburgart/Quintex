package com.quintex.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Steven Burgart
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
}
