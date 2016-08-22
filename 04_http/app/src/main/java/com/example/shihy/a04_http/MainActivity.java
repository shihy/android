package com.example.shihy.a04_http;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getJoke = (Button) findViewById(R.id.get_joke_btn);
        getJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JokeActivity.actionStart(MainActivity.this);
            }
        });

        Button picJoke = (Button) findViewById(R.id.get_joke_img_btn);
        picJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PicJokeActivity.actionStart(MainActivity.this);
            }
        });
        Button weather = (Button) findViewById(R.id.get_weather_btn);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeatherActivity.actionStart(MainActivity.this);
            }
        });

        Button apps = (Button) findViewById(R.id.get_app_btn);
        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppsActivity.actionStart(MainActivity.this);
            }
        });
    }
}
