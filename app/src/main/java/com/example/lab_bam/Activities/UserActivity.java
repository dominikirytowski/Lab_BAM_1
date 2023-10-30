package com.example.lab_bam.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab_bam.ActionEnumValue;
import com.example.lab_bam.BCastRecievers.NumberReceiver;
import com.example.lab_bam.Entity.LogCatEntry;
import com.example.lab_bam.Repository.AppDb;
import com.example.lab_bam.Services.CounterService;
import com.example.lab_bam.R;

import java.util.UUID;

public class UserActivity extends AppCompatActivity {

    private final NumberReceiver numberReceiver = new NumberReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_USER);

        TextView textView = findViewById(R.id.editText);
        textView.setText(text);

        registerReceiver(numberReceiver, new IntentFilter(ActionEnumValue.SUPPRESS_LOGGING.getAction()));

        sendBroadcast(intent);

        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        Button fetch = findViewById(R.id.fetch_data);

        Intent intentService = new Intent(getApplicationContext(), CounterService.class);
        intentService.putExtra(Intent.EXTRA_USER, text);

        start.setOnClickListener(v -> onStartButton(intentService));
        stop.setOnClickListener(v -> onStopButton(intentService));
        fetch.setOnClickListener(v -> onFetchButton());
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(numberReceiver);
        super.onDestroy();
    }

    private void onStopButton(Intent intent) {
        stopService(intent);
    }

    private void onStartButton(Intent intent) {
        startService(intent);
    }

    private void onFetchButton() {
        AppDb db = Room.databaseBuilder(getApplicationContext(), AppDb.class, "log-cat-entry")
                .allowMainThreadQueries()
                .build();
        db.logCatEntryRepo().getAll().forEach(el -> {
            Log.i("Db entry: ", el.getUserName() + " " + el.getLastNumber());
        });
    }

}