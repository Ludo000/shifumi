package com.example.moi.shifumi.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejoindrePlayer;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurConnectionChanged;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurInfoConnection;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurPeersChanged;
import com.example.moi.shifumi.Network.EcouteurNetwork.MonPeerListener;
import com.example.moi.shifumi.R;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ActiviteInitClientWifiP2P extends AppCompatActivity {

    public WifiP2pManager manager;
    public WifiP2pManager.Channel channel;
    public EcouteurInfoConnection ecouteurInfoConnection;
    public BroadcastReceiver receveur;
    public ListView mListView;
    public ArrayAdapter<String> adapter;
    public Button btnRejoindrePlayer;
    public EcouteurBoutonRejoindrePlayer ecouteurBoutonRejoindrePlayer;
    public EcouteurPeersChanged ecouteurPeersChanged;
    public MonPeerListener monPeerListener;
    public EcouteurConnectionChanged ecouteurConnectionChanged;
    public InetAddress adresseServeur;
    public Collection<WifiP2pDevice> listeAppareilsAProximite;
    public String[] nomsAppareilsAProximite;

    public ActiviteInitClientWifiP2P() {
        nomsAppareilsAProximite = new String[100];
    }

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
        this.ecouteurPeersChanged = new EcouteurPeersChanged(this);
        this.monPeerListener = new MonPeerListener(this);
        this.ecouteurConnectionChanged = new EcouteurConnectionChanged(this);
        this.ecouteurInfoConnection = new EcouteurInfoConnection(this);


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
        super.onResume();

        adresseServeur = null;

        filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        this.registerReceiver(this.ecouteurPeersChanged, filtre);

        filtre = new IntentFilter();
        filtre.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        this.registerReceiver(this.ecouteurConnectionChanged, filtre);

        this.manager.discoverPeers(this.channel, null); // lance la recherche des appareils wifi directs à proximité
        // c'est ecouteurPeersChanged qui sera averti si un "peer" est trouvé
        this.manager=(WifiP2pManager)this.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = this.manager.initialize(this,this.getMainLooper(), null);
        this.ecouteurPeersChanged = new EcouteurPeersChanged(this);
        this.monPeerListener = new MonPeerListener(this);
        this.ecouteurConnectionChanged = new EcouteurConnectionChanged(this);
        this.ecouteurInfoConnection = new EcouteurInfoConnection(this);
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.item_liste_serveur, nomsAppareilsAProximite);
        this.mListView.setAdapter(itemsAdapter);



    }

    @Override
    protected void onPause()
    {
        super.onPause();
        this.unregisterReceiver(this.receveur);      // le receveur est désactivé
    }

}
