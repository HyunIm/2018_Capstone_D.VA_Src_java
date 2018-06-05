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
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;

public class Login extends AppCompatActivity {
    EditText editText_ID;
    EditText editText_Password;
    String TrueSerialNumber = "NO";
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_ID = (EditText) findViewById(R.id.editText_ID);
        editText_Password = (EditText) findViewById(R.id.editText_Password);
    }

    public void onButtonTest(View v) {
        Intent MoveTest = new Intent(getApplicationContext(), SelectMenu.class);
        startActivity(MoveTest);
    }

    /*
    * @title : public void onButtonSignUp(View v)
    * @brief : SignUp 버튼을 누를 시 SignUp class로 이동
    * */
    public void onButtonSignUp(View v) {
        Find_broadcast_ip broad_ip = new Find_broadcast_ip(this);
        Send_broadcast broadcast = new Send_broadcast(broad_ip.checkAvailableConnection());
        broadcast.start();
        try {
            //로봇 ip 받아올 객체 생성
            Recieve_robot_ip robot_addr = new Recieve_robot_ip();

            //쓰래드 시작
            robot_addr.start();
            System.out.println("1");
            //쓰래드가 끝날 때까지 대기
            robot_addr.join();
            System.out.println("2");
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

    public void onButtonLogin(View v) {
        Find_broadcast_ip broad_ip = new Find_broadcast_ip(this);
        Send_broadcast broadcast = new Send_broadcast(broad_ip.checkAvailableConnection());
        broadcast.start();
        try {
            String ID = editText_ID.getText().toString();
            String Password = editText_Password.getText().toString();
            if (ID.length() == 0 && Password.length() != 0)
                Toast.makeText(getApplicationContext(), "Input Your ID", Toast.LENGTH_LONG). show();
            else if (ID.length() == 0 && Password.length() == 0)
                Toast.makeText(getApplicationContext(), "Input Your ID and Password!", Toast.LENGTH_LONG). show();
            else
                Toast.makeText(getApplicationContext(), "Input Your Password", Toast.LENGTH_LONG). show();
            //로봇 ip 받아올 객체 생성
            Recieve_robot_ip robot_addr = new Recieve_robot_ip();

            //쓰래드 시작
            robot_addr.start();
            System.out.println("1");
            //쓰래드가 끝날 때까지 대기
            //쓰레드 조건 주어야한다~!~! 너무 오래 대기탐..
            robot_addr.join();
            System.out.println("2");
            //로봇 ip 저장
            result = robot_addr.get_ip();
            System.out.println("robot_ip is : "+ result);

            Send_id_pass id_pass = new Send_id_pass(result, ID + ":::" + Password );
            id_pass.start();
            id_pass.join();
            TrueSerialNumber = id_pass.get_result();

            // Serial Number가 맞다면 Login Class로 돌아가고, ID + ":::" + Password + ":::" + Serial Number를 반환
            if ("yes".equals(TrueSerialNumber)) {
                Toast.makeText(getApplicationContext(), "Log In!", Toast.LENGTH_LONG). show();
                Intent MoveSignUp = new Intent(getApplicationContext(), SelectMenu.class);
                MoveSignUp.putExtra("robot_ip", result);
                startActivity(MoveSignUp);
            }
            else
                Toast.makeText(getApplicationContext(), "Check your ID or Password!", Toast.LENGTH_LONG). show();
        }
        catch (IOException e) {
            System.out.println("fail_recieve_robot_ip");
        }
        catch (InterruptedException e) {
            System.out.println("join problem");
        }
    }
}