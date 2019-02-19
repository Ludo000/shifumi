package com.example.moi.shifumi.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;

public class EcouteurConnectionChanged extends BroadcastReceiver {
    ActiviteInitServeurWifiP2P activite;

    public EcouteurConnectionChanged(ActiviteInitServeurWifiP2P activite) {
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
                this.activite.manager.requestConnectionInfo(this.activite.channel, this.activite.ecouteurInfoConnection); // on peut enfin demander l'adresse IP du serveur
            }
        }
    }

}
