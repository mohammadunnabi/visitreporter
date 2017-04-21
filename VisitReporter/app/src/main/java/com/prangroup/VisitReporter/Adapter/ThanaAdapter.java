package com.prangroup.VisitReporter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prangroup.VisitReporter.BusinessObjects.Thana;
import com.prangroup.VisitReporter.R;

import java.util.List;

/**
 * Created by Manik on 10/4/16.
 */
public class ThanaAdapter extends ArrayAdapter<Thana> {
    public ThanaAdapter(Context context, List<Thana> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Thana thana=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_list_item_1, parent, false);
        }
        TextView textView1= (TextView) convertView.findViewById(R.id.textView1);
        textView1.setText(thana.getThanaName());
        textView1.setTag(thana);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        Thana thana=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_list_item_1, parent, false);
        }
        TextView textView1= (TextView) convertView.findViewById(R.id.textView1);
        textView1.setText(thana.getThanaName());
        textView1.setTag(thana);
        return convertView;
    }

}
