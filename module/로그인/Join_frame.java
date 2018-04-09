package com.example.a201311177.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Join_frame extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_screen);
        final EditText join_id = findViewById(R.id.editText3);
        final EditText join_pass = findViewById(R.id.editText4);
        final EditText join_sn = findViewById(R.id.editText5);
        final Button join_btn = findViewById(R.id.button3);
        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String all_text;
                String result = "yes";
                all_text = join_id.getText().toString()+":::"+join_pass.getText().toString()+":::"+join_sn.getText().toString();

            }
        });
    }
}
