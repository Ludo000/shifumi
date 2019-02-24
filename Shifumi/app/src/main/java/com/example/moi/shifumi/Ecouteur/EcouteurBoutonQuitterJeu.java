package com.example.moi.shifumi.Ecouteur;

import android.view.View;

import com.example.moi.shifumi.MainActivity;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.Fragments.StartFragment;
import com.example.moi.shifumi.R;
import com.example.moi.shifumi.ResulatActivity;

public class EcouteurBoutonQuitterJeu implements View.OnClickListener{

    ResulatActivity resulatActivity;
    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    public EcouteurBoutonQuitterJeu(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
       this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onClick(View view) {
        this.activiteInitServeurWifiP2P.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new StartFragment(this.activiteInitServeurWifiP2P)).commit();

    }
}
