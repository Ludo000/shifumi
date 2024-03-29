package com.example.moi.shifumi.Ecouteur;

import android.view.View;

import com.example.moi.shifumi.MainActivity;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.Fragments.StartFragment;
import com.example.moi.shifumi.R;


public class EcouteurBoutonQuitterJeu implements View.OnClickListener{

     ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    public EcouteurBoutonQuitterJeu(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
       this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onClick(View view) {
        this.activiteInitServeurWifiP2P.sendReceive.write("exit");
        this.activiteInitServeurWifiP2P.serverStarted = false;
        this.activiteInitServeurWifiP2P.clientStarted = false;
        this.activiteInitServeurWifiP2P.disconnectPeers();
        this.activiteInitServeurWifiP2P.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new StartFragment(this.activiteInitServeurWifiP2P)).commit();

    }
}
