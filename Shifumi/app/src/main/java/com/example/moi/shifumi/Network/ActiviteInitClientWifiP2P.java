package com.example.moi.shifumi.Network;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejoindrePlayer;
import com.example.moi.shifumi.R;

import java.util.HashMap;
import java.util.Map;


public class ActiviteInitClientWifiP2P extends AppCompatActivity {

    public Button btnRejoindrePlayer;
    public EcouteurBoutonRejoindrePlayer ecouteurBoutonRejoindrePlayer;

    public String[] nomsAppareilsAProximite;

    public ActiviteInitClientWifiP2P() {
        nomsAppareilsAProximite = new String[100];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_init_client_wifi_p2_p);
        btnRejoindrePlayer = findViewById(R.id.btnRejoindrePlayer);
        btnRejoindrePlayer.setOnClickListener(this.ecouteurBoutonRejoindrePlayer);



    }
/*


*/


}
