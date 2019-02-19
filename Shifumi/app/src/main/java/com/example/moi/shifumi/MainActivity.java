package com.example.moi.shifumi;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonCreePartie;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejoindre;

import com.example.moi.shifumi.Network.EcouteurConnectionChanged;

public class MainActivity extends AppCompatActivity {


    Button btnCreePartie;
    Button btnRejoindre;

    EcouteurBoutonCreePartie ecouteurBoutonCreePartie;
    EcouteurBoutonRejoindre ecouteurBoutonRejoindre;

    EditText edtNamePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreePartie = findViewById(R.id.btnCreePartie);
        btnRejoindre = findViewById(R.id.btnRejoindre);
        edtNamePlayer = findViewById(R.id.edtNamePlayer);



        this.ecouteurBoutonCreePartie = new EcouteurBoutonCreePartie(this,edtNamePlayer);
        btnCreePartie.setOnClickListener(this.ecouteurBoutonCreePartie);

        this.ecouteurBoutonRejoindre = new EcouteurBoutonRejoindre(this, edtNamePlayer);
        btnRejoindre.setOnClickListener(this.ecouteurBoutonRejoindre);

    }
}
