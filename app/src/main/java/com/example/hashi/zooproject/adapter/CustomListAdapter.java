package com.example.hashi.zooproject.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hashi.zooproject.R;


public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the list of countries
    private final String[] nameArray;

    public CustomListAdapter(Activity context, String[] nameArray) {
        super(context, R.layout.item_view_list, nameArray);
        this.context = context;
        this.nameArray = nameArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_view_list, null, true);
        TextView majorCatName = view.findViewById(R.id.tvMajorCategory);
        majorCatName.setText(nameArray[position]);
        return view;
    }
}
