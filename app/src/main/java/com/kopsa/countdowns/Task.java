package com.kopsa.countdowns;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by kkopsa on 5/27/16.
 */
public class Task implements Comparable<Task> {
    private String mDescription;
    private Calendar mDueDate;

    public Task(String description, Calendar dueDate) {
        this.mDescription = description;

        if (dueDate != null) {
            this.mDueDate = dueDate;
        }
        else {
            this.mDueDate = Calendar.getInstance();
        }
    }

//    public Task(Calendar dueDate) {
//        this.mDescription = "";
//        this.mDueDate = dueDate
//    }
//
//    public Task(String mDesc) {
//        this.mDescription = mDesc;
//        this.mDueDate = Calendar.getInstance();
//    }
//
//    public Task() {
//        this.mDueDate = Calendar.getInstance();
//        this.mDescription = "";
//    }

    public String getDescription() {
        return mDescription;
    }

    public Calendar getDueDate() { return mDueDate; }

    public String getDueDateAsString() {
        String dueDate = new String();

        Locale locale = Locale.getDefault();

        dueDate += mDueDate.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
        dueDate += ", " + mDueDate.get(Calendar.DAY_OF_MONTH);
        dueDate += " " + mDueDate.get(Calendar.YEAR);
        dueDate += ", " + mDueDate.get(Calendar.HOUR);
        dueDate += ":" + mDueDate.get(Calendar.MINUTE);

        return dueDate;
    }

    @Override
    public int compareTo(Task another) {
        if (mDueDate.getTimeInMillis() > another.getDueDate().getTimeInMillis())
            return 1;
        else
            return -1;
    }
}
