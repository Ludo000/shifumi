package com.example.moi.shifumi.Ecouteur;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.moi.shifumi.GameActivity;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.Fragments.ResultFragment;
import com.example.moi.shifumi.R;

public class EcouteurBoutonScissors  implements View.OnClickListener {
    GameActivity gameActivity;
    GameFragment gameFragment;
    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    public EcouteurBoutonScissors(    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P,GameFragment gameFragment)
    {
       this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
       this.gameFragment = gameFragment;
    }

    @Override
    public void onClick(View view) {

        this.gameFragment.imgScissors.setImageResource(R.drawable.scissors);
        BitmapDrawable drawable = (BitmapDrawable) this.gameFragment.imgScissors.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        this.activiteInitServeurWifiP2P.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ResultFragment(this.activiteInitServeurWifiP2P,bitmap )).commit();
    }
}
