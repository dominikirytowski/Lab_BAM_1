package com.example.lab_bam.Repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lab_bam.Entity.LogCatEntry;

import java.util.List;

@Dao
public interface LogCatEntryRepo {
    @Query("SELECT * FROM logcatentry")
    List<LogCatEntry> getAll();

    @Insert
    void insert(LogCatEntry logCatEntry);
}
