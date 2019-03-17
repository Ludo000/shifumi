package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.net.wifi.p2p.WifiP2pDevice;
import android.view.View;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.DeviceListArrayAdapter;
import com.example.moi.shifumi.R;

import java.util.Collection;
import java.util.Iterator;

public class EcouteurItemListServer implements View.OnClickListener {
    private int index;
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    public EcouteurItemListServer(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P, int index) {
        this.index = index;
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public void onClick(View v) {
        this.activiteInitServeurWifiP2P.connect(this.index);
       // this.activiteInitServeurWifiP2P.btnPlay.setEnabled(true);

    }
}
