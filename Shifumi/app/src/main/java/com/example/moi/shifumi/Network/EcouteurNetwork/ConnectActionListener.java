package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

public class ConnectActionListener implements WifiP2pManager.ActionListener {

    @Override
    public void onSuccess() {
        // WiFiDirectBroadcastReceiver notifies us. Ignore for now.
        Log.d("sami :", "connect success");


    }

    @Override
    public void onFailure(int reason) {

        Log.d("sami :", "connect failed. Code : " + Integer.toString(reason));
    }
}
