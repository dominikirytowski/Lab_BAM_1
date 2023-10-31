package com.example.lab_bam.Services;


import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lab_bam.ActionEnumValue;
import com.example.lab_bam.BCastRecievers.NumberReceiver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class CounterService extends Service{
    public CounterService() {
    }
    private boolean isLoggingOnOn = true;
    private String username = "";

    private int lastNumber = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread = new Thread(countingTask());
        username = intent.getStringExtra(Intent.EXTRA_USER);
        thread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        isLoggingOnOn = false;
        Intent intent = new Intent(this, NumberReceiver.class);
        intent.setAction(ActionEnumValue.SUPPRESS_LOGGING.getAction());
        intent.putExtra(Intent.EXTRA_USER, username);
        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, lastNumber);
        sendBroadcast(intent);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Runnable countingTask() {
        return () -> {
            try {
                int i = 0;
                while (isLoggingOnOn) {
                    Log.i("Counter in for thread" + Thread.currentThread().getId(), String.valueOf(i));
                    Thread.sleep(1000);
                    i++;
                    lastNumber = i;
                }
            } catch (InterruptedException e) {
                Log.i("Exception", Arrays.toString(e.getStackTrace()));
            }
        };
    }

}