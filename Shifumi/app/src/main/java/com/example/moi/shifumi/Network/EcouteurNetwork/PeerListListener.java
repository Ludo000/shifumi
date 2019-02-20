package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.R;
import java.util.Collection;


public class PeerListListener implements WifiP2pManager.PeerListListener{
    ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    public PeerListListener(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
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

            Collection<WifiP2pDevice> refreshedPeers = peerList.getDeviceList();
            this.activiteInitServeurWifiP2P.listeNomWifi = filtreNoms(refreshedPeers);
            if (!refreshedPeers.equals(this.activiteInitServeurWifiP2P.peers)) {
                this.activiteInitServeurWifiP2P.peers.clear();
                this.activiteInitServeurWifiP2P.peers.addAll(refreshedPeers);

                // If an AdapterView is backed by this data, notify it
                // of the change. For instance, if you have a ListView of
                // available peers, trigger an update.
                this.activiteInitServeurWifiP2P.adapterListeWifi.notifyDataSetChanged();

                // Perform any other updates needed based on the new list of
                // peers connected to the Wi-Fi P2P network.
            }

            if (this.activiteInitServeurWifiP2P.peers.size() == 0) {
                Log.d("WiFiDirectActivity", "No devices found");
                return;
            }

        this.activiteInitServeurWifiP2P.adapterListeWifi = new ArrayAdapter<String>(this.activiteInitServeurWifiP2P,
                R.layout.item_liste_serveur, this.activiteInitServeurWifiP2P.listeNomWifi);
        this.activiteInitServeurWifiP2P.mListView.setAdapter(this.activiteInitServeurWifiP2P.adapterListeWifi);
        this.connect();
    }
    public void connect() {
        // Picking the first device found on the network.
        WifiP2pDevice device = this.activiteInitServeurWifiP2P.peers.get(0);

        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        config.wps.setup = WpsInfo.PBC;

        this.activiteInitServeurWifiP2P.mManager.connect(this.activiteInitServeurWifiP2P.mChannel,
                config, new ConnectActionListener(this));
    }



}
