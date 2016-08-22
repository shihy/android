package com.example.shihy.a04_http.util;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by shihy on 16/8/18.
 */
public class XmlRequest extends Request<XmlPullParser> {
    private final Response.Listener<XmlPullParser> mListener;


    public XmlRequest(int method, String url, Response.ErrorListener listener, Response.Listener<XmlPullParser> mListener) {
        super(method, url, listener);
        this.mListener = mListener;
    }

    public XmlRequest(String url, Response.ErrorListener listener, Response.Listener<XmlPullParser> mListener) {
        this(Method.GET, url, listener, mListener);
    }

    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String xmlString = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlString));
            return Response.success(xmlPullParser, HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (XmlPullParserException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(XmlPullParser xmlPullParser) {
        mListener.onResponse(xmlPullParser);
    }
}
