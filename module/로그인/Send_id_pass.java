package com.example.a201311177.myapplication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

//서버로 id와 passwd를 보내는 클래스
public class Send_id_pass extends Thread{
    private Socket client = null;
    private String con_ip_pass, robot_ip;

    public Send_id_pass(String robot_ip, String con_ip_pass) throws UnknownHostException, IOException {
        this.robot_ip = robot_ip;
        this.con_ip_pass = con_ip_pass;
    }

    //받은 id와 passwd를 보내는 메소드
    public String Send_data() throws IOException {

        //소켓 생성
        client = new Socket(robot_ip, 12223);

        //데이터를 보낼 객체 생성
        OutputStream output = client.getOutputStream();

        String result="success send id";

        //id와 passwd를 구분자(":::")를 두고 합치기
        //String id_pass = this.id+":::"+this.pass;
        //byte[] data = id_pass.getBytes();
        byte[] data = con_ip_pass.getBytes();

        //데이터 보내기
        output.write(data);
        return result;
    }

    //쓰래드 실행 메소드
    public void run(){
        try {
            String result = Send_data();
            System.out.println(result);
        }
        catch (IOException e){
            System.out.println("fail_send_id");
        }
    }
}
