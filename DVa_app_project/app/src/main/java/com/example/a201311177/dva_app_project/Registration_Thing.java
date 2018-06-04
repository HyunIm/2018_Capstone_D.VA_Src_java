package com.example.a201311177.dva_app_project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

// 이 클래스에서 해야할 것 - SQLLIte 확인 버튼 누르면 연동시켜서 저장
public class Registration_Thing extends AppCompatActivity {
    private final int GALLERY_CODE=1112;
    Button yes_btn;
    Button no_btn;
    Button find_btn;
    private ImageView image;
    //private Uri photoUri;
    private String imagePath;//실제 사진 파일 경로
    //String mImageCaptureName;//이미지 이름
    EditText _Text;
    Cursor cursor;
    int column_index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__thing);
        yes_btn =(Button)findViewById(R.id.yes);
        no_btn=(Button)findViewById(R.id.no);
        find_btn=(Button)findViewById(R.id.find);
        _Text=(EditText)findViewById(R.id.editText);
        //db 객체 생성
        //버튼 클릭시 처리로직(내일부터!!!!)
        // 이미지 찾기 버튼을 누르면  화면 실행할 수 있도록 액티비티 전환!!
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Toast.makeText(getBaseContext(), "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();

        if(requestCode == GALLERY_CODE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    imagePath = getImageNameToUri(data.getData());
                    System.out.println("사진경로:"+imagePath);
                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    image = (ImageView)findViewById(R.id.imageView1);

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);


                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        //사진 최신순으로 정렬해서 가져오기
        cursor = managedQuery(data, proj, null, null, null);
        //기본앨범 이미지 안될경우 밑에 코드 한줄 실행해보기
        startManagingCursor(cursor);
        column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String imgPath = cursor.getString(column_index);
        //String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);
        return imgPath;
    }

    public void onButtonCheck(View v) {
        //Db에 데이터 넣은 뒤 완료되었습니다라는 메시지 창을 띄우고 초기화 또는 앱을 나간다.
        //일단 여기에 객체 생성
        final DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext(), "Photo.db", null, 1);
        String photo_path = imagePath;
        String name = _Text.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(getApplicationContext(), "물건 이름을 입력해주세요.", Toast.LENGTH_LONG).show();
        }
        else {

            dbHelper.insert(photo_path, name);
            Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_LONG).show();
            //System.out.println(dbHelper.getResult());
        }

        Intent checkTest = new Intent(getApplicationContext(), Thing_List_Management.class);
        startActivity(checkTest);
    }
}
