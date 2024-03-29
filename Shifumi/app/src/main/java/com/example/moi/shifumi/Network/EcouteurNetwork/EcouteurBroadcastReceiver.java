package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;

public class EcouteurBroadcastReceiver extends BroadcastReceiver {
    private ActiviteInitServeurWifiP2P activity;

    public EcouteurBroadcastReceiver(ActiviteInitServeurWifiP2P activiteInitServerWifiP2P) {
        this.activity = activiteInitServerWifiP2P;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Determine if Wifi P2P mode is enabled or not, alert
            // the Activity.
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                //activity.setIsWifiP2pEnabled(true);
            } else {
                //activity.setIsWifiP2pEnabled(false);
            }
        }
         else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            // Connection state changed! We should probably do something about
            // that.
            if (this.activity.mManager == null) {
                return;
            }

            NetworkInfo networkInfo = (NetworkInfo) intent
                    .getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {

                // We are connected with the other device, request connection
                // info to find group owner IP

                this.activity.mManager.requestConnectionInfo(this.activity.mChannel, this.activity.ecouteurConnectionInfo
                );
            }


        }
        else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action))
        {
            //

        }
        else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            Log.d("ludo :", "P2P peers changed");

            // Request available peers from the wifi p2p manager. This is an
            // asynchronous call and the calling activity is notified with a
            // callback on PeerListListener.onPeersAvailable()
            this.reqPeer();

        }


    }
    public void reqPeer(){
        if (this.activity.mManager != null) {
            this.activity.mManager.requestPeers(this.activity.mChannel, this.activity.peerListListener);
            Log.d("ludo :", "requestPeers");

        }
    }

}
