package com.kopsa.countdowns;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by kkopsa on 5/27/16.
 */
public class Task {
    private String mDesc;
    private Calendar mDate;

    public Task(String mDesc, Calendar mDate) {
        this.mDesc = mDesc;
        this.mDate = mDate;
    }

    public String getDesc() {
        return mDesc;
    }

    public Calendar getDate() {
        return mDate;
    }
}
