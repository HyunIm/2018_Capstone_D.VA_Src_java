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

import com.dva.app_project.R;
import com.dva.app_project.imagesocket.ReceiveImage;
import com.dva.app_project.internet.SendString;
import com.dva.app_project.internet.SendStringInfinite;

public class RobotControlActivity extends AppCompatActivity {
    ImageButton goforward;
    ImageButton goback;
    ImageButton turnleft;
    ImageButton turnright;
    Button stop;
    ImageView frame;
    int port;
    String robotip;
    Thread t_frame = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robotcontrol);

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
    }

    Handler han = new Handler() {
        public void handleMessage(Message msg) {
            byte[] data = msg.getData().getByteArray("Data");
            frame.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
        }
    };

    @Override
    public void onBackPressed() {
        t_frame.interrupt();
        port = getResources().getInteger(R.integer.directionport);
        Runnable r_ss_tomove = new SendString(robotip, port, "cancel");
        Thread t_ss_tomove = new Thread(r_ss_tomove);
        t_ss_tomove.start();
        port = getResources().getInteger(R.integer.closeport);
        Runnable r_ss = new SendString(robotip, port, "cancel");
        Thread t_ss = new Thread(r_ss);
        t_ss.start();
        super.onBackPressed();
    }

    @Override
    public void onUserLeaveHint() {
        t_frame.interrupt();
        port = getResources().getInteger(R.integer.directionport);
        Runnable r_ss_tomove = new SendString(robotip, port, "cancel");
        Thread t_ss_tomove = new Thread(r_ss_tomove);
        t_ss_tomove.start();
        port = getResources().getInteger(R.integer.closeport);
        Runnable r_ss = new SendString(robotip, port, "cancel");
        Thread t_ss = new Thread(r_ss);
        t_ss.start();
        super.onUserLeaveHint();
        super.onBackPressed();
    }
}
