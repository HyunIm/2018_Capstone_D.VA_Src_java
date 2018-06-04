package com.dva.app_project.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dva.app_project.R;

public class ObjectRegistrationActivity extends AppCompatActivity {
    Button register;
    String robotip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectregistration);

        //robot의 ip 가져오기
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        robotip = pref.getString("robotip","");

        //물건 등록 후 물건 리스트 화면으로 전환
        register = findViewById(R.id.Register);
        register.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveObjectList = new Intent(getApplicationContext(), ObjectListActivity.class);
                MoveObjectList.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(MoveObjectList);
                finish();
            }
        });
    }
}
