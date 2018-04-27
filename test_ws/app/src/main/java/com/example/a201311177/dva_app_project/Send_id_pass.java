package com.example.a201311177.dva_app_project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
//import java.net.UnknownHostException;

//서버로 id와 passwd를 보내는 클래스
public class Send_id_pass extends Thread{
    private Socket client = null;
    private String con_ip_pass, robot_ip;
    String signup_result;

    public Send_id_pass(String robot_ip, String con_ip_pass) {//throws UnknownHostException, IOException {
        this.robot_ip = robot_ip;
        this.con_ip_pass = con_ip_pass;
    }

    //받은 id와 passwd를 보내는 메소드
    public String Send_data() throws IOException {
        String result;
        //소켓 생성
        client = new Socket(robot_ip, 12223);

        //데이터를 보낼 객체 생성
        OutputStream output = client.getOutputStream();

        InputStream input = client.getInputStream();

        //id와 passwd를 구분자(":::")를 두고 합치기
        byte[] data = con_ip_pass.getBytes();

        //데이터 보내기
        output.write(data);

        data = new byte[1024];
        input.read(data,0,data.length);
        result = new String(data);
        System.out.println(result);
        result = result.substring(0,result.indexOf(":::"));
        System.out.println(result);
        return result;
    }

    public String get_result(){return signup_result;}

    //쓰래드 실행 메소드
    public void run(){
        try {
            signup_result = Send_data();
        }
        catch (IOException e){
            System.out.println("fail_send_id");
        }
    }
}