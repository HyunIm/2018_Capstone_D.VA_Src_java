package com.example.a201311177.dva_app_project;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Send_string extends Thread{
    private String ip;
    private int port;
    private String msg;
    private Socket socket;
    private OutputStream os;

    public Send_string(String ip, int port, String msg) {
        this.ip = ip;
        this.port = port;
        this.msg = msg + ":::";
    }

    public void set_msg(String msg){
        this.msg = msg;
    }

    public void run() {
        try {
            this.socket = new Socket(this.ip, this.port);
            System.out.println("socket create");

            this.os = socket.getOutputStream();
            byte[] msg_bytes = this.msg.getBytes();
            this.os.write(msg_bytes);
            this.os.flush();
            System.out.println("send msg");

            this.os.close();
            this.socket.close();
            System.out.println("thread end");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("thread problem");
        }
    }
}