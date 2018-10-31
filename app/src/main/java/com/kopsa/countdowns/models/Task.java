package com.kopsa.countdowns.models;

import com.kopsa.countdowns.util.Utilities;

import java.util.Calendar;

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
        } else {
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

    public Calendar getDueDate() {
        return mDueDate;
    }

    public String getDueDateAsString() {
        return Utilities.getCalendarAsString(mDueDate);
    }

    public long getTimeRemaining() {
        long timeRemaining = 0;
        timeRemaining = mDueDate.getTimeInMillis() - System.currentTimeMillis();
        return timeRemaining;
    }

    @Override
    public int compareTo(Task another) {
        if (mDueDate.getTimeInMillis() > another.getDueDate().getTimeInMillis())
            return 1;
        else
            return -1;
    }

    public String getFormattedTimeRemaining() {
        String formattedTimeRemaining = new String();

        formattedTimeRemaining += this.getTimeRemaining();

        return formattedTimeRemaining;
    }
}
