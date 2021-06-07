package com.example.testapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CircleEntity.class}, version = 8, exportSchema = false)
public abstract class CircleDatabase extends RoomDatabase{

    private static CircleDatabase INSTANCE;
    static CircleDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CircleDatabase.class,"circle_database")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public abstract CircleDao getCircleDao();



}
