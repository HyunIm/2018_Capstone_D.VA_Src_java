package com.dva.app_project.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dva.app_project.R;
import com.dva.app_project.internet.SendString;

public class LoginActivity extends AppCompatActivity {
    Button login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //로봇 ip알아 내는 코드 집어 넣기

        //로그인 후 메뉴 화면으로 전환
        login = findViewById(R.id.Login);
        login.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Login.py 가 사용하는 ip와 port로 id와 password 보내는 코드 집어 넣기
                Intent MoveMenu = new Intent(getApplicationContext(), MenuActivity.class);
                MoveMenu.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(MoveMenu);
            }
        });

        //회원 가입 화면으로 전환
        signup = findViewById(R.id.SignUp);
        signup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로봇에게 실행 할 프로그램 이름 전달(회원 가입 모듈 열기)
                Runnable r = new SendString("192.168.0.6", 5001, "SignUp.py");//로봇 ip 알아내면 해당 ip 집어 넣기
                Thread t = new Thread(r);
                t.start();

                Intent MoveSingUp = new Intent(getApplicationContext(), SignUpActivity.class);
                MoveSingUp.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(MoveSingUp);
            }
        });
    }
}
