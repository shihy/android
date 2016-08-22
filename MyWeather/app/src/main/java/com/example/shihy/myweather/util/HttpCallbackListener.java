package com.example.shihy.myweather.util;

/**
 * Created by shihy on 16/8/17.
 */
public interface HttpCallbackListener {
    public void success(String response);

    public void error(String response);
}
