package com.example.a201311177.dva_app_project;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//SQL에 있는 데이터 정보를 리스트 목록에 나타낸다.

public class Thing_List_Management extends AppCompatActivity {

    private ListView itemList;
    ArrayAdapter adapter;
    Cursor cursor;//객체가 없다.
    DatabaseHelper hp;
    SQLiteDatabase db;
    List<String> arrayList = new ArrayList<>();
    List<String> photoarrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing__list__management);
        itemList = (ListView) findViewById(R.id.itemlist);
        hp = new DatabaseHelper(getApplicationContext(), "Photo.db", null, 1);//객체생성
        arrayList=Arrays.asList(hp.nameResult());
        SQLiteDatabase db;
        itemList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        itemList.setItemsCanFocus(false);
//        DBAdapter db=new DBAdapter(this,cursor);
        try {
            //객체생성!!
            adapter = new ArrayAdapter<String>(Thing_List_Management.this, android.R.layout.simple_list_item_multiple_choice, arrayList);   // ArrayAdapter(this, 출력모양, 배열)
            itemList.setAdapter(adapter);
            
            // Test 출력용
            for (int index = 0; index < arrayList.size(); index++) {
                System.out.println(arrayList.get(index));
                System.out.println(hp.nameResult()[index]);
            }
        } catch (Exception e) {
            System.out.println("select Error :  " + e);
        }

    }

    public void onButtonRegister(View v) {
        Intent moveRegister = new Intent(getApplicationContext(), Registration_Thing.class);
        startActivity(moveRegister);
        finish();
    }

    public void onButtonRemove(View v) {
        //삭제 버튼 누르면 리스트 삭제!!
        //선택된 포지션의
        arrayList=Arrays.asList(hp.nameResult());
        photoarrayList=Arrays.asList(hp.photopathResult());
        int countarray,checked,countad;
        countarray = arrayList.size();

//        System.out.println("arraylist:"+countarray);
//        System.out.println("photoarraylist:"+photoarrayList.size());
        countad=adapter.getCount();
        System.out.println(countad);
        if (countarray > 0) {
            //현재 선택된 아이템의 position 획득.
            SparseBooleanArray a = itemList.getCheckedItemPositions();
            for(int i=0;i<adapter.getCount();i++)
            {
                if(a.get(i))
                {
                    arrayList.set(i,null);
                    photoarrayList.set(i,null);

                }
            }
            hp.delete();
//            countarray=arrayList.size();
//            countad=adapter.getCount();
//
//            System.out.println(arrayList.size());
//            System.out.println(photoarrayList.size());

            //데이터베이스 리스트에 있는 그대로 추가
            for(int i=0;i<arrayList.size();i++)
            {
                if(photoarrayList.get(i)!=null&&arrayList.get(i)!=null)
                    hp.insert(photoarrayList.get(i),arrayList.get(i));
                if(photoarrayList.get(i)==null&&arrayList.get(i)==null)
                    adapter.remove(i);
            }

                itemList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                // listview 갱신. 일단 삭제는 살짝 화면이 전환되는 문제 있음 나중에 수정해야할 듯..!!

                Intent in=new Intent(getApplicationContext(),Thing_List_Management.class);
                startActivity(in);
                finish();
//            }
        }

    }

}
