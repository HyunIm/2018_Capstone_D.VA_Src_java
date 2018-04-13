/*
 * @file : SignUp.java
 * @author : LimHyun (hyunzion@gmail.com)
 * @since : 2018 - 04 - 06
 * @brief : 회원가입 기능을 수행
 * */

package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText editText_ID;
    EditText editText_Password;
    EditText editText_SerialNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editText_ID = (EditText) findViewById(R.id.editText_ID);
        editText_Password = (EditText) findViewById(R.id.editText_Password);
        editText_SerialNumber = (EditText) findViewById(R.id.editText_SerialNumber);
    }

    /*
    * @title : public void onButtonSignUp(View v)
    * @brief : SignUp 버튼을 누를 시 기능을 수행
    * */
    public void onButtonSignUp(View v)
    {
        Intent intent = getIntent();
        String robot_ip = intent.getStringExtra("robot_ip");
        // editText로 입력받고, 입력값이 0이라면 입력하라고 요구
        String ID = editText_ID.getText().toString();
        if (ID.length() == 0)
            Toast.makeText(getApplicationContext(), "Input Your ID", Toast.LENGTH_LONG). show();
        String Password = editText_Password.getText().toString();
        if (Password.length() == 0)
            Toast.makeText(getApplicationContext(), "Input Your Password", Toast.LENGTH_LONG). show();
        String SerialNumber = editText_SerialNumber.getText().toString();
        if (SerialNumber.length() == 0)
            Toast.makeText(getApplicationContext(), "Input Your Serial Number", Toast.LENGTH_LONG). show();

        try {
            Send_id_pass id_pass = new Send_id_pass(robot_ip, ID + ":::" + Password + ":::" + SerialNumber);
            id_pass.start();
            id_pass.join();
            String TrueSerialNumber = id_pass.get_result();

            // Serial Number가 맞다면 Login Class로 돌아가고, ID + ":::" + Password + ":::" + Serial Number를 반환
            if ("yes".equals(TrueSerialNumber)) {
                //Toast.makeText(getApplicationContext(), ID + ":::" + Password + ":::" + SerialNumber, Toast.LENGTH_LONG). show();
                Intent MoveLogin = new Intent(getApplicationContext(), Login.class);
                startActivity(MoveLogin);
            }

            // 만약 Serial Number가 일치하지 않다면, 확인 요구
            else {
                Toast.makeText(getApplicationContext(), "Check Your Serial Number", Toast.LENGTH_LONG). show();
            }
        }
        catch (InterruptedException e){
            System.out.println("join problem");
        }
    }
}
