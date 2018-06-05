package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectMenu extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButtonThingList(View v){
        Intent wThingList=new Intent(getApplicationContext(), Thing_List_Management.class);
        startActivity(wThingList);
    }

    public void onButtonFindThing(View v){
        Intent wFindThing = new Intent(getApplicationContext(), Find_Thing.class);
        startActivity(wFindThing);
    }

    public void onButtonRobotControl(View v){
        Intent MoveRobotControl = new Intent(getApplicationContext(), RobotControl.class);
        Intent previous = getIntent();
        String result = previous.getStringExtra("robot_ip");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(result);
        MoveRobotControl.putExtra("robot_ip", result);
        startActivity(MoveRobotControl);
    }
}
