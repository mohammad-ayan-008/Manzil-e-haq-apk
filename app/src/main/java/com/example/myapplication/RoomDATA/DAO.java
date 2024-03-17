package com.example.myapplication.RoomDATA;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DAO {
    @Query("SELECT * FROM AyatData")
    public AyatData getData();

    @Insert
    public void add(AyatData data);
}
