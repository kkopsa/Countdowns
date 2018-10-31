package com.kopsa.countdowns.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kkopsa on 8/27/16.
 */
public class TasksDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "ChecklistTasks.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TasksContract.Task.TABLE_NAME + " (" +
                    TasksContract.Task._ID + INT_TYPE + " PRIMARY KEY," +
                    TasksContract.Task.COLUMN_NAME_DESC + TEXT_TYPE + COMMA_SEP +
                    TasksContract.Task.COLUMN_NAME_CREATE_DATE + INT_TYPE + COMMA_SEP +
                    TasksContract.Task.COLUMN_NAME_DUE_DATE + INT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TasksContract.Task.TABLE_NAME;

    public TasksDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public TasksDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: determine what should be done onUpgrade()
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
