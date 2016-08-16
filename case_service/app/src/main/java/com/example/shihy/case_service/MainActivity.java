package com.example.shihy.case_service;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shihy.case_service.service.AutoService;
import com.example.shihy.case_service.service.ForegroundService;
import com.example.shihy.case_service.service.NormalService;
import com.example.shihy.case_service.service.TimerService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final static String TAG = "Servcie case";

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);
        Button bind = (Button) findViewById(R.id.bind);
        Button unbind = (Button) findViewById(R.id.unbind);
        Button front_start = (Button) findViewById(R.id.front_start);
        Button front_stop = (Button) findViewById(R.id.front_stop);
        Button auto = (Button) findViewById(R.id.auto);
        Button timer = (Button) findViewById(R.id.timer);
        Button timer_stop = (Button) findViewById(R.id.timer_stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        front_start.setOnClickListener(this);
        front_stop.setOnClickListener(this);
        auto.setOnClickListener(this);
        timer.setOnClickListener(this);
        timer_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int optId = view.getId();
        Intent intent = new Intent(this, NormalService.class);
        Intent foregroundIntent = new Intent(this, ForegroundService.class);
        switch (optId) {
            case R.id.start:
                startService(intent);
                break;
            case R.id.stop:
                stopService(intent);
                break;
            case R.id.bind:
                Log.d(TAG, "bind");
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                Log.d(TAG, "unbind");
                unbindService(serviceConnection);
                break;
            case R.id.front_start:
                startService(foregroundIntent);
                break;
            case R.id.front_stop:
                stopService(foregroundIntent);
                break;
            case R.id.auto:
                Intent autoIntent = new Intent(this, AutoService.class);
                startService(autoIntent);
                break;
            case R.id.timer:
                Intent timeIntent = new Intent(this, TimerService.class);
                startService(timeIntent);
                break;
            case R.id.timer_stop:
                Intent timeIntent2 = new Intent(this, TimerService.class);
                stopService(timeIntent2);
                break;
        }
    }
}
