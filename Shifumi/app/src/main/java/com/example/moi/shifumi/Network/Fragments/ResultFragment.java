package com.example.moi.shifumi.Network.Fragments;

import android.annotation.SuppressLint;
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
    ImageView jeu_host, jeu_guest;
    TextView winner, joueur_host, joueur_guest;
    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    String host, guest;

    @SuppressLint("ValidFragment")
    public ResultFragment(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P= activiteInitServeurWifiP2P;
    }

    @SuppressLint({"CutPasteId", "SetTextI18n"})
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewResult =  inflater.inflate(R.layout.activity_resulat, container, false);
        this.ecouteurBoutonRejouer = new EcouteurBoutonRejouer(this.activiteInitServeurWifiP2P);
        this.ecouteurBoutonQuitterJeu = new EcouteurBoutonQuitterJeu(this.activiteInitServeurWifiP2P);
        btnRejouer = viewResult.findViewById(R.id.btnRePlay);
        btnQutterJeu = viewResult.findViewById(R.id.btnAnnuler);
        winner = viewResult.findViewById(R.id.txtWinner);
        joueur_host = viewResult.findViewById(R.id.playerServ);
        joueur_guest = viewResult.findViewById(R.id.playerClient);

        if(this.activiteInitServeurWifiP2P.etat.equals("client")){
            jeu_guest = viewResult.findViewById(R.id.imageViewServ);
            jeu_host = viewResult.findViewById(R.id.imageViewClient);
            joueur_guest = viewResult.findViewById(R.id.playerServ);
            joueur_host = viewResult.findViewById(R.id.playerClient);
        }
        else {
            jeu_host = viewResult.findViewById(R.id.imageViewServ);
            jeu_guest = viewResult.findViewById(R.id.imageViewClient);
            joueur_host = viewResult.findViewById(R.id.playerServ);
            joueur_guest = viewResult.findViewById(R.id.playerClient);
        }


        joueur_host.setText(this.activiteInitServeurWifiP2P.playerName);
        joueur_guest.setText(this.activiteInitServeurWifiP2P.challengerNAme);

        btnRejouer.setOnClickListener(this.ecouteurBoutonRejouer);
        btnQutterJeu.setOnClickListener(this.ecouteurBoutonQuitterJeu);
        btnQutterJeu.setVisibility(View.GONE);


        if(this.activiteInitServeurWifiP2P.args != null){
            host = this.activiteInitServeurWifiP2P.args.getString("host");
            guest = this.activiteInitServeurWifiP2P.args.getString("guest");

            // image du host
            if (host.equals("f")) {
                jeu_host.setImageResource(R.drawable.paper);
            }
            else if (host.equals("c")) {
                jeu_host.setImageResource(R.drawable.scissors);
            }
            else if (host.equals("p")) {
                jeu_host.setImageResource(R.drawable.rock);
            }

            // image du client
            if (guest.equals("f")) {
                jeu_guest.setImageResource(R.drawable.paper);
            }
            else if (guest.equals("c")) {
                jeu_guest.setImageResource(R.drawable.scissors);
            }
            else if (guest.equals("p")) {
                jeu_guest.setImageResource(R.drawable.rock);
            }



            if ((host.equals("p") && guest.equals("f")) || (host.equals("f") && guest.equals("c")) || (host.equals("c") && guest.equals("p"))) {
                winner.setText(this.activiteInitServeurWifiP2P.challengerNAme);
            }
            else if((host.equals("p") && guest.equals("c")) || (host.equals("f") && guest.equals("p")) || (host.equals("c") && guest.equals("f"))) {
                winner.setText(this.activiteInitServeurWifiP2P.playerName);
            }
            else {
                winner.setText("Match nul !");
            }


        }


        return viewResult;
    }
}
