package com.kopsa.countdowns;

import android.provider.BaseColumns;

/**
 * Created by kkopsa on 8/27/16.
 */
public class TasksContract {

    private TasksContract() {}

    public static class Task implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_DESC = "desc";
        public static final String COLUMN_NAME_CREATE_DATE = "create_date";
        public static final String COLUMN_NAME_DUE_DATE = "due_date";
    }
}
