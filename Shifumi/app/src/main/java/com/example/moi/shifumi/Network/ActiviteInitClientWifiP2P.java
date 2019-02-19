package com.example.moi.shifumi.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moi.shifumi.R;

public class ActiviteInitClientWifiP2P extends AppCompatActivity {

    public WifiP2pManager manager;
    public WifiP2pManager.Channel channel;
    public EcouteurInfoConnection ecouteurInfoConnection;
    public BroadcastReceiver receveur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_init_client_wifi_p2_p2);

        this.manager=(WifiP2pManager)this.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = this.manager.initialize(this,this.getMainLooper(), null);


    }

    @Override
    protected void onResume()
    {
        super.onResume();
        IntentFilter filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        filtre.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        filtre.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);


        this.registerReceiver(this.receveur, filtre); // le receveur est activé
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        this.unregisterReceiver(this.receveur);      // le receveur est désactivé
    }

}
