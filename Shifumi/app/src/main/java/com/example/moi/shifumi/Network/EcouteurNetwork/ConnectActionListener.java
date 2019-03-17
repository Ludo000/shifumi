package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.annotation.TargetApi;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceRequest;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.DeviceListArrayAdapter;
import com.example.moi.shifumi.R;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ConnectActionListener implements WifiP2pManager.ActionListener {
    final private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    final private int index;
    final HashMap<String, String> buddies = new HashMap<String, String>();

    public ConnectActionListener(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P, int index) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
        this.index = index;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSuccess() {
        activiteInitServeurWifiP2P.mListView.setVisibility(View.INVISIBLE);
        activiteInitServeurWifiP2P.selectedDevice.setText(((WifiP2pDevice) activiteInitServeurWifiP2P.deviceList.toArray()[index]).deviceName);
        activiteInitServeurWifiP2P.selectedDevice.setVisibility(View.VISIBLE);
    }


    @Override
    public void onFailure(int reason) {

        Log.d("ludo :", "connect failed. Code : " + Integer.toString(reason));
    }
}
