package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;

import com.example.moi.shifumi.Network.ActiviteInitClientWifiP2P;

public class EcouteurPeersChanged extends BroadcastReceiver
{
    AppCompatActivity activite;

    public EcouteurPeersChanged(AppCompatActivity activite)
    {
        this.activite = activite;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();

        if (action.equals(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)) // le système a détecté un nouvel appareil connecté en wifi p2p à proximité
// réponse à discoverPeers()
        {
            ((ActiviteInitClientWifiP2P)activite).manager.requestPeers( ((ActiviteInitClientWifiP2P)activite).channel,  ((ActiviteInitClientWifiP2P)activite).monPeerListener);
        }

    }
}