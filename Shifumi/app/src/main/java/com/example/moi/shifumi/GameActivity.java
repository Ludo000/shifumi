package com.example.moi.shifumi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonPaper;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRock;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonScissors;

public class GameActivity extends AppCompatActivity {

    Button btnPaper;
    Button btnRock;
    Button btnScissors;
    Button btnRejouer;

    EcouteurBoutonRock ecouteurBoutonRock;
    EcouteurBoutonPaper ecouteurBoutonPaper;
    EcouteurBoutonScissors ecouteurBoutonScissors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);




    }
}
