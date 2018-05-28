package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.io.IOException;

//SQL에 있는 데이터 정보를 리스트 목록에 나타낸다.

public class Thing_List_Management extends AppCompatActivity {
    Button register_btn;
    Button remove_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing__list__management);
         register_btn= (Button)findViewById(R.id.register);
         remove_btn=(Button)findViewById(R.id.remove);
    }

    public void onButtonRegister(View v)
    {
            Intent moveRegister =new Intent(getApplicationContext(),Registration_Thing.class);
            startActivity(moveRegister);
    }

    public void onButtonRemove(View v)
    {
           //삭제 버튼 누르면 리스트 삭제!!
    }

}
