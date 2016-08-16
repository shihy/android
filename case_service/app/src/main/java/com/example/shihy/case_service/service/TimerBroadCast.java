package com.example.shihy.case_service.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by shihy on 16/8/15.
 */
public class TimerBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TimerBroadCast", "onReceive");
        Intent intent1 = new Intent(context, TimerService.class);
        context.startService(intent1);
    }
}
