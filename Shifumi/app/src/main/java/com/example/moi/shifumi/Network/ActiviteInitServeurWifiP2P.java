package com.example.moi.shifumi.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonPlay;
import com.example.moi.shifumi.Network.EcouteurNetwork.ConnectActionListener;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurBroadcastReceiver;
import com.example.moi.shifumi.Network.EcouteurNetwork.EcouteurConnectionInfo;
import com.example.moi.shifumi.Network.EcouteurNetwork.PeerListListener;
import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.Fragments.StartFragment;
import com.example.moi.shifumi.R;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ActiviteInitServeurWifiP2P extends AppCompatActivity {


    public String etat;
    public InetAddress groupOwnerAddress;
    public WriteThread writeThreadChoice;
    EcouteurBoutonPlay ecouteurBoutonPlay;
    public InetAddress adresseServeur;

    public WifiP2pManager mManager;
    public WifiP2pManager.Channel mChannel;
    public ListView mListView;
    public ArrayAdapter<String> adapter;
    public Button btnRejoindrePlayer;
    private final IntentFilter intentFilter = new IntentFilter();
    public List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    public DeviceListArrayAdapter adapterListeWifi;
    public Collection<WifiP2pDevice> deviceList;
    public PeerListListener peerListListener;
    public EcouteurConnectionInfo ecouteurConnectionInfo;
    public List<WifiP2pDevice> listeAppareilsAProximite;
    public String[] nomsAppareilsAProximite;
    public TextView enAttente;
    public EcouteurBroadcastReceiver receiver;
    public TextView selectedDevice;
    public String playerName, challengerNAme, jeu;
    public TextView servPlayerName;

    public ImageView imageViewRock;


    public ServerThread serverThread;
    public ClientThread clientClass;
    public SendReceive sendReceive;
    public Handler handler;
    public WriteThread writeThread;
    public ServerSocket serverSocket;
    public Socket socketS;
    public Socket socketC;
    public Bundle args;
    public Boolean clientStarted = false;
    public Boolean serverStarted = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_init_serveur_wifi_p2_p);

        this.disconnectPeers();
        this.etat = "";
        this.ecouteurBoutonPlay = new EcouteurBoutonPlay(this);
        this.mListView = findViewById(R.id.listview_server);
        this.enAttente = findViewById(R.id.enAttentPlayer);
        this.selectedDevice = findViewById(R.id.selectedDevice);
        playerName = getIntent().getStringExtra("NamePlayer");
        challengerNAme = "Joueur 2";
        servPlayerName = findViewById(R.id.servPlayerName);
        //    servPlayerName.setText(playerName);
        this.args = new Bundle();

        // Indicates a change in the Wi-Fi P2P status.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        // Indicates a change in the list of available peers.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        // Indicates the state of Wi-Fi P2P connectivity has changed.
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        // Indicates this device's details have changed.String[] listplayer = {playerName};
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        this.socketC = new Socket();

        String[] listplayer = {playerName};
        receiver = new EcouteurBroadcastReceiver(this);
        this.peerListListener = new PeerListListener(this, this.receiver);
        this.ecouteurConnectionInfo = new EcouteurConnectionInfo(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new StartFragment(this)).commit();
        this.handler = new Handler(new ReadHandler(this));
    }



    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver(receiver, intentFilter);
        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
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




    @Override
    protected void onPause()
    {
        super.onPause();
        this.unregisterReceiver(this.receiver);      // le receveur est désactivé
    }

    public void connect(int id) {
        // Picking the first device found on the network.
        WifiP2pDevice device = this.peers.get(id);

        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        config.wps.setup = WpsInfo.PBC;

        this.mManager.connect(this.mChannel,
                config, new ConnectActionListener(this, id));

    }

    public void disconnectPeers(){
        if (mManager != null && mChannel != null) {
            mManager.requestGroupInfo(mChannel, new WifiP2pManager.GroupInfoListener() {
                @Override
                public void onGroupInfoAvailable(WifiP2pGroup group) {
                    if (group != null && mManager != null && mChannel != null
                            && group.isGroupOwner()) {
                        mManager.removeGroup(mChannel, new WifiP2pManager.ActionListener() {

                            @Override
                            public void onSuccess() {
                                Log.d("ludo", "removeGroup onSuccess -");
                            }

                            @Override
                            public void onFailure(int reason) {
                                Log.d("ludo", "removeGroup onFailure -" + reason);
                            }
                        });
                    }
                }
            });
        }
    }
}
