package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.content.BroadcastReceiver;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.DeviceListArrayAdapter;
import com.example.moi.shifumi.R;
import java.util.Collection;


public class PeerListListener implements WifiP2pManager.PeerListListener{
    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    EcouteurBroadcastReceiver broadcastReceiver;

    public PeerListListener(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P, EcouteurBroadcastReceiver broadcastReceiver) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
        this.broadcastReceiver = broadcastReceiver;
    }
    private static String[] filtreNoms(Collection<WifiP2pDevice> listeAppareilsAProximite)
    {
        String [] t = new String[listeAppareilsAProximite.size()];
        int i = -1;
        for (WifiP2pDevice d : listeAppareilsAProximite)
            t[++i] = d.deviceName;
        return t;
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peerList) {
        Log.d("ludo :", "onPeersAvailable");

        Collection<WifiP2pDevice> refreshedPeers = peerList.getDeviceList();
        if (!refreshedPeers.equals(this.activiteInitServeurWifiP2P.peers)) {
            this.activiteInitServeurWifiP2P.peers.clear();
            this.activiteInitServeurWifiP2P.peers.addAll(refreshedPeers);
            this.activiteInitServeurWifiP2P.deviceList = refreshedPeers;
            // If an AdapterView is backed by this data, notify it
            // of the change. For instance, if you have a ListView of
            // available peers, trigger an update.
            this.activiteInitServeurWifiP2P.enAttente.setVisibility(View.INVISIBLE);
            this.activiteInitServeurWifiP2P.adapterListeWifi = new DeviceListArrayAdapter(this.activiteInitServeurWifiP2P,
                    R.layout.item_liste_serveur, this.activiteInitServeurWifiP2P);
            this.activiteInitServeurWifiP2P.mListView.setAdapter(this.activiteInitServeurWifiP2P.adapterListeWifi);
            this.activiteInitServeurWifiP2P.adapterListeWifi.notifyDataSetChanged();

            // Perform any other updates needed based on the new list of
            // peers connected to the Wi-Fi P2P network.
        }

        if (this.activiteInitServeurWifiP2P.peers.size() == 0) {
            Log.d("ludo", "No devices found");
            this.broadcastReceiver.reqPeer();
            return;
        }


    }




}
