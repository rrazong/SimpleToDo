package com.example.richellerazon.simpletodo;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by richellerazon on 10/4/16.
 */
public class ToDo extends SugarRecord {
    @Unique
    private String title;

    public ToDo() {
        this.title = null;
    }

    public ToDo(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
