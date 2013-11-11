package com.natercio.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by natercio on 11/10/13.
 */
public class ToDoAdapter extends ArrayAdapter<ToDoTask> {

    public ToDoAdapter(Context context, ArrayList<ToDoTask> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ToDoTask task = getItem(position);

        View view;
        
        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.generic_list_item, null);
        } else {
            view = convertView;
        }

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.DKGRAY);
        }

        TextView titleTxt = (TextView) view.findViewById(R.id.list_item_title);
        titleTxt.setText(task.getTitle());
        
        TextView extraTxt = (TextView) view.findViewById(R.id.list_item_extra);
        extraTxt.setText(task.getDueDate());
        
        TextView contentTxt = (TextView) view.findViewById(R.id.list_item_content);
        contentTxt.setText(task.getContent());

        return view;
    }
}
