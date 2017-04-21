package com.prangroup.VisitReporter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prangroup.VisitReporter.BusinessObjects.Discussion;
import com.prangroup.VisitReporter.R;

import java.util.List;

/**
 * Created by Manik on 4/20/2017.
 */
public class DiscussionAdapter extends ArrayAdapter<Discussion> {
    Context context;
    public DiscussionAdapter(Context context, List<Discussion> objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.discussion_row, parent, false);
        }
        Discussion discussion=getItem(position);
        TextView txtSL= (TextView) convertView.findViewById(R.id.txtSL);
        TextView txtVisitNumber= (TextView) convertView.findViewById(R.id.txtDiscussionPoint);
        TextView txtAction= (TextView) convertView.findViewById(R.id.txtAction);
        txtSL.setText(position+1+". ");
        txtVisitNumber.setText(discussion.getDiscussionPoint());
        txtAction.setText(discussion.getAction());
        txtVisitNumber.setTag(discussion);
        return convertView;
    }
}
