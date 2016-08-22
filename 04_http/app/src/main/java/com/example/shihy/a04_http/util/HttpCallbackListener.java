package com.example.shihy.a04_http.util;

/**
 * Created by shihy on 16/8/17.
 */
public interface HttpCallbackListener {
    public void success(String response);

    public void error(String response);
}
