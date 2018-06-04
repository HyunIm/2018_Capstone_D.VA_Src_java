package com.dva.app_project.internet;

import com.dva.app_project.broadcast_and_get_robot_ip.RecieveRobotIp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class RecieveString implements Runnable{
    private String ip;
    private int port;
    private String msg;

    public RecieveString(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void run(){
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

    public String getMsg(){
        return msg;
    }
}
