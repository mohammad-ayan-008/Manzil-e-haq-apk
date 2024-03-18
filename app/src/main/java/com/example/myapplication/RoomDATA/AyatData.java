package com.example.myapplication.RoomDATA;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class AyatData {


    @PrimaryKey(autoGenerate = true)
    public int id;
    public void setAyat_no(int ayat_no) {
        this.ayat_no = ayat_no;
    }
    public int getAyat_no() {
        return ayat_no;
    }

    @ColumnInfo(name = "ayat number")
    public  int ayat_no;



    public AyatData(int ayat_no) {
        this.ayat_no = ayat_no;
    }
}
