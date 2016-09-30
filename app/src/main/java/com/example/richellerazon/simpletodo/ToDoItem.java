package com.example.richellerazon.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

@Table(name = "Items")
public class ToDoItem extends Model implements Serializable{

    @Column(name = "Description")
    public String description;

    public String getDescription() {
        return this.description;
    }

    public ToDoItem(String description) {
        super();

        this.description = description;
    }
}
