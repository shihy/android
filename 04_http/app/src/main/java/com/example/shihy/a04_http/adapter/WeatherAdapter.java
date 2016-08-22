package com.example.shihy.a04_http.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shihy.a04_http.R;
import com.example.shihy.a04_http.entity.Weather;
import com.example.shihy.a04_http.util.LogUtil;

import java.util.List;

/**
 * Created by shihy on 16/8/18.
 */
public class WeatherAdapter extends ArrayAdapter<Weather> {

    private int resourceId;

    public WeatherAdapter(Context context, int resource) {
        super(context, resource);
        resourceId = resource;
    }

    public WeatherAdapter(Context context, int resource, List<Weather> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Weather weather = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView pname = (TextView) view.findViewById(R.id.pname);
        TextView cname = (TextView) view.findViewById(R.id.cname);
        TextView detail = (TextView) view.findViewById(R.id.detail);
        pname.setText(weather.getPname());
        cname.setText(weather.getCname());
        detail.setText(weather.getDetail());
        return view;
    }
}
