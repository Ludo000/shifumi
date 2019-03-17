package com.example.moi.shifumi.Network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ClientThread extends Thread {
    String hostAdd;
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;

    public ClientThread(InetAddress hostAdress, ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P)
    {
        this.hostAdd = hostAdress.getHostAddress();
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }


    @Override
    public void run(){
        try{
            Log.d("ludo", "ClientThread run");
            this.activiteInitServeurWifiP2P.socketC.connect(new InetSocketAddress(this.hostAdd,8945), 500);
            this.activiteInitServeurWifiP2P.sendReceive = new SendReceive(this.activiteInitServeurWifiP2P.socketC, this.activiteInitServeurWifiP2P);
            this.activiteInitServeurWifiP2P.sendReceive.start();

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
