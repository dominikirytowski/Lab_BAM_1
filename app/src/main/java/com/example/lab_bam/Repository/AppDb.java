package com.example.lab_bam.Repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab_bam.Entity.LogCatEntry;

@Database(entities = {LogCatEntry.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
    public abstract LogCatEntryRepo logCatEntryRepo();
}
