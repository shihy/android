package com.example.shihy.case_service.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.shihy.case_service.MainActivity;
import com.example.shihy.case_service.R;

/**
 * Created by shihy on 16/8/15.
 */
public class ForegroundService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this.getApplicationContext())
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText("前台服务内容")
                .setContentTitle("前台服务").build();

        startForeground(1, notification);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }
}
