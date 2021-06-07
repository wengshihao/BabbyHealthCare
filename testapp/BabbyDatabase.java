package com.example.testapp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {BabbyEntity.class},version = 2,exportSchema = false)
public abstract class BabbyDatabase extends RoomDatabase {
    public abstract BabbyDao getBabbyDao();
}
