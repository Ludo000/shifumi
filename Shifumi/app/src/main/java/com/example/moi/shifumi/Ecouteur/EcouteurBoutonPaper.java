package com.example.moi.shifumi.Ecouteur;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;


import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.SendReceive;
import com.example.moi.shifumi.Network.WriteThread;
import com.example.moi.shifumi.R;

public class EcouteurBoutonPaper implements View.OnClickListener {


   // public ImageView imageViewRock;
   private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    private GameFragment gameFragment;
    public EcouteurBoutonPaper( ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P,GameFragment gameFragment)
    {
      this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
      this.gameFragment = gameFragment;
    }

    @Override
    public void onClick(View view) {
      //  imageViewRock = view.findViewById(R.id.imageViewRock);
        this.activiteInitServeurWifiP2P.jeu = "f";
        this.activiteInitServeurWifiP2P.sendReceive.write("f");

        this.gameFragment.imgPaper.setImageResource(R.drawable.paper);
        this.gameFragment.imgRock.setAlpha(0.2f);
        this.gameFragment.imgScissors.setAlpha(0.2f);
        this.gameFragment.imgPaper.setAlpha(1f);

    }
}
