package com.kopsa.countdowns;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by kkopsa on 2/3/18.
 */

public class Utilities {
    static public String getCalendarAsString(Calendar date) {
        String string = new String();

        Locale locale = Locale.getDefault();

        string += date.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
        string += ", " + date.get(Calendar.DAY_OF_MONTH);
        string += " " + date.get(Calendar.YEAR);
        string += ", " + date.get(Calendar.HOUR);
        string += ":" + date.get(Calendar.MINUTE);

        return string;
    }
}
