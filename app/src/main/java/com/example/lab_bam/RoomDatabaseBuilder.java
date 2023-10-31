package com.example.lab_bam;

import android.content.Context;

import androidx.room.Room;

import com.example.lab_bam.Repository.AppDb;

public class RoomDatabaseBuilder {

    private final static String DB_NAME = "log-cat-entry";
    public static AppDb buildInMemoryDb(Context context){
        return Room.databaseBuilder(context, AppDb.class, DB_NAME)
                .allowMainThreadQueries()
                .build();
    }
}
