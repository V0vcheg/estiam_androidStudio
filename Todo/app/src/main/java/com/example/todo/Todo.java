package com.example.todo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Todo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "urgency")
    private String urgency;

    public Todo() {
        name = "";
        urgency = "";
    }

    public String getName() {
        return this.name;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrgency() {
        return this.urgency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
}
