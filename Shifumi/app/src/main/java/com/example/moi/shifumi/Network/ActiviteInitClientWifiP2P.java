package com.example.moi.shifumi.Network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejoindrePlayer;
import com.example.moi.shifumi.R;


public class ActiviteInitClientWifiP2P extends AppCompatActivity {

    public ListView mListView;
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
        mListView = findViewById(R.id.listview);
        btnRejoindrePlayer = findViewById(R.id.btnRejoindrePlayer);
        btnRejoindrePlayer.setOnClickListener(this.ecouteurBoutonRejoindrePlayer);

    }



}
