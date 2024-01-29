package com.example.todo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDAO {
    @Query("SELECT * FROM Todo where id = :id")
    public Todo findById(long id);

    @Query("SELECT * FROM Todo")
    public List<Todo> findAll();

    @Insert
    public void add(Todo todo);

    @Update
    public void update(Todo todo);

    @Delete
    public void delete(Todo todo);
}
