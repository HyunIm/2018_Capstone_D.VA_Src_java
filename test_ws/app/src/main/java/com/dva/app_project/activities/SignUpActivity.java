package com.dva.app_project.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dva.app_project.R;
import com.dva.app_project.internet.SendRecvString;
import com.dva.app_project.internet.SendString;

public class SignUpActivity extends AppCompatActivity {
    Button signup;
    EditText edit_id;
    EditText edit_pass;
    EditText edit_serial;
    int port;
    String robotip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edit_id = findViewById(R.id.editText_ID);
        edit_pass = findViewById(R.id.editText_Password);
        edit_serial = findViewById(R.id.editText_SerialNumber);

        //로봇 ip가져오기
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        robotip = pref.getString("robotip","");

        //회원 가입 후 로그인 화면으로 전환
        signup = findViewById(R.id.SignUp);
        signup.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SingUp 모듈이 사용하는 ip와 port로 회원가입 정보 보내는 코드
                String id = edit_id.getText().toString();
                if(id.length() == 0){
                    print_toast("ID를 입력하세요!");
                }
                else{
                    String pass = edit_pass.getText().toString();
                    if(pass.length() == 0){
                        print_toast("PassWord를 입력하세요!");
                    }
                    else{
                        String serial = edit_serial.getText().toString();
                        if(serial.length() == 0){
                            print_toast("Serial 번호를 입력하세요!");
                        }
                        else{
                            //포트를 구하고 로봇에게 실행 할 프로그램 이름 전달(회원 가입 모듈 열기)
                            port = getResources().getInteger(R.integer.stringclassifyport);
                            Runnable r_ss = new SendString(robotip, port, "SignUp.py");//로봇 ip 알아내면 해당 ip 집어 넣기
                            Thread t_ss = new Thread(r_ss);
                            t_ss.start();

                            //회원 가입 정보 전달
                            port = getResources().getInteger(R.integer.signupinfoport);
                            SendRecvString srs = new SendRecvString(robotip, port, id+":::"+pass+":::"+serial+":::");
                            Runnable r_srs = srs;
                            Thread t_srs = new Thread(r_srs);
                            t_srs.start();
                            try {
                                t_srs.join();
                                String signupinfo = srs.getResult();
                                if(signupinfo.equals("yes")){
                                    print_toast("가입 되었습니다!");
                                    Intent MoveLogin = new Intent(getApplicationContext(), LoginActivity.class);
                                    MoveLogin.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                    startActivity(MoveLogin);
                                    finish();
                                }
                                else{
                                    if(signupinfo.equals("noid")){
                                        print_toast("이미 존재하는 ID 입니다.");
                                    }
                                    else{
                                        print_toast("serial 번호를 확인해 주세요!");
                                    }
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    private void print_toast(String toastmsg) {
        //로봇과 연결 실패
        Toast.makeText(getApplicationContext(), toastmsg, Toast.LENGTH_LONG).show();
    }
}
