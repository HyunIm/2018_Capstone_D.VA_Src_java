package com.example.a201311177.dva_app_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String TAG = DatabaseHelper.class.getSimpleName();


    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //onCreate() 함수는 생성자에서 넘겨받은 이름과 버전의 데이터베이스가 존재하지 않을때 한번 호출. 그렇기 때문에 새로운 데이터베이스를 생성할때 사용
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE PICTURELIST (_id INTEGER PRIMARY KEY AUTOINCREMENT,Photo_path TEXT, name TEXT);");

            }
        catch (Exception ex) {
            Log.e(TAG, "Exception in CREATE_SQL",ex);
        }
    }
    @Override
    //onUpgrade() 함수는 데이터베이스가 존재하지만 버전이 다르면 호출. 데이터베이스를 변경하고 싶을때 버전을 올려주고 새로운 작업을 ㄱㄱ
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insert(String Photo_path, String name)
    {
        SQLiteDatabase db=getWritableDatabase();//DB 읽고 쓰도록 열기
        //행 추가
        try {
            //만약 같은 사진을 중복으로 등록한다면??????
            db.execSQL("INSERT INTO PICTURELIST VALUES(null,'" + Photo_path + "', " + name + ");");
            db.close();
        }
        catch (Exception ex) {
            Log.e(TAG, "Exception in insert_SQL",ex);
        }
    }
    public void update(String Photo_path, String name)
    {
        SQLiteDatabase db= getWritableDatabase();
        try {
            db.execSQL("UPDATE  SET path=" + Photo_path + " WHERE name='" + name + "';");
            db.close();
        }
        catch (Exception ex) {
            Log.e(TAG, "Exception in update_SQL",ex);
        }
    }
    public void delete(String name)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM PICTURELIST WHERE name'" + name + "';");
        db.close();
    }
    public String getResult(){
        SQLiteDatabase db=getReadableDatabase();
        String result="";
        Cursor cursor = db.rawQuery("SELECT * FROM PICTURELIST", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + ""
                    + cursor.getString(3)
                    + "\n";
        }

        return result;
    }
}
