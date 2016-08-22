package com.example.shihy.a04_http.util;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shihy on 16/8/17.
 */
public class JokeUtil {

    public static JSONObject getImgJoke() {
        String url = "http://japi.juhe.cn/joke/img/text.from";
//        String url = "http://app.xiaomi.com/categotyAllListApi?page=0&categoryId=7&pageSize=30";
        Map<String, Object> params = new HashMap<>();
        params.put("page", 1);
        params.put("pagesize", 1);
        params.put("key", "f7876d9d5233c5de7953668e2d5b57a2");

        RequestQueue mQueue = Volley.newRequestQueue(BaseApplication.getContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(url, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonArray) {
                LogUtil.d("TAG", jsonArray.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mQueue.add(jsonArrayRequest);

        JSONObject ret = new JSONObject();
        return ret;
    }


    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



}
