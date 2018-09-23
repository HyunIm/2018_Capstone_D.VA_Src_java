package com.dva.app_project.imagesocket;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveImage implements Runnable{
    DatagramSocket dsock;
    DatagramPacket dpack;
    String ip;
    int port;
    Handler han;
    int mod = 20;
    String[] msg_stack = new String[mod];
    int end = 0;


    public ReceiveImage(String ip, int port, Handler han) {
        this.ip = ip;
        this.port = port;
        this.han = han;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //System.out.println("-----------2----------------");
                byte[] length = new byte[1024];
                dsock = new DatagramSocket(port);
                dpack = new DatagramPacket(length, length.length);
                int msg_stack_count = 0;
                int buf_count = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    //System.out.println("-----------3----------------");
                    dsock.receive(dpack);
                    //System.out.println("-----------4----------------");
                    String msg = new String(dpack.getData(), 0, dpack.getLength());
                    //System.out.println("-----------4----------------");
                    msg = msg.split(":::")[0];
                    //System.out.println(msg);
                    try {
                        //System.out.println("-----------4----------------");
                        int len = Integer.parseInt(msg);
                        //System.out.println(len);
                        int i = len;
                        int read_size = 1024;
                        byte[] buf = new byte[read_size];
                        dpack = new DatagramPacket(buf, buf.length);
                        String s;
                        //StringBuffer sb = new StringBuffer();
                        String ss = "";
                        //System.out.println("-----------5----------------");
                        while (i > 0) {
                            dsock.receive(dpack);
                            s = new String(dpack.getData(), 0, dpack.getLength()).trim();
                            //sb.append(s);
                            ss += s;
                            i -= read_size;
                        }
                        //System.out.println("-----------6----------------");
                        //s = sb.toString();
                        msg_stack[msg_stack_count % mod] = ss;
                        msg_stack_count++;
                        buf_count++;
                        if (buf_count > mod) {
                            try {
                                //System.out.println("-----------7----------------");
                                byte[] bs = Base64.decode(msg_stack[msg_stack_count % mod], 0);
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("Data", bs);
                                Message hmsg = han.obtainMessage();
                                hmsg.setData(bundle);
                                han.sendMessage(hmsg);

                                //byte[] bs = Base64.getDecoder().decode(s.getBytes());
                            } catch (IllegalArgumentException e) {
                                //System.out.println("bases64decode");
                            } catch (NullPointerException e) {
                                System.out.println("ImageIcon");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("interger.parserint");

                    }
                }
            } catch (SocketException e) {
                System.out.println("socket");
                //dsock.close();
                //e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("socketio");
            }
        }
    }
}
