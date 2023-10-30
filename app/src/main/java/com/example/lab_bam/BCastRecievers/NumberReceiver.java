package com.example.lab_bam.BCastRecievers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.room.Room;

import com.example.lab_bam.Entity.LogCatEntry;
import com.example.lab_bam.Repository.AppDb;

import java.util.Optional;
import java.util.UUID;

public class NumberReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String username = intent.getStringExtra(Intent.EXTRA_USER);
        Integer number = intent.getIntExtra(Intent.EXTRA_PHONE_NUMBER, 0);
        Log.i("Username", Optional.ofNullable(username).orElse(""));
        Log.i("Number", String.valueOf(number));
        buildAndSaveEntryToDb(username, number, context);
    }

    private void buildAndSaveEntryToDb(String username, Integer number, Context context) {
        AppDb db = Room.databaseBuilder(context, AppDb.class, "log-cat-entry")
                .allowMainThreadQueries()
                .build();
        db.logCatEntryRepo().insert(new LogCatEntry(UUID.randomUUID(), username, number));
    }
}