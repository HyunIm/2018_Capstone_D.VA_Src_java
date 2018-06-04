package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

//SQL에 있는 데이터 정보를 리스트 목록에 나타낸다.

public class Thing_List_Management extends AppCompatActivity {
    Button register_btn;
    Button remove_btn;
    private ListView itemList;
    ArrayAdapter adapter;
    Cursor cursor;//객체가 없다.
    DatabaseHelper hp;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing__list__management);
        register_btn = (Button) findViewById(R.id.register);
        remove_btn = (Button) findViewById(R.id.remove);
        itemList = (ListView) findViewById(R.id.itemlist);
        hp = new DatabaseHelper(getApplicationContext(), "Photo.db", null, 1);//객체생성
        itemList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        itemList.setItemsCanFocus(false);
        //nameresult에서
        //여기 객체 다시보기 여기부분 다시보기
//        DBAdapter db=new DBAdapter(this,cursor);
        try {
            //객체생성!!

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, hp.nameResult());   // ArrayAdapter(this, 출력모양, 배열)
            itemList.setAdapter(adapter);
        } catch (Exception e) {
            System.out.println("select Error :  " + e);
        }

        //충돌일어남@@!!!!!여기서부터
        //db를 가르키도록 해야함 cursor가

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), String.valueOf(itemList.getCheckedItemPositions()), Toast.LENGTH_LONG).show();
                // get TextView's Text.
//                cursor.moveToPosition(position);
//                String index = cursor.getString(cursor.getColumnIndex("_id"));
////                String strText = (String) parent.getItemAtPosition(position) ;
//                int ㅣ_id = Integer.parseInt(index);
            }
        });
        //여기까지 문제
    }

    //여기 이제 고치면 삭제 끝날듯....******
    public void onButtonRegister(View v) {
        Intent moveRegister = new Intent(getApplicationContext(), Registration_Thing.class);
        startActivity(moveRegister);
    }

    public void onButtonRemove(View v) {
        //삭제 버튼 누르면 리스트 삭제!!
        int count, checked;
        count = adapter.getCount();

        if (count > 0) {
            //현재 선택된 아이템의 position 획득.
            checked = itemList.getCheckedItemPosition();
            System.out.println(checked);
            if (checked > -1 && checked < count) {
                // 아이템 삭제

                adapter.remove(checked);

                // listview 선택 초기화.
                itemList.clearChoices();

                // listview 갱신.
                adapter.notifyDataSetChanged();
            }
        }

    }
}
