package com.example.shihy.a04_http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shihy.a04_http.util.BaseActivity;
import com.example.shihy.a04_http.util.HttpCallbackListener;
import com.example.shihy.a04_http.util.HttpUtil;
import com.example.shihy.a04_http.util.LogUtil;

public class JokeActivity extends BaseActivity {
    private final static int SHOW_JOKE = 0;

    private TextView showJoke;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case SHOW_JOKE:
                    showJoke.setText(message.obj.toString());
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        showJoke = (TextView) findViewById(R.id.show_joke);


        Button getJoke = (Button) findViewById(R.id.get_one_joke);
        getJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtil.get("http://www.jianshu.com/p/8417c2695866", new HttpCallbackListener() {
                    @Override
                    public void success(String response) {
                        LogUtil.d("Joke", response);
                        Message message = new Message();
                        message.what = SHOW_JOKE;
                        message.obj = response;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void error(String response) {
                        LogUtil.d("Joke error", response);
                    }
                });


            }
        });

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JokeActivity.class);
        context.startActivity(intent);
    }
}
