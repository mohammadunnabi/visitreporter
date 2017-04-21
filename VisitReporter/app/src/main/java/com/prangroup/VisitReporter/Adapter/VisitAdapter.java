package com.prangroup.VisitReporter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prangroup.VisitReporter.BusinessObjects.Visit;
import com.prangroup.VisitReporter.R;

import java.util.List;

/**
 * Created by Manik on 4/20/2017.
 */
public class VisitAdapter extends ArrayAdapter<Visit> {
    Context context;
    public VisitAdapter(Context context,  List<Visit> objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.visit_row, parent, false);
        }
        Visit visit=getItem(position);
        TextView txtVisitNumber= (TextView) convertView.findViewById(R.id.txtVisitNumber);
        txtVisitNumber.setText(visit.getVisitNumber());
        txtVisitNumber.setTag(visit);
        return convertView;
    }
}
