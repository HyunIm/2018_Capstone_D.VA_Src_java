package com.dva.app_project.internet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ReceiveString implements Runnable{
    private String ip;
    private int port;
    private String msg;
    Handler han = null;

    public ReceiveString(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public ReceiveString(String ip, int port, Handler han){
        this.ip = ip;
        this.port = port;
        this.han = han;
    }

    public void run(){
        if (han == null) {
            try {
                Socket socket = new Socket(this.ip, this.port);
                System.out.println("recv socket create");

                InputStream is = socket.getInputStream();
                byte[] msg_buf = new byte[1024];
                is.read(msg_buf);
                System.out.println("recv msg");

                msg = new String(msg_buf).split(":::")[0];
                System.out.println(msg);

                is.close();
                socket.close();
                System.out.println("recv thread end");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                Socket socket = new Socket(this.ip, this.port);
                System.out.println("recv socket create");

                InputStream is = socket.getInputStream();
                byte[] msg_buf = new byte[1024];
                is.read(msg_buf);
                System.out.println("recv msg");

                msg = new String(msg_buf).split(":::")[0];
                System.out.println(msg);

                Bundle bundle = new Bundle();
                bundle.putString("data", msg);
                Message hmsg = han.obtainMessage();
                hmsg.setData(bundle);
                han.sendMessage(hmsg);

                is.close();
                socket.close();
                System.out.println("recv thread end");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getMsg(){
        return msg;
    }
}
