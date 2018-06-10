package com.dva.app_project.imagesocket;

import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendImage implements Runnable {
    String robotip;
    int port;
    File file;
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;

    public SendImage(String robotip, int port, File file) {
        this.robotip = robotip;
        this.port = port;
        this.file = file;
    }

    @Override
    public void run(){
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            byte[] bytes = new byte[(int)file.length()];
            bis.read(bytes);
           // byte[] result = Base64.encode(bytes, 0);
            Socket socket = new Socket(robotip, port);
            os = socket.getOutputStream();
            os.write((Integer.toString(bytes.length)+":::").getBytes());
            System.out.println(Integer.toString(bytes.length)+"----------------------");
           // System.out.println(new String(result));
            int i = bytes.length;
            int offset = 0;
            int send_size = 1024;
            while(i>0){
                if (i> send_size){
                    os.write(bytes,offset,send_size);
                }
                else{
                    byte[] buf = new byte[send_size];
                    for(int j = 0 ; j< send_size; j++){
                        buf[j] = 0;
                    }
                    for(int j = 0; j<i; j++){
                        buf[j]= bytes[offset+j];
                    }
                    os.write(buf);
//                    os.write(result,offset,i);

                }
                Thread.sleep(5);
                offset += send_size;
                i -= send_size;
            }
            System.out.println("img send end");

            os.close();
            bis.close();
            fis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
