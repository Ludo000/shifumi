package com.example.moi.shifumi.Ecouteur;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.Fragments.ResultFragment;
import com.example.moi.shifumi.R;

public class EcouteurBoutonRock implements View.OnClickListener {


    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    GameFragment gameFragment;
    public EcouteurBoutonRock( ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P, GameFragment gameFragment) {
       this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
       this.gameFragment = gameFragment;
    }

    @Override
    public void onClick(View view) {


        this.gameFragment.imgRock.setImageResource(R.drawable.rock);
        BitmapDrawable drawable = (BitmapDrawable)   this.gameFragment.imgRock.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        this.activiteInitServeurWifiP2P.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
        new ResultFragment(this.activiteInitServeurWifiP2P,bitmap )).commit();





    }
}
