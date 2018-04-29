package com.example.a201311177.dva_app_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;

public class RobotControl extends AppCompatActivity {
    ImageView imageView;
    Handler handler = new Handler();
    String direction;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_control);//insert layout
        imageView = (ImageView) findViewById((R.id.imageView));
        Button forward_btn = (Button) findViewById(R.id.forward_btn);
        Button forward_left_btn = (Button) findViewById(R.id.forward_left_btn);
        Button forward_right_btn = (Button) findViewById(R.id.forward_right_btn);
        Button reverse_btn = (Button) findViewById(R.id.reverse_btn);
        startAnimation();

        forward_btn.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                direction = "go forward";
                System.out.println(direction);
                return true;
            }
        });

        forward_right_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                direction = "turn right";
                System.out.println(direction);
                return true;
            }
        });

        forward_left_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                direction = "turn left";
                System.out.println(direction);
                return true;
            }
        });

        reverse_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                direction = "go back";
                System.out.println(direction);
                return true;
            }
        });
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
                        Intent RobotControl = new Intent();
                        String robot_ip = RobotControl.getStringExtra("robot_ip");
                        Recieve_robot_image image = new Recieve_robot_image(robot_ip,12224);
                        try {
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
