package com.example.moi.shifumi.Ecouteur;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.moi.shifumi.MainActivity;
import com.example.moi.shifumi.Network.ActiviteInitClientWifiP2P;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;

public class EcouteurBoutonRejoindre implements View.OnClickListener {

    MainActivity mainActivity;
    EditText playerName;
    public EcouteurBoutonRejoindre(MainActivity mainActivity, EditText playerName) {

        this.mainActivity = mainActivity;
        this.playerName = playerName;
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this.mainActivity, ActiviteInitClientWifiP2P.class);
        intent.putExtra("NamePlayer",this.playerName.getText().toString());
        this.mainActivity.startActivity(intent);

    }
}
