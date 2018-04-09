package com.example.a201311177.myapplication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//받은 브로드캐스트 ip로 문자열을 전달하는 클래스
public class Broadcast_id_pass extends Thread{
    private String broadcast_ip;
    //보낼 문자열
    private String msg = "where are you";
    private DatagramSocket socket = null;

    //클래스 생성시에 브로드캐스트 ip 받음
    public Broadcast_id_pass(String broadcast_ip){
        this.broadcast_ip = broadcast_ip;
        System.out.println("broadcast ip addr = "+ this.broadcast_ip);
    }

    //쓰래드 실행 메소드
    public void run(){
        try{
            //문자열 전송 메소드 호출
            broadcast(this.msg, InetAddress.getByName(this.broadcast_ip));
            //return "ok";
        }
        //전송 오류시
        catch (IOException e){
            //return "fail";
        }
    }

    //문자열 전송 메소드
    public void broadcast(String broadcastMessage, InetAddress address) throws IOException {
        //문자열을 String형에서 Byte형으로 전환
        byte[] buffer = broadcastMessage.getBytes();
        //문자열을 패킷으로 만듦
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 50000);
        //브로드캐스트 소켓 생성
        socket = new DatagramSocket();
        //브로드캐스트 주고 받기 허용
        socket.setBroadcast(true);
        //패킷 보내기
        socket.send(packet);
        //소켓 닫기
        socket.close();
    }
}