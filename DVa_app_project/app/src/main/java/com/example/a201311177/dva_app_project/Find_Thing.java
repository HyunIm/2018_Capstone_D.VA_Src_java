package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Find_Thing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find__thing);
    }

    public void onButtonMenu(View v) {
        Intent MenuThing = new Intent(getApplicationContext(), SelectMenu.class);
        startActivity(MenuThing);
    }
}
