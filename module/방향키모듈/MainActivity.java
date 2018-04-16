package com.example.wonti.movekey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private OutputStream outputStream;
    Button forward_btn = (Button) findViewById(R.id.forward_btn);
    Button forward_left_btn = (Button) findViewById(R.id.forward_left_btn);
    Button forward_right_btn = (Button) findViewById(R.id.forward_right_btn);
    Button reverse_btn = (Button) findViewById(R.id.reverse_btn);
    String direction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaration of button


        //Listener

        forward_btn.setOnTouchListener(new Button.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                /*//혹시 시작 터치만 했을 경우도 메시지 다이렉션에 넣어놧는데 필요없을 경우 삭제해도될듯
                if (event.getAction() == MotionEvent.ACTION_DOWN)//if it is touched , it make event.
                {
                    direction = "go forward";

                    //try {
                      //  outputStream.write(direction.getBytes());

                   // } catch (IOException e) {
                   //     e.printStackTrace();
                   // }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    direction = "go forward";

                    *//*try {
                        outputStream.write(direction.getBytes());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }*//*
                }
                return false;
            }*/

                return true;
            }
                });
//
//        forward_left_btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//                {
//
//                    if (event.getAction() == MotionEvent.ACTION_DOWN)//if it is touched , it make event.
//                    {
//                        direction = "turn left";
//
//                        try {
//                            outputStream.write(direction.getBytes());
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                        direction = "turn left";
//
//                        try {
//                            outputStream.write(direction.getBytes());
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return false;
//                }
//
//        });
//
//        forward_right_btn.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event){
//
//
//                    if (event.getAction() == MotionEvent.ACTION_DOWN)//if it is touched , it make event.
//                    {
//                        direction = "turn right";
//
//                        try {
//                            outputStream.write(direction.getBytes());
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                        direction = "turn right";
//
//                        try {
//                            outputStream.write(direction.getBytes());
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return false;
//                }
//
//        });
//
//        reverse_btn.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event)
//                    {
//
//                        if (event.getAction() == MotionEvent.ACTION_DOWN)//if it is touched , it make event.
//                        {
//                            direction = "go back";
//
//                            try {
//                                outputStream.write(direction.getBytes());
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                            direction = "go back";
//
//                            try {
//                                outputStream.write(direction.getBytes());
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        return false;
//                    }
//
//    });
//
        }
    }