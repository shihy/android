package com.example.shihy.a04_http;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.shihy.a04_http.adapter.AppAdapter;
import com.example.shihy.a04_http.entity.App;
import com.example.shihy.a04_http.util.BaseActivity;
import com.example.shihy.a04_http.util.BaseApplication;
import com.example.shihy.a04_http.util.BitmapCache;
import com.example.shihy.a04_http.util.FastjsonRequest;
import com.example.shihy.a04_http.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class AppsActivity extends BaseActivity {

    private Button button;
    private ListView appsView;

    private RequestQueue mQueue = Volley.newRequestQueue(BaseApplication.getContext());
    private ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                final List<App> apps = new ArrayList<>();
                JSONObject object = (JSONObject) msg.obj;
                JSONArray array = object.getJSONArray("data");
                for (Object j :array) {
                    JSONObject o = (JSONObject)j;
                    String icon = o.getString("icon");
                    String displayName = o.getString("displayName");
                    apps.add(new App(displayName, icon));
                }
                AppAdapter appAdapte = new AppAdapter(BaseApplication.getContext(), R.layout.app_item, apps, imageLoader);
                appsView.setAdapter(appAdapte);
                appsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(AppsActivity.this, apps.get(i).getName()+".....", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        button = (Button) findViewById(R.id.get_apps);
        appsView = (ListView) findViewById(R.id.apps_list);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FastjsonRequest request = new FastjsonRequest("http://app.xiaomi.com/categotyAllListApi?page=0&categoryId=7&pageSize=30",
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                LogUtil.e("TAG", "error");
                            }
                        },
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Message message = new Message();
                                message.what = 1;
                                message.obj = jsonObject;
                                handler.sendMessage(message);
                            }
                        }
                );
                mQueue.add(request);
            }
        });
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AppsActivity.class);
        context.startActivity(intent);
    }
}
