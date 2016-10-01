package com.example.richellerazon.simpletodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by richellerazon on 9/30/16.
 */

public class TodoItemDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ToDo.db";
    private static final int DATABASE_VERSION = 1;

    public TodoItemDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // These is where we need to write create table statements.
    // This is called when database is created.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL for creating the tables
        db.execSQL("CREATE TABLE ToDo (title TEXT PRIMARY KEY, priority TEXT)");
    }

    // This method is called when database is upgraded like
    // modifying the table structure,
    // adding constraints to database, etc
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        // SQL for upgrading the tables
        db.execSQL("DROP TABLE IF EXISTS ToDo");
        onCreate(db);
    }
}
