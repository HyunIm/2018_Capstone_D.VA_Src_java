package com.example.a201311177.myapplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

//로봇이 보내준 ip(사실은 ":::"이 나오기 전까지의 그냥 문자열)를 받아오는 클래스
public class Recieve_robot_ip extends Thread{
    private ServerSocket server = null;
    private Socket client = null;
    private InetSocketAddress ip = null;
    private String result;
    public Recieve_robot_ip() throws IOException{
        //서버 소켓 초기화
        server = new ServerSocket();
        ip = new InetSocketAddress(12222);
    }
    public String recieve_result() {
        try {
            //서버 수행
            server.bind(ip);

            //클라이언트 접근을 대기
            client = server.accept();

            //클라이언트가 보내준 값 읽기
            InputStream reciever = client.getInputStream();
            byte[] data = new byte[1024];
            reciever.read(data, 0, data.length);

            //클라이언트가 보내준 값을 문자열로 변환
            String robot_ip = new String(data);
            server.close();
            client.close();
            robot_ip = robot_ip.substring(0,robot_ip.indexOf(":::"));
            return robot_ip;
        } catch (IOException e) {
            return "fail";
        }
    }

    //쓰래드는 리턴값을 가지지 못하므로 getter 만듦
    public String get_ip(){
        return result;
    }

    //쓰래드 실행 메소드
    public void run(){
        result = recieve_result();
    }
}
