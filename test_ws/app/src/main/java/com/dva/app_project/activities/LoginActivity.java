package com.dva.app_project.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dva.app_project.R;
import com.dva.app_project.broadcast_and_get_robot_ip.FindBroadcastIp;
import com.dva.app_project.broadcast_and_get_robot_ip.ReceiveRobotIp;
import com.dva.app_project.broadcast_and_get_robot_ip.SendBroadcast;
import com.dva.app_project.internet.SendRecvString;
import com.dva.app_project.internet.SendString;

public class LoginActivity extends AppCompatActivity {
    Button login;
    Button signup;
    EditText edit_id;
    EditText edit_pass;
    String broadcastip;
    String robotip = "fail";
    int port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_id = findViewById(R.id.editText_ID);
        edit_pass = findViewById(R.id.editText_Password);

        //로봇 ip알아 내는 코드
        //브로드캐스트 ip알아 내기
        FindBroadcastIp fbi = new FindBroadcastIp(this);
        broadcastip = fbi.checkAvailableConnection();
        if (broadcastip.equals("Wifi not connected!")) {
            print_toast("Please connect wifi!!");
            finish();
        }
        //포트 구하고 로봇에게 브로드캐스트 하기
        port = getResources().getInteger(R.integer.broadcastport);
        Runnable r_sb = new SendBroadcast(broadcastip, port);
        Thread t_sb = new Thread(r_sb);
        t_sb.start();
        //로봇 ip 받기
        port = getResources().getInteger(R.integer.recvrobotipport);
        ReceiveRobotIp rri = new ReceiveRobotIp(port);
        Runnable r_rri = rri;
        Thread t_rri = new Thread(r_rri);
        t_rri.start();
        try {
            t_rri.join(10000);
            //로봇과 연결 실패시 종료
            robotip = rri.get_ip();
            if (robotip.equals("fail")) {
                print_toast("로봇을 와이파이에 연결해 주세요!");
                finish();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            print_toast("로봇을 와이파이에 연결해 주세요!");
            finish();
        }

        //클래스간 ip 공유
        SharedPreferences perf = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = perf.edit();
        editor.putString("robotip", robotip);
        editor.commit();

        //로그인 버튼 누를 때
        login = findViewById(R.id.Login);
        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Login.py 가 사용하는 ip와 port로 id와 password 보내는 코드
                //id를 입력했는지 확인
                String id = edit_id.getText().toString();
                if (id.length() == 0) {
                    print_toast("ID를 입력하세요!");
                } else {
                    //비밀번호 입력했는지 확인
                    String pass = edit_pass.getText().toString();
                    if (pass.length() == 0) {
                        print_toast("PassWord를 입력하세요!");
                    } else {
                        //로봇의 로그인 모듈 실행
                        port = getResources().getInteger(R.integer.stringclassifyport);
                        Runnable r_ss = new SendString(robotip, port, "Login.py");
                        Thread t_ss = new Thread(r_ss);
                        t_ss.start();
                        try {
                            t_ss.join();
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                        //로그인 정보 전달
                        port = getResources().getInteger(R.integer.logininfoport);
                        SendRecvString srs = new SendRecvString(robotip, port, id+":::"+pass+":::");
                        Runnable r_srs = srs;
                        Thread t_srs = new Thread(r_srs);
                        t_srs.start();
                        try {
                            t_srs.join();
                            String logininfo = srs.getResult();
                            if(logininfo.equals("yes")){
                                print_toast("Login!");
                                Intent MoveMenu = new Intent(getApplicationContext(), MenuActivity.class);
                                MoveMenu.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                startActivity(MoveMenu);
                            }
                            else{
                                print_toast("잘못된 아이디, 패스워드 입니다.");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        //회원 가입 버튼 누를 때
        signup = findViewById(R.id.SignUp);
        signup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MoveSingUp = new Intent(getApplicationContext(), SignUpActivity.class);
                MoveSingUp.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(MoveSingUp);

            }
        });
    }

    private void print_toast(String toastmsg) {
        //로봇과 연결 실패
        Toast.makeText(getApplicationContext(), toastmsg, Toast.LENGTH_LONG).show();
    }
}
