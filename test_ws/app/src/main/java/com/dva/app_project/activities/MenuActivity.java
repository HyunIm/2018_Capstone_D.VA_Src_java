package com.dva.app_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dva.app_project.R;

public class MenuActivity extends AppCompatActivity {
    Button objectlist;
    Button searchobject;
    Button robotcontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //물건 리스트 관리 화면으로 전환
        objectlist = findViewById(R.id.ObjectList);
        objectlist.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveObjectList = new Intent(getApplicationContext(), ObjectListActivity.class);
                startActivity(MoveObjectList);
            }
        });

        //물건 찾기 화면으로 전환
        searchobject = findViewById(R.id.SearchObject);
        searchobject.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveSearchObject = new Intent(getApplicationContext(), SearchObjectActivity.class);
                startActivity(MoveSearchObject);
            }
        });

        //로봇 조종 화면으로 전환
        robotcontrol = findViewById(R.id.RobotControl);
        robotcontrol.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent MoveRobotControl = new Intent(getApplicationContext(), RobotControlActivity.class);
                startActivity(MoveRobotControl);
            }
        });
    }
}