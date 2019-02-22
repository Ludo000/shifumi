package com.example.moi.shifumi.Network;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonCreePartie;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurItemListServer;
import com.example.moi.shifumi.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class DeviceListArrayAdapter extends ArrayAdapter<WifiP2pDevice>  {
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    private TextView titleView;
    public DeviceListArrayAdapter(Context context, int resource, ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        super(context, resource);
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }
    @Override
    public void add(WifiP2pDevice  object) {
        this.activiteInitServeurWifiP2P.deviceList.add(object);
        super.add(object);
    }
    @Override
    public WifiP2pDevice getItem(int index) {
        return (WifiP2pDevice) this.activiteInitServeurWifiP2P.deviceList.toArray()[index];
    }

    @Override
    public int getCount() {
        return this.activiteInitServeurWifiP2P.deviceList.size();
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
        WifiP2pDevice device = getItem(position);
        titleView.setText(device.deviceName);
        titleView.setOnClickListener(new EcouteurItemListServer(activiteInitServeurWifiP2P, position));

        return row;
    }
}
