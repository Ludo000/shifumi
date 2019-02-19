package com.example.moi.shifumi.Ecouteur;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.moi.shifumi.MainActivity;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;


public class EcouteurBoutonCreePartie implements View.OnClickListener{

    MainActivity mainActivity;
    EditText playerName;

    public EcouteurBoutonCreePartie(MainActivity mainActivity, EditText playerName) {

        this.mainActivity = mainActivity;
        this.playerName = playerName;
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this.mainActivity, ActiviteInitServeurWifiP2P.class);
        intent.putExtra("NamePlayer",this.playerName.getText().toString());
        this.mainActivity.startActivity(intent);


    }
}
