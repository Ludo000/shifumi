package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;

import java.net.InetAddress;

public class EcouteurInfoConnection implements WifiP2pManager.ConnectionInfoListener{


    private AppCompatActivity activiteInitServeurWifiP2P;

    public EcouteurInfoConnection(AppCompatActivity activiteInitServeurWifiP2P)
    {
        this.activiteInitServeurWifiP2P =  activiteInitServeurWifiP2P;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info)
    {
        ((ActiviteInitServeurWifiP2P) this.activiteInitServeurWifiP2P).adresseServeur = info.groupOwnerAddress;      // l'adresse IP du serveur (= Group Owner)est enfin connue
// ici : communiquer l'adresse à l'utilisateur ou lancer une activité

        if (info.groupFormed && info.isGroupOwner) //  cet appareil va jouer le rôle de serveur
        {

        }

    } // onConnectionInfoAvailable

}
