package com.example.lab_bam.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_bam.Provider.LogCatEntryContentProvider;
import com.example.lab_bam.R;

public class MainActivity extends AppCompatActivity {

    private LogCatEntryContentProvider logCatEntryContentProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.buttonSend);
        button.setOnClickListener(v -> onButtonClick());
        logCatEntryContentProvider = new LogCatEntryContentProvider(getApplicationContext());
        fetchAndLogEntires();
    }

    public void onButtonClick() {

        EditText editText = findViewById(R.id.editText);
        String textToPass = editText.getText().toString();

        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(Intent.EXTRA_USER, textToPass);
        startActivity(intent);
    }


    @SuppressLint("Range")
    private void fetchAndLogEntires() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Cursor cursor = logCatEntryContentProvider
                    .query(Uri.parse("content://com.example.lab_bam.Provider.LogCatEntryContentProvider/log-cat-entry"), null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Log.i("ContentProvider",
                            "Id: " + cursor.getString(cursor.getColumnIndex("id")) + " " +
                                    "Username: " + cursor.getString(cursor.getColumnIndex("username")) + " " +
                                    "Number: " + cursor.getInt(cursor.getColumnIndex("number")));
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
    }
}