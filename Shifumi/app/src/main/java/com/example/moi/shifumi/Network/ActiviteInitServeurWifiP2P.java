package com.example.moi.shifumi.Network;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonPlay;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurConnectionChanged;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurInfoConnection;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurPeersChanged;
import com.example.moi.shifumi.Network.EcouteurNetwork.MonPeerListener;
import com.example.moi.shifumi.R;

import java.net.InetAddress;

public class ActiviteInitServeurWifiP2P extends AppCompatActivity {


    public WifiP2pManager manager;
    public WifiP2pManager.Channel channel;
    public EcouteurConnectionChanged ecouteurConnectionChanged;
    public EcouteurInfoConnection ecouteurInfoConnection;

    public Button btnPlay;
    EcouteurBoutonPlay ecouteurBoutonPlay;
    public InetAddress adresseServeur;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView servPlayerName;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_init_serveur_wifi_p2_p);
        btnPlay = findViewById(R.id.btnPlay);
        this.ecouteurBoutonPlay = new EcouteurBoutonPlay(this);
        btnPlay.setOnClickListener(this.ecouteurBoutonPlay);

        String playerName = getIntent().getStringExtra("NamePlayer");
        servPlayerName = findViewById(R.id.servPlayerName);
        servPlayerName.setText(playerName);


        this.manager=(WifiP2pManager)this.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = this.manager.initialize(this,this.getMainLooper(), null);
        ecouteurConnectionChanged = new EcouteurConnectionChanged(this);
        ecouteurInfoConnection = new EcouteurInfoConnection(this);

        this.manager = (WifiP2pManager) this.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = this.manager.initialize(this, this.getMainLooper(), null);  // active le réseau Wifi P2P

        this.manager.createGroup(this.channel, null);  // indique que cet appareil va servir de serveur ou de Group Owner



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
