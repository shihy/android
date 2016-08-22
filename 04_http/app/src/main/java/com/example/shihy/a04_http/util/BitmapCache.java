package com.example.shihy.a04_http.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by shihy on 16/8/18.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> lruCache;

    public BitmapCache() {
        int size = 10 * 1024 * 1024;
        lruCache = new LruCache<String, Bitmap>(size) {
            protected int sizeOf(Object key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

    }

    @Override
    public Bitmap getBitmap(String s) {
        return lruCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        lruCache.put(s, bitmap);

    }
}
