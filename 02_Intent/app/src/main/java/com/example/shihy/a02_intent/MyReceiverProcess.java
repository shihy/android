package com.example.shihy.a02_intent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by shihy on 16/8/11.
 */
public class MyReceiverProcess extends BroadcastReceiver {
    public static int numMessages = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播处理
        String value = intent.getStringExtra("msg");
        Log.d("BroadCast process", value);
        final int notifyID = 2;


        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("my notification process")
                .setContentText(value)
                .setSmallIcon(R.drawable.ic_launcher);
        builder.setAutoCancel(false);

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int incr = 1; incr <= 100; incr++) {
                    // 设置进度条
                    builder.setProgress(100, incr, false);
                    // 设置无进度的进度条
                    // builder.setProgress(0, 0, true);
                    notificationManager.notify(notifyID, builder.build());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Log.e("download", "sleep fail");
                    }

                }
                builder.setContentText("download success").setProgress(0, 0, false);
                notificationManager.notify(notifyID, builder.build());
            }
        }).start();

    }
}
