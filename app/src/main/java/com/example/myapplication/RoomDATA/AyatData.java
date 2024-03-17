package com.example.myapplication.RoomDATA;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AyatData {
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
