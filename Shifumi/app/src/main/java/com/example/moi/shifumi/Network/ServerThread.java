package com.example.moi.shifumi.Network;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerThread extends Thread{
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    public ServerThread(ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P){
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
    }


    @Override
    public void run() {
        try {
            Log.d("ludo", "ServerThread run");
            this.activiteInitServeurWifiP2P.serverSocket = new ServerSocket(8945);
            this.activiteInitServeurWifiP2P.socketS = this.activiteInitServeurWifiP2P.serverSocket.accept();

            this.activiteInitServeurWifiP2P.sendReceive = new SendReceive(this.activiteInitServeurWifiP2P.socketS, this.activiteInitServeurWifiP2P);
            this.activiteInitServeurWifiP2P.sendReceive.start();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ludo", "erreur serveur");
        }
    }
}