package com.example.moi.shifumi.Ecouteur;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.Fragments.ResultFragment;
import com.example.moi.shifumi.Network.WriteThread;
import com.example.moi.shifumi.R;

public class EcouteurBoutonScissors  implements View.OnClickListener {
    private GameFragment gameFragment;
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    public EcouteurBoutonScissors(    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P,GameFragment gameFragment)
    {
       this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
       this.gameFragment = gameFragment;
    }

    @Override
    public void onClick(View view) {
        this.activiteInitServeurWifiP2P.jeu = "c";
        this.activiteInitServeurWifiP2P.sendReceive.write("c." + this.activiteInitServeurWifiP2P.playerName);

        this.gameFragment.imgScissors.setImageResource(R.drawable.scissors);
        this.gameFragment.imgRock.setAlpha(0.2f);
        this.gameFragment.imgScissors.setAlpha(1f);
        this.gameFragment.imgPaper.setAlpha(0.2f);
    }
}
