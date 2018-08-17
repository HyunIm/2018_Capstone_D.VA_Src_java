package com.dva.app_project.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dva.app_project.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ObjectRegistrationActivity extends AppCompatActivity {
    ImageView photoview;
    Button register;
    Button selectphoto;
    EditText name;
    private final int GALLERY_CODE = 1112;
    Bitmap img = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectregistration);
        photoview = findViewById(R.id.PhotoView);
        name = findViewById(R.id.editText_name);

        //물건 등록 후 물건 리스트 화면으로 전환
        register = findViewById(R.id.Register);
        register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //사진이 없을 경우
                if (img == null) {
                    print_toast("사진을 등록해 주세요!");
                } else {
                    //이름을 적지 않았을 경우
                    String img_name = name.getText().toString();
                    if (img_name.length() == 0) {
                        print_toast("사진의 이름을 적어주세요!");
                    } else {
                        //적은 이름이 이미 있는 경우
                        boolean checker = false;
                        File dir = getFilesDir();
                        File[] list = dir.listFiles();
                        for (int i = 0; i < list.length; i++) {
                            if (list[i].getName().equals(img_name+".png")) {
                                checker = true;
                                break;
                            }
                        }
                        if (checker) {
                            print_toast("같은 이름의 물건이 있습니다.\n다른 이름으로 등록해 주세요!");
                        } else {
                            print_toast("저장 중 입니다...");
                            //가져온 사진을 내부 저장소에 저장
                            FileOutputStream outputStream = null;
                            try {
                                outputStream = openFileOutput(img_name + ".png", MODE_PRIVATE);
                                Bitmap result = Bitmap.createScaledBitmap(img, 300, 150, false);
                                result.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                outputStream.flush();
                                outputStream.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            print_toast("등록 되었습니다.");
                            Intent MoveObjectList = new Intent(getApplicationContext(), ObjectListActivity.class);
                            MoveObjectList.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(MoveObjectList);
                            finish();
                        }
                    }
                }
            }
        });

        //갤러리에서 사진 가져오기
        selectphoto = findViewById(R.id.SelectPhoto);
        selectphoto.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GALLERY_CODE:
                    //갤러리에서 가져온 사진을 이미지 뷰에 보여주기
                    try {
                        img = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                        photoview.setImageBitmap(img);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void print_toast(String toastmsg) {
        //로봇과 연결 실패
        Toast.makeText(getApplicationContext(), toastmsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        Intent MoveObjectList = new Intent(getApplicationContext(), ObjectListActivity.class);
        MoveObjectList.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(MoveObjectList);
        finish();
    }
}
