package com.dva.app_project.internet;

import android.os.Handler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendString implements Runnable {
    private String ip;
    private int port;
    private String msg;
    Handler han = null;

    public SendString(String ip, int port, String msg){
        this.ip = ip;
        this.port = port;
        this.msg = msg+":::";
    }

    public SendString(String ip, int port, String msg, Handler han){
        this.ip = ip;
        this.port = port;
        this.msg = msg+":::";
        this.han = han;
    }

    public void run(){
        if(han == null) {
            try {
                Socket socket = new Socket(this.ip, this.port);
                System.out.println("socket create");

                OutputStream os = socket.getOutputStream();
                os.write(msg.getBytes());
                os.flush();
                System.out.println("send msg");

                os.close();
                socket.close();
                System.out.println("thread end");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                Socket socket = new Socket(this.ip, this.port);
                System.out.println("socket create");

                OutputStream os = socket.getOutputStream();
                os.write(msg.getBytes());
                os.flush();
                System.out.println("send msg");

                os.close();
                socket.close();
                System.out.println("thread end");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
