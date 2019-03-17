package com.example.moi.shifumi.Ecouteur;

import android.view.View;
import android.widget.Toast;

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

        if((this.activiteInitServeurWifiP2P.etat.equals("serveur") && this.activiteInitServeurWifiP2P.serverStarted)
            || this.activiteInitServeurWifiP2P.etat.equals("client") && this.activiteInitServeurWifiP2P.clientStarted) {
            this.activiteInitServeurWifiP2P.sendReceive.write("start");
            this.activiteInitServeurWifiP2P.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new GameFragment(this.activiteInitServeurWifiP2P)).commit();
        }
        else
            Toast.makeText(this.activiteInitServeurWifiP2P.getApplicationContext(), "Le serveur n'est pas encore prêt !",
                    Toast.LENGTH_SHORT).show();


    }
}
