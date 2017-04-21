package com.prangroup.VisitReporter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prangroup.VisitReporter.BusinessObjects.Zone;
import com.prangroup.VisitReporter.R;

import java.util.List;

/**
 * Created by Manik on 10/4/16.
 */
public class ZoneAdapter extends ArrayAdapter<Zone> {
    public ZoneAdapter(Context context, List<Zone> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zone zone=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_list_item_1, parent, false);
        }
        TextView textView1= (TextView) convertView.findViewById(R.id.textView1);
        textView1.setText(zone.getZoneName());
        textView1.setTag(zone);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Zone zone=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_list_item_1, parent, false);
        }
        TextView textView1= (TextView) convertView.findViewById(R.id.textView1);
        textView1.setText(zone.getZoneName());
        textView1.setTag(zone);
        return convertView;
    }

}
