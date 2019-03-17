package com.example.moi.shifumi.Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.moi.shifumi.Network.Fragments.StartFragment;

import static com.example.moi.shifumi.Network.ReadHandler.MESSAGE_READ;


public class SendReceive extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Handler handler;
    private ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P;
    private WriteThread writeThread;

    public SendReceive(Socket socket, ActiviteInitServeurWifiP2P activiteInitServeurWifiP2P) {
        this.socket = socket;
        this.activiteInitServeurWifiP2P = activiteInitServeurWifiP2P;
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

        } catch (IOException e){
            e.printStackTrace();
            Log.d("ludo", "erreur creation SendReceive");
        }
    }

    public void write(String mess){
        Log.d("ludo","WriteThread : writing " + mess);
        this.writeThread = new WriteThread(this.socket, this.activiteInitServeurWifiP2P.handler, mess.getBytes());
        this.writeThread.start();
    }


    @Override
    public void run(){
        Log.d("ludo", "sendReceive run");

        //activer bouton Play ici

        if(this.activiteInitServeurWifiP2P.etat.equals("serveur"))
            this.activiteInitServeurWifiP2P.serverStarted=true;
        else
            this.activiteInitServeurWifiP2P.clientStarted=true;
        byte[] buffer = new byte[1024];
        int bytes;
        while (socket != null) {
            try {
                bytes = inputStream.read(buffer);
                if (bytes > 0) {
                    this.activiteInitServeurWifiP2P.handler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
