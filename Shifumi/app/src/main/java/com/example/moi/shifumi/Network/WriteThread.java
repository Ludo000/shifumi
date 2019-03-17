package com.example.moi.shifumi.Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import android.os.Handler;
import android.util.Log;


public class WriteThread extends Thread {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Handler handler;
    private byte[] bytes;

    public WriteThread(Socket socket, Handler handler, byte[] bytes) {
        this.socket = socket;
        this.bytes = bytes;
        try{
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

        } catch (IOException e){
            e.printStackTrace();
            Log.d("ludo", "erreur creation WriteThread");
        }
        this.handler = handler;
    }


    @Override
    public void run() {
        try {
            Log.d("ludo","WriteThread : writing " + bytes.toString());
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
