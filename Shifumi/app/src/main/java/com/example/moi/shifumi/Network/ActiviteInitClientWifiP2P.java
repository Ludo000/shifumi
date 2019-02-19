package com.example.moi.shifumi.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejoindrePlayer;
import com.example.moi.shifumi.R;

import java.util.ArrayList;

public class ActiviteInitClientWifiP2P extends AppCompatActivity {

    public WifiP2pManager manager;
    public WifiP2pManager.Channel channel;
    public EcouteurInfoConnection ecouteurInfoConnection;
    public BroadcastReceiver receveur;
    public ListView mListView;
    public ArrayAdapter<String> adapter;
    public Button btnRejoindrePlayer;
    public EcouteurBoutonRejoindrePlayer ecouteurBoutonRejoindrePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_init_client_wifi_p2_p);
        mListView = findViewById(R.id.listview);
        btnRejoindrePlayer = findViewById(R.id.btnRejoindrePlayer);

        this.ecouteurBoutonRejoindrePlayer = new EcouteurBoutonRejoindrePlayer(this);

        btnRejoindrePlayer.setOnClickListener(this.ecouteurBoutonRejoindrePlayer);



        String playerName = getIntent().getStringExtra("NamePlayer");
        String[] listplayer = {playerName};
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.mytextview, listplayer);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


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
