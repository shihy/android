package com.example.shihy.a04_http.util;

import android.util.Log;

/**
 * Created by shihy on 16/8/17.
 * 打印日志  根据设置的Level
 */
public class LogUtil {

    public static void d(String msg) {
        Log.d("TAG", msg);
    }
    public static void d(String TAG, String msg) {
        Log.d(TAG, msg);
    }

    public static void e(String TAG, String msg) {
        Log.e(TAG, msg);
    }
}
