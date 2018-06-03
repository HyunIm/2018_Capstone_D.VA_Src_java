package com.dva.app_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dva.app_project.R;

public class ObjectRegistrationActivity extends AppCompatActivity {
    Button register;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectregistration);

        //물건 등록 후 물건 리스트 화면으로 전환
        register = findViewById(R.id.Register);
        register.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveObjectList = new Intent(getApplicationContext(), ObjectListActivity.class);
                startActivity(MoveObjectList);
                finish();
            }
        });

        //물건 등록을 취소하고 물건 리스트 화면으로 전환
        cancel = findViewById(R.id.Cancel);
        cancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveObjectList = new Intent(getApplicationContext(), ObjectListActivity.class);
                startActivity(MoveObjectList);
                finish();
            }
        });
    }
}
