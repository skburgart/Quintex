/**
 * @author steve
 */
package com.dynamic.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        boolean stackTrace = true;

        log("ERROR: " + exp.getMessage());

        if (stackTrace) {
            exp.printStackTrace();
        }
    }
}
