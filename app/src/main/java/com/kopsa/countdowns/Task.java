package com.kopsa.countdowns;

import java.util.Date;

/**
 * Created by kkopsa on 5/27/16.
 */
public class Task {
    private String mId;
    private String mDesc;
    private Date mDate;
    private boolean mCompleted;

    public Task(String mId, String mDesc, Date mDate, boolean mCompleted) {
        this.mId = mId;
        this.mDesc = mDesc;
        this.mDate = mDate;
        this.mCompleted = mCompleted;
    }
}
