package com.example.a201311177.dva_app_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;
import java.net.Socket;

public class Recieve_robot_image {
    private Socket s;
    String ip;
    int port;
    Bitmap bmp;
    public Recieve_robot_image(String host, int port) {
        this.ip = host;
        this.port = port;
    }
    public void saveFile() throws IOException {
        s = new Socket(ip, port);
        InputStream in = s.getInputStream();
        byte[] img = new byte[307200];
        in.read(img, 0, img.length);
        bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
        s.close();
    }

    public Bitmap getBmp() {
        return bmp;
    }
}
