package com.dva.app_project.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.dva.app_project.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestActivity extends AppCompatActivity {
    File dir;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        imageView = findViewById(R.id.imageView2);

        dir = getFilesDir();
        File[] list = dir.listFiles();
        int num = -1;
        for(int i = 0; i< list.length; i++){
            System.out.println("-----------------------------------");
            System.out.println(list[i].getName());
            String text = list[i].getName();
            if(text.contains(".png")){
                num = i;
                break;
            }

//            if(list[i].getName().split(".")[1].equals("png")){
//                num = i;
//                break;
//            }
        }
        if(num != -1){
            try {

                FileInputStream fis = openFileInput(list[num].getName());
                FileDescriptor fd = fis.getFD();
                imageView.setImageBitmap(BitmapFactory.decodeFile(list[num].getAbsolutePath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
