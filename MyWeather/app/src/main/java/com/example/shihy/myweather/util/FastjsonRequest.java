package com.example.shihy.myweather.util;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * Created by shihy on 16/8/18.
 */
public class FastjsonRequest extends Request<JSONObject> {
    private Response.Listener<JSONObject> mListener;

    public FastjsonRequest(int method, String url, Response.ErrorListener listener, Response.Listener<JSONObject> listener1) {
        super(method, url, listener);
        mListener = listener1;
    }

    public FastjsonRequest(String url, Response.ErrorListener listener, Response.Listener<JSONObject> mListener) {
        this(Method.GET, url, listener, mListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String re = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(JSONObject.parseObject(re), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(JSONObject jsonObject) {
        mListener.onResponse(jsonObject);
    }
}
