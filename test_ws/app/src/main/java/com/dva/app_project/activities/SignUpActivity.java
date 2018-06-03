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
        signup.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(MoveLogin);
                finish();
            }
        });
    }
}
