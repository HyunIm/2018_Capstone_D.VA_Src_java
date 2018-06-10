package com.dva.app_project.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dva.app_project.R;
import com.dva.app_project.imagesocket.SendImage;
import com.dva.app_project.internet.SendString;

import java.io.File;
import java.util.ArrayList;

public class SearchObjectActivity extends AppCompatActivity {
    ListView listView;
    File dir;
    File[] list;
    int port;
    String robotip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchobject);

        //robot의 ip 가져오기
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        robotip = pref.getString("robotip", "");

        listView = findViewById(R.id.itemlist);
        final ArrayList<String> items = new ArrayList<>();

        dir = getFilesDir();
        list = dir.listFiles();
        for(int i = 0; i< list.length; i++){
            System.out.println("-----------------------------------");
            System.out.println(list[i].getName());
            // if(list[i].getName().contains(".png")){
            items.add(list[i].getName());
            // }
        }

        CustomAdapter adapter = new CustomAdapter(this, 0, items, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = items.get(position);
                s = s.substring(0,s.indexOf('.'));
                print_toast(s+"가 선택되었습니다.");

                int num = 0;
                for(int i = 0; i<list.length ;i++){
                    if(list[i].getName().equals(items.get(position))){
                        num = i;
                        break;
                    }
                }

                //로봇에게 이미지 전송
                port = getResources().getInteger(R.integer.stringclassifyport);
                Runnable r_ss = new SendString(robotip, port, "SearchObject.py");
                Thread t_ss = new Thread(r_ss);
                t_ss.start();
                try {
                    t_ss.join();
                    Thread.sleep(10);
                    port = getResources().getInteger(R.integer.searchobjectport);
                    Runnable r_si = new SendImage(robotip, port, list[num]);
                    Thread t_si = new Thread(r_si);
                    t_si.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;
        private File[] files = null;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects, File[] files) {
            super(context, textViewResourceId, objects);
            this.items = objects;
            this.files = files;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.listview_item, null);
            }

            // ImageView 인스턴스
            ImageView imageView = (ImageView)v.findViewById(R.id.listimage);

            // 리스트뷰의 아이템에 이미지를 변경한다.
            Bitmap src = BitmapFactory.decodeFile(files[position].getAbsolutePath());
            if(src != null){
                //src = Bitmap.createScaledBitmap(src, 300, 150, false);
                imageView.setImageBitmap(src);
            }

            TextView textView = (TextView)v.findViewById(R.id.listname);
            String s = items.get(position);
            s = s.substring(0,s.indexOf('.'));
            textView.setText(s);

            return v;
        }
    }

    private void print_toast(String toastmsg) {
        //로봇과 연결 실패
        Toast.makeText(getApplicationContext(), toastmsg, Toast.LENGTH_LONG).show();
    }
}
