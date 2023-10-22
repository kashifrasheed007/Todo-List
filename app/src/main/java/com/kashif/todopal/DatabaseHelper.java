package com.kashif.todopal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TASK_TABLE_COL = "TASK_TABLE";
    public static final String TASKS_COL = "TASKS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "task.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String taskQuery = "CREATE TABLE " + TASK_TABLE_COL + " (" + TASKS_COL + " TEXT)";
        db.execSQL(taskQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
