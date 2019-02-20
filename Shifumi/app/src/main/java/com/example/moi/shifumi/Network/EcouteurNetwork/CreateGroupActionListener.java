package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.widget.Toast;

import com.example.moi.shifumi.Network.ActiviteInitClientWifiP2P;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;

public class CreateGroupActionListener implements WifiP2pManager.ActionListener {
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    public CreateGroupActionListener(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onSuccess() {
        // Device is ready to accept incoming connections from peers.
        Log.d("sami :","P2P group creation success.");
    }

    @Override
    public void onFailure(int reason) {
        Log.d("sami :","P2P group creation failed. Code : " + Integer.toString(reason));
        this.activiteInitServeurWifiP2P.creerGroupe();
    }
}
