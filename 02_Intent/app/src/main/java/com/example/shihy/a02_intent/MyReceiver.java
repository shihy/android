package com.example.shihy.a02_intent;

import android.app.Notification;
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
public class MyReceiver extends BroadcastReceiver {
    public final static String TAG = "BroadCastTest";

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播处理
        String value = intent.getStringExtra("msg");
        Log.d(TAG, "return Main");
        final int notifyID = 1;

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("my notification")
                        .setContentText(value)
                        .setAutoCancel(true);
        // 设置跳转Activity
        Intent resultIntent = new Intent(context, NotificationActivity.class);
        // 创建堆栈生成器
        TaskStackBuilder stackBuilder2 = TaskStackBuilder.create(context);
        // 将返回栈添加到堆栈生成器
        stackBuilder2.addParentStack(NotificationActivity.class);
        // 添加可从通知中启动 Activity
        stackBuilder2.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder2.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        manager.notify(notifyID, builder.build());
    }


}
