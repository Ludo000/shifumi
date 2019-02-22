package com.example.moi.shifumi.Network.EcouteurNetwork;

import android.annotation.TargetApi;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceRequest;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.moi.shifumi.Network.ActiviteInitServeurWifiP2P;
import com.example.moi.shifumi.Network.DeviceListArrayAdapter;
import com.example.moi.shifumi.R;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ConnectActionListener implements WifiP2pManager.ActionListener {
    final private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    final private int index;
    final HashMap<String, String> buddies = new HashMap<String, String>();

    public ConnectActionListener(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P, int index) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
        this.index = index;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSuccess() {
        this.startRegistration();
        this.discoverService();

    }

   @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
   private void startRegistration() {
    // WiFiDirectBroadcastReceiver notifies us. Ignore for now.
       Log.d("ludo :", "connect success");
       Map record = new HashMap();
       record.put("listenport", String.valueOf(3333));
       record.put("buddyname", "Sami" + (int) (Math.random() * 1000));
       record.put("available", "visible");

       // Service information.  Pass it an instance name, service type
       // _protocol._transportlayer , and the map containing
       // information other devices will want once they connect to this one.
       WifiP2pDnsSdServiceInfo serviceInfo =
               WifiP2pDnsSdServiceInfo.newInstance("_test", "_presence._tcp", record);

       // Add the local service, sending the service info, network channel,
       // and listener that will be used to indicate success or failure of
       // the request.
       this.activiteInitServeurWifiP2P.mManager.addLocalService(activiteInitServeurWifiP2P.mChannel, serviceInfo, new WifiP2pManager.ActionListener() {
           @Override
           public void onSuccess() {
               // Command successful! Code isn't necessarily needed here,
               // Unless you want to update the UI or add logging statements.
               Log.d("ludo :", "addLocalService success");
               activiteInitServeurWifiP2P.mListView.setVisibility(View.INVISIBLE);
               activiteInitServeurWifiP2P.selectedDevice.setText(((WifiP2pDevice) activiteInitServeurWifiP2P.deviceList.toArray()[index]).deviceName);
               activiteInitServeurWifiP2P.selectedDevice.setVisibility(View.VISIBLE);
           }

           @Override
           public void onFailure(int arg0) {
               // Command failed.  Check for P2P_UNSUPPORTED, ERROR, or BUSY
               Log.d("ludo :", "addLocalService error : " + arg0);

           }
       });
   }

   @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
   @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void discoverService() {
        WifiP2pManager.DnsSdTxtRecordListener txtListener = new WifiP2pManager.DnsSdTxtRecordListener() {
            @Override

        public void onDnsSdTxtRecordAvailable(
                String fullDomain, Map record, WifiP2pDevice device) {
            Log.d("ludo :", "DnsSdTxtRecord available -" + record.toString());
            buddies.put(device.deviceAddress, (String) record.get("buddyname"));
        }
    };

    WifiP2pManager.DnsSdServiceResponseListener servListener = new WifiP2pManager.DnsSdServiceResponseListener() {
        @Override
        public void onDnsSdServiceAvailable(String instanceName, String registrationType,
                                            WifiP2pDevice resourceType) {

            // Update the device name with the human-friendly version from
            // the DnsTxtRecord, assuming one arrived.
            resourceType.deviceName = buddies
                    .containsKey(resourceType.deviceAddress) ? buddies
                    .get(resourceType.deviceAddress) : resourceType.deviceName;


            Log.d("ludo :", "onBonjourServiceAvailable " + instanceName);
        }
    };

    activiteInitServeurWifiP2P.mManager.setDnsSdResponseListeners(activiteInitServeurWifiP2P.mChannel, servListener, txtListener);
    WifiP2pDnsSdServiceRequest serviceRequest = WifiP2pDnsSdServiceRequest.newInstance();
       activiteInitServeurWifiP2P.mManager.addServiceRequest(activiteInitServeurWifiP2P.mChannel,
            serviceRequest,
            new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    // Success!
                }

                @Override
                public void onFailure(int code) {
                    // Command failed.  Check for P2P_UNSUPPORTED, ERROR, or BUSY
                }
            });
       activiteInitServeurWifiP2P.mManager.discoverServices(activiteInitServeurWifiP2P.mChannel, new WifiP2pManager.ActionListener() {

        @Override
        public void onSuccess() {
            // Success!
        }

        @Override
        public void onFailure(int code) {
            // Command failed.  Check for P2P_UNSUPPORTED, ERROR, or BUSY
            if (code == WifiP2pManager.P2P_UNSUPPORTED) {
                Log.d("ludo :", "P2P isn't supported on this device.");
            }
            else if(code == WifiP2pManager.BUSY){
                Log.d("ludo : ", "BUSY");

            }
            else if(code == WifiP2pManager.ERROR)
                Log.d("ludo : ", "ERROR");

             }

        });


    }





    @Override
    public void onFailure(int reason) {

        Log.d("ludo :", "connect failed. Code : " + Integer.toString(reason));
    }
}
