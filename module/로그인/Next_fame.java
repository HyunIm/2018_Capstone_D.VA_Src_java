package com.example.a201311177.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Next_fame  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_next);  // layout xml 과 자바파일을 연결

        //텍스트 뷰 객체 생성
        TextView textView =(TextView)findViewById(R.id.textView);

        //인텐트로 넘겨준 스트링 받기
        Intent intent = getIntent();
        String robot_ip = intent.getStringExtra("robot_ip");

        //텍스트 뷰에 받은 스트링 출력
        textView.setText(robot_ip);
    } // end onCreate()
} // end MyTwo