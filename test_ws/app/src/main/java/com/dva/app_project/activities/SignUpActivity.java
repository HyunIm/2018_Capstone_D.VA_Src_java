package com.dva.app_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dva.app_project.R;

public class SignUpActivity extends AppCompatActivity {
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //회원 가입 후 로그인 화면으로 전환
        signup = findViewById(R.id.SignUp);
        signup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SingUp 모듈이 사용하는 ip와 port로 회원가입 정보 보내는 코드 집어넣기

                Intent MoveLogin = new Intent(getApplicationContext(), LoginActivity.class);
                MoveLogin.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(MoveLogin);
                finish();
            }
        });
    }
}
