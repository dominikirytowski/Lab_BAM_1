package com.example.lab_bam.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class LogCatEntry {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    private String id;

    public LogCatEntry(String id, String userName, Integer lastNumber) {
        this.id = id;
        this.userName = userName;
        this.lastNumber = lastNumber;
    }

    @ColumnInfo(name = "username")
    private String userName;
    @ColumnInfo(name = "number")
    private Integer lastNumber;


    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getLastNumber() {
        return lastNumber;
    }
}
