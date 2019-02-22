package com.example.moi.shifumi.Ecouteur;

import android.content.Intent;
import android.view.View;

import com.example.moi.shifumi.GameActivity;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;

public class EcouteurBoutonPlay implements View.OnClickListener {

    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    public EcouteurBoutonPlay(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
    this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this.activiteInitServeurWifiP2P, GameActivity.class);
        intent.putExtra("NamePlayer", this.activiteInitServeurWifiP2P.playerName);
        this.activiteInitServeurWifiP2P.startActivity(intent);
    }
}
