package com.example.moec.Room_database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_Text")
public class database_module {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "searchtext")
    String text;

    public database_module( String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
