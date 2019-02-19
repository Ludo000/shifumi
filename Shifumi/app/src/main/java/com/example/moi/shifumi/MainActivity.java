package com.example.moi.shifumi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonCreePartie;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejoindre;

public class MainActivity extends AppCompatActivity {

    Button btnCreePartie;
    Button btnRejoindre;

    EcouteurBoutonCreePartie ecouteurBoutonCreePartie;
    EcouteurBoutonRejoindre ecouteurBoutonRejoindre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreePartie = findViewById(R.id.btnCreePartie);
        btnRejoindre = findViewById(R.id.btnRejoindre);

        this.ecouteurBoutonCreePartie = new EcouteurBoutonCreePartie(this);
        btnCreePartie.setOnClickListener(this.ecouteurBoutonCreePartie);

        this.ecouteurBoutonRejoindre = new EcouteurBoutonRejoindre(this);
        btnRejoindre.setOnClickListener(this.ecouteurBoutonRejoindre);

    }
}
