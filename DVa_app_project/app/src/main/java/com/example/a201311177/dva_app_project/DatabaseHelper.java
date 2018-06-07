package com.example.a201311177.dva_app_project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context mCtx;
    public static final String TAG = DatabaseHelper.class.getSimpleName();

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mCtx = context;
    }


    @Override
    //onCreate() 함수는 생성자에서 넘겨받은 이름과 버전의 데이터베이스가 존재하지 않을때 한번 호출. 그렇기 때문에 새로운 데이터베이스를 생성할때 사용
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE PICTURELIST (Photo_path TEXT, name TEXT PRIMARY KEY);");
        } catch (Exception ex) {
            Log.e(TAG, "Exception in CREATE_SQL", ex);
        }
    }

    @Override
    //onUpgrade() 함수는 데이터베이스가 존재하지만 버전이 다르면 호출. 데이터베이스를 변경하고 싶을때 버전을 올려주고 새로운 작업을 ㄱㄱ
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String Photo_path, String name) {
        SQLiteDatabase db = getWritableDatabase(); //DB 읽고 쓰도록 열기
        //행 추가
        try {
            //만약 같은 사진을 중복으로 등록한다면??????
            db.execSQL("INSERT INTO PICTURELIST VALUES('" + Photo_path + "', '" + name + "');");
            Log.e(TAG, "success in insert_SQL");
            db.close();
        } catch (Exception ex) {
            Log.e(TAG, "Exception in insert_SQL", ex);
        }
    }

    public void update(String Photo_path, String name) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL("UPDATE  SET path=" + Photo_path + " WHERE name='" + name + "';");
            db.close();
        } catch (Exception ex) {
            Log.e(TAG, "Exception in update_SQL", ex);
        }
    }

    //이부분 수정좀 하기!!!!
    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM PICTURELIST;");//" WHERE _id'" + _id + "';");//primartkey 값으로 지운다.

        db.close();
    }

    //이부분도 필요없으면 없앨 수도
    public String[] photopathResult() {
        int j = 0;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM PICTURELIST", null);
        String[] result = new String[cursor.getCount()];
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            cursor.getString(1);
            do {
                result[j] += cursor.getString(0);//photopath
                j++;
                if (j > cursor.getCount())
                    break;
            } while (cursor.moveToNext());
        }
        return result;
    }

    public String[] nameResult() {
        int j = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PICTURELIST", null);

        String[] result = new String[cursor.getCount()];
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            cursor.getString(1);
            do {
                result[j] += cursor.getString(1);//name
                j++;
                if (j >= cursor.getCount())
                    break;

            } while (cursor.moveToNext());
        }
        return result;
    }
}
//
//    public String []ALlResult()
//    {
//        SQLiteDatabase db = getReadableDatabase();
//
//        int i=0;
//        Cursor cursor = db.rawQuery("SELECT * FROM PICTURELIST", null);
//        cursor.moveToFirst();
//        cursor.getString(2);
//        String [] result = new String[cursor.getCount()];
//        System.out.println(cursor.getCount());
//        do
//        {
//            result[i] +=  cursor.getString(0)
//                + " : "
//                + cursor.getString(1)
//                + "\n";
//            i++;
//            System.out.println(result[i]);
//            if(i>cursor.getCount())
//                break;
//        }while (cursor.moveToNext());
//        return result;
//    }
//}
