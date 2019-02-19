package com.example.moi.shifumi.Network;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;

import java.net.InetAddress;

public class EcouteurInfoConnection implements WifiP2pManager.ConnectionInfoListener{

    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    public EcouteurInfoConnection(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P)
    {
        this.activiteInitServeurWifiP2P =  activiteInitServeurWifiP2P;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info)
    {
        InetAddress adresseServeur = info.groupOwnerAddress;    // on extrait enfin l'adresse IP

// ici : communiquer l'adresse à l'utilisateur ou lancer une activité

        if (info.groupFormed && info.isGroupOwner) //  cet appareil va jouer le rôle de serveur
        {

        }

    } // onConnectionInfoAvailable

}
