package com.example.richellerazon.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = TodoDatabase.class)
public class Item extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String title;

    public Item() {}

    public Item(String title) {
        this.title = title;
    }
}
