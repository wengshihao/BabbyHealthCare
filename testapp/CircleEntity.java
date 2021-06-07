package com.example.testapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CircleEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "text")
    private String text;

    private int circleFlag;


    public CircleEntity(String text, int circleFlag) {
        this.text = text;
        this.circleFlag = circleFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCircleFlag() {
        return circleFlag;
    }

    public void setCircleFlag(int circleFlag) {
        this.circleFlag = circleFlag;
    }
}
