package com.example.shihy.a04_http.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shihy on 16/8/17.
 */
public class HttpUtil {
    public static String get(final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    // 会把body内容发送到服务端。只有POST PUT需要设置
//                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    Log.d("Http", stringBuilder.toString());
                    if (listener != null) {
                        listener.success(stringBuilder.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.error(e.getMessage());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.error(e.getMessage());
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
        return "....";
    }
}
