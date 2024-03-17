package com.example.myapplication.RoomDATA;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {AyatData.class},version = 1,exportSchema = false)
public abstract class Databasee extends RoomDatabase {
    public static Databasee data;

    public static  synchronized Databasee getDatabasee(Context x){
        data = (Databasee) Room.databaseBuilder(x,Databasee.class,"ayatdatabase").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        return  data;
    }
    public abstract  DAO userdao();

}
