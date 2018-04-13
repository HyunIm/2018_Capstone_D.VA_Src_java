/*
* @file : Login.java
* @author : LimHyun (hyunzion@gmail.com)
* @since : 2018 - 04 - 06
* @brief : 로그인 기능을 수행
* */

package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /*
    * @title : public void onButtonSignUp(View v)
    * @brief : SignUp 버튼을 누를 시 SignUp class로 이동
    * */
    public void onButtonSignUp(View v)
    {
        String result;
        Find_broadcast_ip broad_ip = new Find_broadcast_ip(this);
        Send_broadcast broadcast = new Send_broadcast(broad_ip.checkAvailableConnection());
        broadcast.start();
        try {
            //로봇 ip 받아올 객체 생성
            Recieve_robot_ip robot_addr = new Recieve_robot_ip();

            //쓰래드 시작
            robot_addr.start();

            //쓰래드가 끝날 때까지 대기
            robot_addr.join();

            //로봇 ip 저장
            result = robot_addr.get_ip();
            System.out.println("robot_ip is : "+result);

            Intent MoveSignUp = new Intent(getApplicationContext(), SignUp.class);
            MoveSignUp.putExtra("robot_ip", result);
            startActivity(MoveSignUp);
        }
        catch(IOException e) {
            System.out.println("fail_recieve_robot_ip");
        }
        catch (InterruptedException e){
            System.out.println("join problem");
        }
    }
}