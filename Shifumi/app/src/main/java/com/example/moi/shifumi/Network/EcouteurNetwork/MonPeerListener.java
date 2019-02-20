package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;

import com.example.moi.shifumi.Network.ActiviteInitClientWifiP2P;

import java.util.Collection;

public class MonPeerListener implements WifiP2pManager.PeerListListener
{
    /** sert à récupérer la liste des noms des appareils à proximité  */
    private static String[] filtreNoms(Collection<WifiP2pDevice> listeAppareilsAProximite)
    {
        String [] t = new String[listeAppareilsAProximite.size()];


        int i = -1;

        for (WifiP2pDevice d : listeAppareilsAProximite)
            t[++i] = d.deviceName;

        return t;
    }

    AppCompatActivity activite;

    public MonPeerListener(AppCompatActivity activite)
    {
        this.activite = activite;
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) // réponse à requestPeers()
    {
        ((ActiviteInitClientWifiP2P)activite).listeAppareilsAProximite = peers.getDeviceList();

        ((ActiviteInitClientWifiP2P)activite).nomsAppareilsAProximite = filtreNoms(((ActiviteInitClientWifiP2P)activite).listeAppareilsAProximite);

// ici présenter à l'utilisateur la liste des noms des appareils pourqu'il fasse son choix du Group Owner
    }
}