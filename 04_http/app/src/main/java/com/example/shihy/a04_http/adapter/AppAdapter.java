package com.example.shihy.a04_http.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.shihy.a04_http.R;
import com.example.shihy.a04_http.entity.App;

import java.util.List;

/**
 * Created by shihy on 16/8/18.
 */
public class AppAdapter extends ArrayAdapter<App> {
    private int resourceId;
    private ImageLoader imageLoader;

    public AppAdapter(Context context, int resource) {
        super(context, resource);
    }

    public AppAdapter(Context context, int resource, List<App> objects, ImageLoader imageLoader) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.imageLoader = imageLoader;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        App app = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }
        NetworkImageView icon = (NetworkImageView) view.findViewById(R.id.app_icon);
        TextView name = (TextView) view.findViewById(R.id.app_name);
        icon.setErrorImageResId(R.mipmap.ic_launcher);
        icon.setDefaultImageResId(R.mipmap.ic_launcher);
        icon.setImageUrl(app.getIcon(), imageLoader);
        name.setText(app.getName());
        return view;
    }
}
