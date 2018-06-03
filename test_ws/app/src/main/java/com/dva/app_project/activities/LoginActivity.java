package com.dva.app_project.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dva.app_project.R;

public class LoginActivity extends AppCompatActivity {
    Button login;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //로그인 후 메뉴 화면으로 전환
        login = findViewById(R.id.Login);
        login.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveMenu = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(MoveMenu);
            }
        });

        //회원 가입 화면으로 전환
        signup = findViewById(R.id.SignUp);
        signup.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveSingUp = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(MoveSingUp);
                finish();
            }
        });
    }
}
