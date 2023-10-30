package com.example.lab_bam.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab_bam.Activities.UserActivity;
import com.example.lab_bam.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.buttonSend);
        button.setOnClickListener(v -> onButtonClick());
    }

    public void onButtonClick() {

        EditText editText = findViewById(R.id.editText);
        String textToPass = editText.getText().toString();

        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(Intent.EXTRA_USER, textToPass);
        startActivity(intent);
    }
}