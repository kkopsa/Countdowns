package com.kopsa.countdowns.util;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by kkopsa on 2/3/18.
 */

public class Utilities {
    public static String getCalendarAsString(Calendar date) {
        String string = new String();

        Locale locale = Locale.getDefault();

        string += date.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
        string += ", " + date.get(Calendar.DAY_OF_MONTH);
        string += " " + date.get(Calendar.YEAR);
        string += ", " + date.get(Calendar.HOUR);
        string += ":" + date.get(Calendar.MINUTE);

        return string;
    }

    public static String getTimeRemaining(Calendar dueDate) {
        Calendar rightNow = Calendar.getInstance();
        long timeRemaining = dueDate.getTimeInMillis() - rightNow.getTimeInMillis();
        long days = timeRemaining / (1000 * 60 * 60 * 24);
        timeRemaining -= 1000 * 60 * 60 * 24 * days;
        long hours = timeRemaining / (1000 * 60 * 60);
        timeRemaining -= 1000 * 60 * 60 * hours;
        long minutes = timeRemaining / (1000 * 60);
        timeRemaining -= 1000 * 60 * minutes;
        long seconds = timeRemaining / 1000;

        return String.valueOf(days) + " days " + String.valueOf(hours) + " hours "
                + String.valueOf(minutes) + " minutes " + String.valueOf(seconds) + " seconds";
    }
}
