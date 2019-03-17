package com.example.moi.shifumi.Ecouteur;

import android.view.View;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.WriteThread;
import com.example.moi.shifumi.R;

public class EcouteurBoutonPlay implements View.OnClickListener {

    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    public EcouteurBoutonPlay(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onClick(View view) {

        this.activiteInitServeurWifiP2P.sendReceive.write("start");
        this.activiteInitServeurWifiP2P.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new GameFragment(this.activiteInitServeurWifiP2P)).commit();


    }
}
