package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

import android.view.View;
import android.graphics.Bitmap;

public class RobotControl extends AppCompatActivity {
    ImageView imageView;
    Handler handler = new Handler();
    String direction;
    String robot_ip;
    Recieve_robot_image image = null;
    ImageButton forward_btn;
    ImageButton forward_left_btn;
    ImageButton forward_right_btn;
    ImageButton reverse_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_control);
        Intent RobotControl = new Intent();
        robot_ip = RobotControl.getStringExtra("robot_ip");
        //forward_btn = (ImageButton) findViewById(R.id.forward_btn);
        //forward_right_btn = (ImageButton) findViewById(R.id.forward_right_btn);
        //forward_left_btn = (ImageButton) findViewById(R.id.forward_left_btn);
        //reverse_btn = (ImageButton) findViewById(R.id.reverse_btn);
        startAnimation();
    }

    public void onButtonForward(View v)
    {
        direction = "go forward";
        System.out.println(direction);
    }

    public void onButtonRight(View v)
    {
        direction = "turn right";
        System.out.println(direction);
    }

    public void onButtonLeft(View v)
    {
        direction = "turn left";
        System.out.println(direction);
    }

    public void onButtonReverse(View v)
    {
        direction = "go back";
        System.out.println(direction);
    }

    public void startAnimation() {

        AnimThread thread = new AnimThread();
        thread.start();
    }

    // Thread
    class AnimThread extends Thread {
        public void run() {
            while (true) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            image = new Recieve_robot_image(robot_ip,5001);
                            image.saveFile();
                            Bitmap img = image.getBmp();
                            imageView.setImageBitmap(Bitmap.createScaledBitmap(img,imageView.getWidth(),imageView.getHeight(),false));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                try {
                    // 지속 시간 변경
                    Thread.sleep(1000);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
