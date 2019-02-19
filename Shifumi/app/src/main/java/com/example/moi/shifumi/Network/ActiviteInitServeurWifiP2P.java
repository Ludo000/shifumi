package com.example.moi.shifumi.Network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moi.shifumi.R;

public class ActiviteInitServeurWifiP2P extends AppCompatActivity {


    public WifiP2pManager manager;
    public WifiP2pManager.Channel channel;
    public EcouteurConnectionChanged ecouteurConnectionChanged;
    public EcouteurInfoConnection ecouteurInfoConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_init_serveur_wifi_p2_p);

        this.manager=(WifiP2pManager)this.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = this.manager.initialize(this,this.getMainLooper(), null);
        ecouteurConnectionChanged = new EcouteurConnectionChanged(this);
        ecouteurInfoConnection = new EcouteurInfoConnection(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        IntentFilter filtre;

        filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        this.registerReceiver(this.ecouteurConnectionChanged,filtre);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        this.unregisterReceiver(this.ecouteurConnectionChanged);
    }

}
