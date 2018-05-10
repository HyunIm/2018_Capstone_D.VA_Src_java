package com.example.a201311177.dva_app_project;

import android.provider.ContactsContract;
import android.widget.ImageView;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import android.graphics.Bitmap;

public class Recv_img extends Thread{
	String ip;
	int port;
	//Socket socket = null;
	byte[] img_data;
	ImageView imageView;
	Bitmap bmp = null;

	public Recv_img(String ip, int port, ImageView imageView) {
		this.ip = ip;
		this.port = port;
		this.imageView = imageView;
	}

//Socket
//	public InputStream connect() throws IOException {
//		System.out.println("1");
//		Socket socket = new Socket(robot_ip, 12224);
//		System.out.println("2");
//		InputStream in = socket.getInputStream();
//		return in;
//	}

	public byte[] make_connect(Socket socket) throws IOException {
		System.out.println("2");
		InputStream in = socket.getInputStream();
		System.out.println("3");
		byte[] length = new byte[1024];
		System.out.println("4");
		in.read(length, 0, length.length);
		System.out.println("5");
		String len = new String(length);
		System.out.println("6");
		len = len.trim();
		System.out.println("7");
		System.out.println(len);
		System.out.println("8");
		int l = Integer.parseInt(len);
		System.out.println("9");
		System.out.println(l);
		System.out.println("10");
		byte[] data = new byte[l];
		System.out.println("11");
		in.read(data, 0, l);
		System.out.println("12");
		return data;
	}

	public byte[] getImg_data(){
		return img_data;
	}

	public void run(){
		try {
			Socket socket = new Socket(ip, 5001);
			System.out.println("13");
			img_data = make_connect(socket);
			System.out.println("14");
//			bmp = BitmapFactory.decodeByteArray(img_data, 0, img_data.length);
//			imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp,imageView.getWidth(),imageView.getHeight(),false));
		} catch (IOException e) {
			System.out.println("/////////////////////////////////////////////////////////////////////////");
		}
	}
}
