package com.dva.app_project.internet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class SendStringInfinite implements Runnable {
    private String ip;
    private int port;
    private String msg;

    public SendStringInfinite(String ip, int port, String msg){
        this.ip = ip;
        this.port = port;
        this.msg = msg+":::";
    }

    public void run(){
        try {
            Socket socket = new Socket(this.ip, this.port);
            System.out.println("socket create");

            OutputStream os = socket.getOutputStream();
            int i = 10;
            while(i>0) {
                os.write(msg.getBytes());
                os.flush();
                System.out.println("send msg");
                i--;
                Thread.sleep(100);
            }
            os.close();
            socket.close();
            System.out.println("thread end");
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
