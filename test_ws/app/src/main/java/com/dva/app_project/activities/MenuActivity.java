package com.dva.app_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dva.app_project.R;
import com.dva.app_project.internet.SendString;

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
                MoveObjectList.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(MoveObjectList);
            }
        });

        //물건 찾기 화면으로 전환
        searchobject = findViewById(R.id.SearchObject);
        searchobject.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                //로봇에게 실행 할 프로그램 이름 전달
                Runnable r = new SendString("192.168.0.6", 5001, "SearchObject.py");//로봇 ip 알아내면 해당 ip 집어 넣기
                Thread t = new Thread(r);
                t.start();

                Intent MoveSearchObject = new Intent(getApplicationContext(), SearchObjectActivity.class);
                MoveSearchObject.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(MoveSearchObject);
            }
        });

        //로봇 조종 화면으로 전환
        robotcontrol = findViewById(R.id.RobotControl);
        robotcontrol.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                //로봇에게 실행 할 프로그램 이름 전달
                Runnable r = new SendString("192.168.0.6", 5001, "RobotControl.py");//로봇 ip 알아내면 해당 ip 집어 넣기
                Thread t = new Thread(r);
                t.start();

                Intent MoveRobotControl = new Intent(getApplicationContext(), RobotControlActivity.class);
                MoveRobotControl.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(MoveRobotControl);
            }
        });
    }
}