package com.example.testapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BabbyDao {
    @Insert
    void insert(BabbyEntity...babbyEntities);
    @Update
    void update(BabbyEntity...babbyEntities);
    @Delete
    void delete(BabbyEntity...babbyEntities);
    @Query("DELETE FROM BabbyEntity")
    void deleteAll();

    @Query("SELECT * FROM BabbyEntity ORDER BY ID DESC")
    List<BabbyEntity>getall();
}
