package com.example.shihy.a04_http.util;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shihy on 16/8/17.
 * 活动基类。做一些基本操作
 */
public class BaseActivity extends Activity {
    private static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 知晓当前活动
        LogUtil.d("BaseActivity", "activity is " + getClass().getSimpleName());
        activities.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }

    /**
     * 关闭所有activity。退出功能
     */
    public static void flushAll() {
        for (Activity activity : activities
                ) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
