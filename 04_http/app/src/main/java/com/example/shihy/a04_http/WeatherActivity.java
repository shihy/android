package com.example.shihy.a04_http;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.shihy.a04_http.adapter.WeatherAdapter;
import com.example.shihy.a04_http.entity.Weather;
import com.example.shihy.a04_http.util.BaseActivity;
import com.example.shihy.a04_http.util.BaseApplication;
import com.example.shihy.a04_http.util.LogUtil;
import com.example.shihy.a04_http.util.XmlRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends BaseActivity {
    private final static int SHOW_WEATHER = 1;

    RequestQueue mQueue = Volley.newRequestQueue(BaseApplication.getContext());

    private Button weather;
    private ListView citys;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_WEATHER) {
                XmlPullParser xmlPullParser = (XmlPullParser) msg.obj;
                try {
                    List<Weather> cities = new ArrayList<>();
                    int eventType = xmlPullParser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        Weather weather = new Weather();
                        switch (eventType) {
                            case XmlPullParser.START_TAG:
                                String nodeName = xmlPullParser.getName();
                                if (nodeName.equals("city")) {
                                    String pname = xmlPullParser.getAttributeValue(0);
                                    String cname = xmlPullParser.getAttributeValue(2);
                                    String detail = xmlPullParser.getAttributeValue(null, "stateDetailed");
                                    weather.setPname(pname);
                                    weather.setCname(cname);
                                    weather.setDetail(detail);
                                    cities.add(weather);
                                }
                                break;
                        }
                        eventType = xmlPullParser.next();

                    }
//                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(WeatherActivity.this, android.R.layout.simple_list_item_1, cities);
                    WeatherAdapter arrayAdapter = new WeatherAdapter(WeatherActivity.this, R.layout.weather_item, cities);
                    citys.setAdapter(arrayAdapter);

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        weather = (Button) findViewById(R.id.get_weather_btn);
        citys = (ListView) findViewById(R.id.cities);

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWeather();
            }
        });
    }

    private void showWeather() {
        String url = "http://flash.weather.com.cn/wmaps/xml/china.xml";
        XmlRequest xmlRequest = new XmlRequest(url,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        LogUtil.e("Weather", volleyError.toString());
                    }
                },
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser xmlPullParser) {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = xmlPullParser;
                        handler.sendMessage(message);
                    }
                }
        );
        mQueue.add(xmlRequest);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, WeatherActivity.class);
        context.startActivity(intent);

    }
}
