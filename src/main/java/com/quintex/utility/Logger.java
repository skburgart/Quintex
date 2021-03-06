package com.quintex.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Steven Burgart
 */
public class Logger {

    public static void log(String message) {
        boolean debugging = true;

        if (debugging) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z");
            String dateTime = sdf.format(cal.getTime());
            System.out.println("[" + dateTime + "] " + message);
        }
    }

    public static void logError(Exception exp) {
        log("ERROR: " + exp.getMessage());

        exp.printStackTrace();
    }
}
