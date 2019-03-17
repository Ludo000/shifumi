package com.example.moi.shifumi.Network.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonPaper;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRock;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonScissors;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.R;

@SuppressLint("ValidFragment")
public class GameFragment extends Fragment implements Chronometer.OnChronometerTickListener {

    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    EcouteurBoutonRock ecouteurBoutonRock;
    EcouteurBoutonPaper ecouteurBoutonPaper;
    EcouteurBoutonScissors ecouteurBoutonScissors;

    public ImageButton imgRock;
    public ImageButton imgPaper;
    public ImageButton imgScissors;
    Chronometer chronometre;
    int counter = 10;
    String monJeu;


    @SuppressLint("ValidFragment")
    public GameFragment(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P= activiteInitServeurWifiP2P;
    }

    @SuppressLint("CutPasteId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View viewGame= inflater.inflate(R.layout.activity_game, container, false);
       imgRock =viewGame.findViewById(R.id.imageButtonRock);
       imgPaper = viewGame.findViewById(R.id.imageButtonPaper);
       imgScissors = viewGame.findViewById(R.id.imageButtonScissors);

        chronometre = viewGame.findViewById(R.id.chronometre);
        chronometre.setOnChronometerTickListener(this);
        chronometre.start();

        this.ecouteurBoutonRock = new EcouteurBoutonRock(this.activiteInitServeurWifiP2P,this);
        this.ecouteurBoutonPaper = new EcouteurBoutonPaper(this.activiteInitServeurWifiP2P,this);
        this.ecouteurBoutonScissors = new EcouteurBoutonScissors(this.activiteInitServeurWifiP2P,this);

       imgRock.setOnClickListener(this.ecouteurBoutonRock);
       imgPaper.setOnClickListener(this.ecouteurBoutonPaper);
       imgScissors.setOnClickListener(this.ecouteurBoutonScissors);


       return viewGame;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onChronometerTick(Chronometer chronometer) {

        if(counter < 0)
        {
            counter = 10;
        }
        chronometer.setText(counter + "");
        counter--;

        if ("0".contentEquals(chronometer.getText())) {
            ResultFragment resultFragment = new ResultFragment(this.activiteInitServeurWifiP2P);
            this.activiteInitServeurWifiP2P.args.putString("host", this.activiteInitServeurWifiP2P.jeu);
            resultFragment.setArguments(this.activiteInitServeurWifiP2P.args);
            if (getFragmentManager() != null) {
                getFragmentManager().
                        beginTransaction().replace(R.id.fragment_container, resultFragment).commit();
            }

            Log.d("fini", "STOOPPPPP");
        }
    }
}
