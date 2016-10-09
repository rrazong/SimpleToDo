package com.example.richellerazon.simpletodo;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by richellerazon on 10/5/16.
 */
@Database(name = TodoDatabase.NAME, version = TodoDatabase.VERSION)
public class TodoDatabase {
    public static final String NAME = "TodoDatabase";
    public static final int VERSION = 4;
}
