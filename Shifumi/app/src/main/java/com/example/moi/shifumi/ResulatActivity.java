package com.example.moi.shifumi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonQuitterJeu;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejouer;

public class ResulatActivity extends AppCompatActivity {

    Button btnRejouer;
    Button btnQuitter;

    EcouteurBoutonRejouer ecouteurBoutonRejouer;
    EcouteurBoutonQuitterJeu ecouteurBoutonQuitterJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulat);

        this.ecouteurBoutonRejouer = new EcouteurBoutonRejouer(this);
        this.ecouteurBoutonQuitterJeu= new EcouteurBoutonQuitterJeu(this);

        btnRejouer.setOnClickListener(this.ecouteurBoutonRejouer);
        btnQuitter.setOnClickListener(this.ecouteurBoutonQuitterJeu);



    }
}
