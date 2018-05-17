package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.Button;

public class SelectMenu extends AppCompatActivity {

    Button ThingList_btn;
    Button FindThing_btn;
    Button RobotControl_btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ThingList_btn=(Button)findViewById(R.id.ThingList);
        FindThing_btn=(Button)findViewById(R.id.FindThing);
        RobotControl_btn=(Button)findViewById(R.id.robotControl);
    }

    public void onButtonThingList(View v){
        Intent wThingList=new Intent(getApplicationContext(), Thing_List_Management.class);
        startActivity(wThingList);
    }

    public void onButtonFindThing(View v){
        Intent wFindThing = new Intent(getApplicationContext(), Find_Thing.class);
        startActivity(wFindThing);
    }

    public void onButtonRobotCntrol(View v){
        Intent MoveRobotControl = new Intent(getApplicationContext(), RobotControl.class);
        startActivity(MoveRobotControl);
    }
}
