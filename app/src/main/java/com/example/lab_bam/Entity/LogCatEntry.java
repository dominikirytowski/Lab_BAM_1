package com.example.lab_bam.Entity;

import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity
public class LogCatEntry {
    @PrimaryKey
    @NotNull
    private UUID id;

    public LogCatEntry(UUID id, String userName, Integer lastNumber) {
        this.id = id;
        this.userName = userName;
        this.lastNumber = lastNumber;
    }

    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "last_number")
    private Integer lastNumber;


    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getLastNumber() {
        return lastNumber;
    }
}
