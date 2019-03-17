package com.example.moi.shifumi.Network.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonActualiser;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonPlay;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.R;

@SuppressLint("ValidFragment")
public class StartFragment extends Fragment {

    public Button btnPlay;
    public Button btnActualiser;
    public  TextView servPlayerName;
    public String playerName;
    public EcouteurBoutonPlay ecouteurBoutonPlay;
    public EcouteurBoutonActualiser ecouteurBoutonActualiser;
    public ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    @SuppressLint("ValidFragment")
    public StartFragment(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start, container, false);
        btnPlay = v.findViewById(R.id.btnPlay);
        btnActualiser = v.findViewById(R.id.btnRefresh);
        this.ecouteurBoutonPlay = new EcouteurBoutonPlay(this.activiteInitServeurWifiP2P);
        this.ecouteurBoutonActualiser = new EcouteurBoutonActualiser(this.activiteInitServeurWifiP2P);
        this.activiteInitServeurWifiP2P.mListView = v.findViewById(R.id.listview_server);
        this.activiteInitServeurWifiP2P.enAttente = v.findViewById(R.id.enAttentPlayer);
        this.activiteInitServeurWifiP2P.selectedDevice = v.findViewById(R.id.selectedDevice);
        this.activiteInitServeurWifiP2P.playerName = this.activiteInitServeurWifiP2P.getIntent().getStringExtra("NamePlayer");
        servPlayerName = v.findViewById(R.id.servPlayerName);
        servPlayerName.setText(this.activiteInitServeurWifiP2P.playerName);
        btnPlay.setOnClickListener(this.ecouteurBoutonPlay);
        btnPlay.setEnabled(this.activiteInitServeurWifiP2P.activerBoutonPlay);
        btnActualiser.setOnClickListener(this.ecouteurBoutonActualiser);


        return v;

    }
}
