package com.example.testapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CircleDao {
    @Insert
    void insert(CircleEntity... circleEntities);
    @Update
    void update(CircleEntity... circleEntities);
    @Delete
    void delete(CircleEntity... circleEntities);
    @Query("DELETE FROM CircleEntity")
    void deleteAllCircleEntity();
    @Query("SELECT * FROM CircleEntity ORDER BY ID DESC")
    LiveData<List<CircleEntity>> getAllCircleEntity();
    @Query("SELECT COUNT(*) FROM CircleEntity")
    int getCountCircleEntity();
}