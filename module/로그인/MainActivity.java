package com.example.a201311177.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

//버튼 누르면 로봇에게 브로트캐스트 패킷을 보내는 코드 입니다.
public class MainActivity extends AppCompatActivity {
    //메인 코드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 객체 생성
        final Button button = findViewById(R.id.button);

        //내부망의 브로드케스트 ip 구하는 객체 생성
        final Find_broadcast_ip ip_addr = new Find_broadcast_ip(this);

        //id가 있는 텍스트 객체
        final EditText id_text = findViewById(R.id.editText);

        //passwd가 있는 텍스트 객체
        final EditText pass_text = findViewById(R.id.editText2);

        //버튼 한번 클릭될 때 할 행동 정의
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result;
                //브로드캐스트 ip를 전달받아 문자열을 보내는 객체 생성
                //네트워크 동작은 모두 쓰래드로 처리해야 에러가 나지 않습니다.
                Broadcast_id_pass bro = new Broadcast_id_pass(ip_addr.checkAvailableConnection());
                //쓰래드 시작
                bro.start();

                //로봇 ip 받아와서 id, passwd 전송하기
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

                    //id, passwd 전송할 객체 생성
                    Send_id_pass id_pass = new Send_id_pass(result,id_text.getText().toString()+":::"+pass_text.getText().toString());

                    //쓰래드 시작
                    id_pass.start();

                    //쓰래드가 끝날때 까지 대기
                    id_pass.join();

                    //화면 전환
                    Intent intent = new Intent(getApplicationContext(), Next_fame.class);

                    //화면 전환시 로봇 ip를 넘겨주기
                    intent.putExtra("robot_ip",result);
                    startActivity(intent);
                }
                catch(IOException e) {
                    System.out.println("fail_recieve_robot_ip");
                }
                catch (InterruptedException e){
                    System.out.println("join problem");
                }
            }
        });
    }
}