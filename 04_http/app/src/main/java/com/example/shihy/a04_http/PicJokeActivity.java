package com.example.shihy.a04_http;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.shihy.a04_http.util.BaseActivity;
import com.example.shihy.a04_http.util.BaseApplication;
import com.example.shihy.a04_http.util.BitmapCache;
import com.example.shihy.a04_http.util.JokeUtil;
import com.example.shihy.a04_http.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class PicJokeActivity extends BaseActivity {

    private Button getPic;
    private TextView title;
    private ImageView pic;
    private ImageView pic2;
    RequestQueue mQueue = Volley.newRequestQueue(BaseApplication.getContext());
    ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
    NetworkImageView pic3;

    private final int SHOW_JOKE = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SHOW_JOKE) {
                JSONObject obj = (JSONObject) msg.obj;
                try {
                    JSONArray array = obj.getJSONArray("data");
                    JSONObject app = (JSONObject) array.get(0);
                    String displayName = app.getString("displayName");
                    title.setText(displayName);

                    String icon = app.getString("icon");
                    // case 1: 通过ImageRequest来加载图片
                    ImageRequest imageRequest = createImageRequest(icon);
                    mQueue.add(imageRequest);

                    // case 2: 通过ImageLoader加载图片
                    ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(pic2, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                    imageLoader.get(icon, imageListener, 200, 200);

                    // case 3: 通过NewworkView
                    pic3.setDefaultImageResId(R.mipmap.ic_launcher);
                    pic3.setErrorImageResId(R.mipmap.ic_launcher);
                    pic3.setImageUrl(icon, imageLoader);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @NonNull
    private ImageRequest createImageRequest(String icon) {
        return new ImageRequest(icon,
                                new Response.Listener<Bitmap>() {
                                    @Override
                                    public void onResponse(Bitmap bitmap) {
                                        pic.setImageBitmap(bitmap);
                                    }
                                },
                                0, 0, Bitmap.Config.RGB_565,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                    }
                                }
                        );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_joke);

        getPic = (Button) findViewById(R.id.get_pic_joke);
        title = (TextView) findViewById(R.id.joke_title);
        pic = (ImageView) findViewById(R.id.joke_pic);
        pic2 = (ImageView) findViewById(R.id.joke_pic2);
        pic3 = (NetworkImageView) findViewById(R.id.joke_pic3);

        getPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                        "http://app.xiaomi.com/categotyAllListApi?page=0&categoryId=7&pageSize=30",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Message message = new Message();
                                message.what = SHOW_JOKE;
                                message.obj = jsonObject;
                                handler.sendMessage(message);
                                LogUtil.d("TAG", jsonObject.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        });
                mQueue.add(jsonArrayRequest);
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PicJokeActivity.class);
        context.startActivity(intent);
    }

}
