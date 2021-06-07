package com.example.testapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//PH正常6
//尿尿次数7
//白细胞正常3
//红细胞正常4
//尿蛋白正常4
@Entity
public class BabbyEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo()
    private String username;
    @ColumnInfo()
    private String password;
    @ColumnInfo()
    private float PH;
    @ColumnInfo()
    private float niaoniaotimes;
    @ColumnInfo()
    private float whiteCells;
    @ColumnInfo()
    private float redCells;
    @ColumnInfo()
    private float eggCells;

    public BabbyEntity(String username, String password, float PH, float niaoniaotimes, float whiteCells, float redCells, float eggCells) {
        this.username = username;
        this.password = password;
        this.PH = PH;
        this.niaoniaotimes = niaoniaotimes;
        this.whiteCells = whiteCells;
        this.redCells = redCells;
        this.eggCells = eggCells;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getPH() {
        return PH;
    }

    public void setPH(float PH) {
        this.PH = PH;
    }

    public float getNiaoniaotimes() {
        return niaoniaotimes;
    }

    public void setNiaoniaotimes(float niaoniaotimes) {
        this.niaoniaotimes = niaoniaotimes;
    }

    public float getWhiteCells() {
        return whiteCells;
    }

    public void setWhiteCells(float whiteCells) {
        this.whiteCells = whiteCells;
    }

    public float getRedCells() {
        return redCells;
    }

    public void setRedCells(float redCells) {
        this.redCells = redCells;
    }

    public float getEggCells() {
        return eggCells;
    }

    public void setEggCells(float eggCells) {
        this.eggCells = eggCells;
    }
}
