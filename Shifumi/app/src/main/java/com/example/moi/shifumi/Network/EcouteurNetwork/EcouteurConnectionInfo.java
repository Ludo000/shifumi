package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;

import java.net.InetAddress;

public class EcouteurConnectionInfo implements WifiP2pManager.ConnectionInfoListener {
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    public EcouteurConnectionInfo(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {

        // InetAddress from WifiP2pInfo struct.
        InetAddress groupOwnerAddress = info.groupOwnerAddress;

        // After the group negotiation, we can determine the group owner
        // (server).
        if (info.groupFormed && info.isGroupOwner) {
            // Do whatever tasks are specific to the group owner.
            // One common case is creating a group owner thread and accepting
            // incoming connections.
        } else if (info.groupFormed) {
            // The other device acts as the peer (client). In this case,
            // you'll want to create a peer thread that connects
            // to the group owner.
        }

    }
}
