package com.example.moi.shifumi.Network.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
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

import com.example.moi.shifumi.Ecouteur.EcouteurBoutonQuitterJeu;
import com.example.moi.shifumi.Ecouteur.EcouteurBoutonRejouer;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.R;

@SuppressLint("ValidFragment")
public class ResultFragment extends Fragment {

    EcouteurBoutonRejouer ecouteurBoutonRejouer;
    EcouteurBoutonQuitterJeu ecouteurBoutonQuitterJeu;
    Button btnRejouer;
    Button btnQutterJeu;
    ImageView imgServPlayer;
    public TextView playServ;
    Bitmap bitmap;
    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    @SuppressLint("ValidFragment")
    public ResultFragment(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P, Bitmap bitmap) {
        this.activiteInitServeurWifiP2P= activiteInitServeurWifiP2P;
        this.bitmap= bitmap;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewResult =  inflater.inflate(R.layout.activity_resulat, container, false);
        this.ecouteurBoutonRejouer = new EcouteurBoutonRejouer(this.activiteInitServeurWifiP2P);
        this.ecouteurBoutonQuitterJeu = new EcouteurBoutonQuitterJeu(this.activiteInitServeurWifiP2P);
        btnRejouer = viewResult.findViewById(R.id.btnRePlay);
        btnQutterJeu = viewResult.findViewById(R.id.btnAnnuler);
        playServ = viewResult.findViewById(R.id.playerServ);
        imgServPlayer = viewResult.findViewById(R.id.imageViewServ);
        btnRejouer.setOnClickListener(this.ecouteurBoutonRejouer);
        btnQutterJeu.setOnClickListener(this.ecouteurBoutonQuitterJeu);
        playServ.setText(this.activiteInitServeurWifiP2P.playerName);
       // imgServPlayer.setIm setImageResource(this.activiteInitServeurWifiP2P);
        imgServPlayer.setImageBitmap(this.bitmap);

return viewResult;
    }
}
