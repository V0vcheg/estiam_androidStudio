package com.example.todo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Ignore;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "todo_db";

    public static TodoDatabase getDbInstance(Context context) {
        return Room.databaseBuilder(context, TodoDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public abstract TodoDAO getTodoDAO();

}
