package com.example.moi.shifumi.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;


public class MonWifiP2PBroadcastReceiver extends BroadcastReceiver {

    ActiviteInitClientWifiP2P  activite;

    public MonWifiP2PBroadcastReceiver(ActiviteInitClientWifiP2P  activite) {
        this.activite = activite;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION))
// le wifi p2p vient d'être activé ou désactivé par le système.
        {
            int extra = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE,-1);

            if (extra == WifiP2pManager.WIFI_P2P_STATE_ENABLED)
            {
                // le wifi p2p vient d'être activé. Réponse à initialize()

            }

            if (extra == WifiP2pManager.WIFI_P2P_STATE_DISABLED)
            {
                // le wifi p2p vient d'être désactivé

            }
        }

        if (action.equals(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION))
// le système a détecté un nouvel appareil connecté en wifi p2p à proximité
// réponse à discoverPeers() : seulement côté client
        {

        }

        if (action.equals(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION ))
// le réseau local a changé
        {
            NetworkInfo networkInfo = (NetworkInfo)intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);
            if (networkInfo.isConnected())
            {
                this.activite.manager.requestConnectionInfo(this.activite.channel,
                        this.activite.ecouteurInfoConnection);
            }

        }
    }   // onReceive()

}

