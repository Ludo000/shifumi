package com.example.moi.shifumi.Ecouteur;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.view.View;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;

public class EcouteurBoutonActualiser implements View.OnClickListener{

    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    public EcouteurBoutonActualiser(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onClick(View view) {
        this.activiteInitServeurWifiP2P.mManager.discoverPeers(this.activiteInitServeurWifiP2P.mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                // Code for when the discovery initiation is successful goes here.
                // No services have actually been discovered yet, so this method
                // can often be left blank. Code for peer discovery goes in the
                // onReceive method, detailed below.
                Log.d("ludo :","discoverPeers success");

            }

            @Override
            public void onFailure(int reasonCode) {
                // Code for when the discovery initiation fails goes here.
                // Alert the user that something went wrong.
                Log.d("ludo :","discoverPeers failed, code : " + Integer.toString(reasonCode));
            }
        });

    }
}
