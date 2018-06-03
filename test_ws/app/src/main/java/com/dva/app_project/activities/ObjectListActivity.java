package com.dva.app_project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dva.app_project.R;

public class ObjectListActivity extends AppCompatActivity {
    Button objectregistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectlist);

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
}
