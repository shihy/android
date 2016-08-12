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
public class MyReceiverFudong extends BroadcastReceiver {
    public static int numMessages = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播处理
        String value = intent.getStringExtra("msg");
        Log.d("BroadCast fudong", value);
        final int notifyID = 3;





        Intent resultIntent = new Intent(context, NotificationActivity.class);

        // Sets the Activity to start in a new, empty task
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(context, 0, resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("my notification fudong")
                .setContentText(value)
                .setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(notifyPendingIntent);
        builder.setAutoCancel(false);
        notificationManager.notify(notifyID, builder.build());
    }
}
