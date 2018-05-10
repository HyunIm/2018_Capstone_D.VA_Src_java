package com.example.a201311177.dva_app_project;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.net.Socket;

import android.view.View;


public class RobotControl extends AppCompatActivity {
    ImageView imageView;
    Handler handler = new Handler();
    String direction;
    String robot_ip;
    Recv_img image = null;
    ImageButton forward_btn;
    ImageButton turn_left_btn;
    ImageButton turn_right_btn;
    ImageButton reverse_btn;
    Button stop;
    Socket socket = null;
    Send_string ss;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_control);
//        imageView = findViewById(R.id.imageView);
        Intent previous = getIntent();
        robot_ip = previous.getStringExtra("robot_ip");
        forward_btn = findViewById(R.id.forward_btn);
        forward_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                direction = "go_forward";
                System.out.println(direction);
                ss = new Send_string(robot_ip, 12225, direction);
                ss.start();
                return false;
            }
        });
        turn_left_btn = findViewById(R.id.turn_left_btn);
        turn_left_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                direction = "turn__left";
                System.out.println(direction);
                ss = new Send_string(robot_ip, 12225, direction);
                ss.start();
                return false;
            }
        });
        turn_right_btn = findViewById(R.id.turn_right_btn);
        turn_right_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                direction = "turn_right";
                System.out.println(direction);
                ss = new Send_string(robot_ip, 12225, direction);
                ss.start();
                return false;
            }
        });
        reverse_btn = findViewById(R.id.reverse_btn);
        reverse_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                direction = "move__back";
                System.out.println(direction);
                ss = new Send_string(robot_ip, 12225, direction);
                ss.start();
                return false;
            }
        });
        stop = findViewById(R.id.stop);
        stop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                direction = "donot_move";
                System.out.println(direction);
                ss = new Send_string(robot_ip, 12225, direction);
                ss.start();
                return false;
            }
        });
        //image = new Recv_img(robot_ip, 12224, imageView);
        //startAnimation();
//        Recv_img image = new Recv_img(robot_ip, 12224, imageView);
//        while(true) {
//            image.start();
//            try {
//                image.join();
//                byte[] img = image.getImg_data();
//                System.out.println("--------------------------------------------------------");
//                System.out.println(img.length);
//                Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
//                imageView.setImageBitmap(bmp);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void onButtonForward(View v)
    {
        direction = "go_forward";
        System.out.println(direction);
        ss = new Send_string(robot_ip, 12225, direction);
        ss.start();
    }

    public void onButtonRight(View v)
    {
        direction = "turn_right";
        System.out.println(direction);
        ss = new Send_string(robot_ip, 12225, direction);
        ss.start();
    }

    public void onButtonLeft(View v)
    {
        direction = "turn__left";
        System.out.println(direction);
        ss = new Send_string(robot_ip, 12225, direction);
        ss.start();
    }

    public void onButtonReverse(View v)
    {
        direction = "move__back";
        System.out.println(direction);
        ss = new Send_string(robot_ip, 12225, direction);
        ss.start();
    }

    public void onButtonStop(View v){
        direction = "donot_move";
        System.out.println(direction);
        ss = new Send_string(robot_ip, 12225, direction);
        ss.start();
    }

//    public void startAnimation() {
//
//        AnimThread thread = new AnimThread();
//        thread.start();
//    }

    // Thread
//    class AnimThread extends Thread {
//        public void run() {
//            while (true) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            //image.start();
//                            //image.join();
//                            image.make_connect(socket);
//                            byte[] img = image.getImg_data();
//                            System.out.println("--------------------------------------------------------");
//                            System.out.println(img.length);
//                            Bitmap bmp = BitmapFactory.decodeByteArray(img, 0, img.length);
//                            imageView.setImageBitmap(bmp);
//                        }
////                          catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
//                        catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                try {
//                    // 지속 시간 변경
//                    Thread.sleep(1000);
//                } catch(Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
