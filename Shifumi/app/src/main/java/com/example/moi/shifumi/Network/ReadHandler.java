package com.example.moi.shifumi.Network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.moi.shifumi.Network.Fragments.GameFragment;
import com.example.moi.shifumi.Network.Fragments.StartFragment;
import com.example.moi.shifumi.R;


public class ReadHandler implements Handler.Callback {
    static final int MESSAGE_READ=1;
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    public ReadHandler(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_READ:
                byte[] readBuff = (byte[]) msg.obj;
                String tempMsg = new String(readBuff, 0, msg.arg1);
                Log.d("ludo", "handler reçoit : " + tempMsg);

                String[] output = tempMsg.split("\\.");

                if(output[0].equals("start")){
                    this.activiteInitServeurWifiP2P.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new GameFragment(this.activiteInitServeurWifiP2P)).commit();
                }
                else if(output[0].equals("f") || output[0].equals("c") || output[0].equals("p") ){
                    this.activiteInitServeurWifiP2P.args.putString("guest", output[0]);
                    this.activiteInitServeurWifiP2P.challengerNAme = output[1];
                }
                else if(output[0].equals("exit")){
                    this.activiteInitServeurWifiP2P.disconnectPeers();
                    this.activiteInitServeurWifiP2P.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new StartFragment(this.activiteInitServeurWifiP2P)).commit();
                }

                break;
        }
        return true;
    }
}
