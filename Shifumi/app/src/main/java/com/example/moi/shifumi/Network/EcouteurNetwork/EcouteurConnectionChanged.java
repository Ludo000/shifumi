package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;

public class EcouteurConnectionChanged extends BroadcastReceiver {
    AppCompatActivity activite;

    public EcouteurConnectionChanged(AppCompatActivity activite) {
        this.activite = activite;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)) // quelque chose a changé dans le réseau local wifi direct
        {
            NetworkInfo networkInfo =
                    (NetworkInfo) intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {
                ((ActiviteInitServeurWifiP2P)activite).manager.requestConnectionInfo(((ActiviteInitServeurWifiP2P)activite).channel, ((ActiviteInitServeurWifiP2P)activite).ecouteurInfoConnection); // on peut enfin demander l'adresse IP du serveur
            }
        }
    }

}
