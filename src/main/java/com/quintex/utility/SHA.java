package com.quintex.utility;

import java.security.MessageDigest;
import java.util.Formatter;

/**
 *
 * @author Steven Burgart
 */
public class SHA {

    public static String getSHAOne(String toEncode) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (Exception exp) {
            Logger.logError(exp);
        }

        return byteToBase16(md.digest(toEncode.getBytes()));
    }

    public static String getSHA256(String toEncode) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception exp) {
            Logger.logError(exp);
        }

        return byteToBase16(md.digest(toEncode.getBytes()));
    }

    private static String byteToBase16(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
