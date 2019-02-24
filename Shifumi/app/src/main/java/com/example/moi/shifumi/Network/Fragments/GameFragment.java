package com.example.moi.shifumi.Network.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonPaper;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejouer;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRock;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonScissors;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.R;

@SuppressLint("ValidFragment")
public class GameFragment extends Fragment {

    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    EcouteurBoutonRock ecouteurBoutonRock;
    EcouteurBoutonPaper ecouteurBoutonPaper;
    EcouteurBoutonScissors ecouteurBoutonScissors;

    public Button btnRock;
    public ImageView imgRock;
    public ImageView imgPaper;
    public ImageView imgScissors;


    @SuppressLint("ValidFragment")
    public GameFragment(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P= activiteInitServeurWifiP2P;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View viewGame= inflater.inflate(R.layout.activity_game, container, false);
       imgRock =viewGame.findViewById(R.id.imageViewRock);
       imgPaper = viewGame.findViewById(R.id.imageViewPaper);
       imgScissors = viewGame.findViewById(R.id.imageViewScissors);

      this.ecouteurBoutonRock = new EcouteurBoutonRock(this.activiteInitServeurWifiP2P,this);
      this.ecouteurBoutonPaper = new EcouteurBoutonPaper(this.activiteInitServeurWifiP2P,this);
      this.ecouteurBoutonScissors = new EcouteurBoutonScissors(this.activiteInitServeurWifiP2P,this);

       imgRock.setOnClickListener(this.ecouteurBoutonRock);
       imgPaper.setOnClickListener(this.ecouteurBoutonPaper);
       imgScissors.setOnClickListener(this.ecouteurBoutonScissors);


       return viewGame;

    }
}
