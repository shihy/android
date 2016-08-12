package com.example.shihy.a02_intent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

/**
 * Created by shihy on 16/8/11.
 */
public class MyReceiverSimple extends BroadcastReceiver {
    public final static String TAG = "BroadCastTest";

    @Override
    public void onReceive(Context context, Intent intent) {
        String value = intent.getStringExtra("msg");
        Log.d(TAG, "Simple notification");
        final int notifyID = 5;

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("simple notification")
                        .setContentText(value)
                        .setAutoCancel(true);
        manager.notify(notifyID, builder.build());
    }


}
