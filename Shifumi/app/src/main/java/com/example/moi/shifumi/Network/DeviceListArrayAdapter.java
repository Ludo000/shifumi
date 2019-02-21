package com.example.moi.shifumi.Network;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.moi.shifumi.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public class DeviceListArrayAdapter extends ArrayAdapter<String> {
    private String[] deviceList = new String[0];
    private TextView titleView;
    public DeviceListArrayAdapter(Context context, int resource, String[] list) {
        super(context, resource, list);
    }
    @Override
    public void add(String object) {
        deviceList = new String[deviceList.length+1];
        deviceList[deviceList.length-1] = object;
        super.add(object);
    }
    @Override
    public String getItem(int index) {
        return this.deviceList[index];
    }

    @Override
    public int getCount() {
        return this.deviceList.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_liste_serveur, parent, false);
            titleView = row.findViewById(R.id.itemListeServeur);
            row.setTag(titleView);
            Log.d("aubin", "je suis la 'null'");
        }
        Log.d("aubin", "je suis la 'not null'");
        String title = getItem(position);
        titleView.setText(title);
        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return row;
    }
}
