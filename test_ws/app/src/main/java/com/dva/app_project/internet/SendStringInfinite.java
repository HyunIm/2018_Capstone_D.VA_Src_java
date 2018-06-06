package com.dva.app_project.internet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class SendStringInfinite implements Runnable {
    private String ip;
    private int port;
    private String msg;
    private boolean flag = true;

    public SendStringInfinite(String ip, int port, String msg) {
        this.ip = ip;
        this.port = port;
        this.msg = msg + ":::";
    }

    public void run() {
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
