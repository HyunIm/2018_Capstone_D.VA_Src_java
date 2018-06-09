package com.dva.app_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dva.app_project.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ObjectListActivity extends AppCompatActivity {
    Button objectregistration;
    File dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectlist);

        dir = getFilesDir();
        File[] list = dir.listFiles();
        for(int i = 0; i< list.length; i++){
            System.out.println("-----------------------------------");
            System.out.println(list[i].getName());
        }
        //물건 등록 창으로 전환
        objectregistration = findViewById(R.id.Register);
        objectregistration.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveObjectRegistration = new Intent(getApplicationContext(), ObjectRegistrationActivity.class);
                startActivity(MoveObjectRegistration);
                finish();
            }
        });
    }

    private void print_toast(String toastmsg) {
        //로봇과 연결 실패
        Toast.makeText(getApplicationContext(), toastmsg, Toast.LENGTH_LONG).show();
    }
}
