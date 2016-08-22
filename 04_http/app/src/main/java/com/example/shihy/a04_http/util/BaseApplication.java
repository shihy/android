package com.example.shihy.a04_http.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by shihy on 16/8/17.
 */
public class BaseApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
