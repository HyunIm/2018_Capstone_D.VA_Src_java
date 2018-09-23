package com.dva.app_project.activities;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dva.app_project.R;
import com.dva.app_project.imagesocket.ReceiveImage;
import com.dva.app_project.internet.ReceiveString;
import com.dva.app_project.internet.SendRecvString;
import com.dva.app_project.internet.SendString;
import com.dva.app_project.internet.SendStringInfinite;

public class RobotControlActivity extends AppCompatActivity {
    ImageButton goforward;
    ImageButton goback;
    ImageButton turnleft;
    ImageButton turnright;
    Button stop;
    ImageView frame;
    TextView textView;
    Button button;
    int port;
    String robotip;
    Thread t_frame = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robotcontrol);
        textView = findViewById(R.id.textView);

        //로봇 ip가져오기
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        robotip = pref.getString("robotip", "");

        //영상 출력
        frame = findViewById(R.id.imageView);
        port = getResources().getInteger(R.integer.imagegetport);
        final Runnable r_frame = new ReceiveImage(robotip, port, han);
        t_frame = new Thread(r_frame);
        t_frame.start();

        //앞으로 이동
        goforward = findViewById(R.id.forward_btn);
        goforward.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                port = getResources().getInteger(R.integer.directionport);
                Runnable r_ss = new SendString(robotip, port, "RobotControl_base_forward.py");
                Thread t_ss = new Thread(r_ss);
                t_ss.start();
            }
        });

        goback = findViewById(R.id.reverse_btn);
        goback.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                port = getResources().getInteger(R.integer.directionport);
                Runnable r_ss = new SendString(robotip, port, "RobotControl_base_goback.py");
                Thread t_ss = new Thread(r_ss);
                t_ss.start();
            }
        });

        turnleft = findViewById(R.id.turn_left_btn);
        turnleft.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                port = getResources().getInteger(R.integer.directionport);
                Runnable r_ss = new SendString(robotip, port, "RobotControl_base_turnleft.py");
                Thread t_ss = new Thread(r_ss);
                t_ss.start();
            }
        });

        turnright = findViewById(R.id.turn_right_btn);
        turnright.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                port = getResources().getInteger(R.integer.directionport);
                Runnable r_ss = new SendString(robotip, port, "RobotControl_base_turnright.py");
                Thread t_ss = new Thread(r_ss);
                t_ss.start();
            }
        });

        stop = findViewById(R.id.stop);
        stop.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                port = getResources().getInteger(R.integer.directionport);
                Runnable r_ss = new SendString(robotip, port, "RobotControl_base_stop.py");
                Thread t_ss = new Thread(r_ss);
                t_ss.start();
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                textView.setText("잠시만 기다려주세요!");
                port = getResources().getInteger(R.integer.findobjectname);
                Runnable r_ss_find = new SendString(robotip, port, "now");
                Thread t_ss_find = new Thread(r_ss_find);
                t_ss_find.start();
                try {
                    t_ss_find.join();
                    try {
                        Thread.sleep(5000);
                        port = getResources().getInteger(R.integer.searchobject);
                        Runnable r_rs_search = new ReceiveString(robotip, port, han_text);
                        Thread t_rs_search = new Thread(r_rs_search);
                        t_rs_search.start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    Handler han = new Handler() {
        public void handleMessage(Message msg) {
            byte[] data = msg.getData().getByteArray("Data");
            frame.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
        }
    };

    Handler han_text = new Handler() {
        public void handleMessage(Message msg) {
            String data = msg.getData().getString("data","무엇인지 모르겠어요.");
            textView.setText(data+"입니다!!");
        }
    };

    @Override
    public void onStop(){
        Toast.makeText(getApplicationContext(), "로봇을 대기모드로 전환하였습니다.", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        t_frame.interrupt();
        port = getResources().getInteger(R.integer.directionport);
        Runnable r_ss_tomove = new SendString(robotip, port, "cancel");
        Thread t_ss_tomove = new Thread(r_ss_tomove);
        t_ss_tomove.start();

        port = getResources().getInteger(R.integer.findobjectname);
        Runnable r_ss_find = new SendString(robotip, port, "cancel");
        Thread t_ss_find = new Thread(r_ss_find);
        t_ss_find.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        port = getResources().getInteger(R.integer.closeport);
        Runnable r_ss = new SendString(robotip, port, "cancel");
        Thread t_ss = new Thread(r_ss);
        t_ss.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onBackPressed();
    }

    @Override
    public void onUserLeaveHint() {
        t_frame.interrupt();
        port = getResources().getInteger(R.integer.directionport);
        Runnable r_ss_tomove = new SendString(robotip, port, "cancel");
        Thread t_ss_tomove = new Thread(r_ss_tomove);
        t_ss_tomove.start();

        port = getResources().getInteger(R.integer.findobjectname);
        Runnable r_ss_find = new SendString(robotip, port, "cancel");
        Thread t_ss_find = new Thread(r_ss_find);
        t_ss_find.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        port = getResources().getInteger(R.integer.closeport);
        Runnable r_ss = new SendString(robotip, port, "cancel");
        Thread t_ss = new Thread(r_ss);
        t_ss.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onUserLeaveHint();
        super.onBackPressed();
    }

    private void print_toast(String toastmsg) {
        //로봇과 연결 실패
        Toast.makeText(getApplicationContext(), toastmsg, Toast.LENGTH_LONG).show();
    }
}
