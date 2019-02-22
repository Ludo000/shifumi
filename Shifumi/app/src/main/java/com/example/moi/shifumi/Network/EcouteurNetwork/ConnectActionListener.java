package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ConnectActionListener implements WifiP2pManager.ActionListener {
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel mChannel;
    public ConnectActionListener(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel mChannel) {
        this.wifiP2pManager = wifiP2pManager;
        this.mChannel = mChannel;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSuccess() {
        // WiFiDirectBroadcastReceiver notifies us. Ignore for now.
        Log.d("ludo :", "connect success");
        Map record = new HashMap();
        record.put("listenport", String.valueOf(3333));
        record.put("buddyname", "Sami" + (int) (Math.random() * 1000));
        record.put("available", "visible");

        // Service information.  Pass it an instance name, service type
        // _protocol._transportlayer , and the map containing
        // information other devices will want once they connect to this one.
        WifiP2pDnsSdServiceInfo serviceInfo =
                WifiP2pDnsSdServiceInfo.newInstance("_test", "_presence._tcp", record);

        // Add the local service, sending the service info, network channel,
        // and listener that will be used to indicate success or failure of
        // the request.
       this.wifiP2pManager.addLocalService(mChannel, serviceInfo, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                // Command successful! Code isn't necessarily needed here,
                // Unless you want to update the UI or add logging statements.
                Log.d("ludo :", "addLocalService success");
            }

            @Override
            public void onFailure(int arg0) {
                // Command failed.  Check for P2P_UNSUPPORTED, ERROR, or BUSY
                Log.d("ludo :", "addLocalService error : " + arg0);

            }
        });




    }

    @Override
    public void onFailure(int reason) {

        Log.d("ludo :", "connect failed. Code : " + Integer.toString(reason));
    }
}
