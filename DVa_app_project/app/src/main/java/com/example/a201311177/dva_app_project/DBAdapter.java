//package com.example.a201311177.dva_app_project;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CursorAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
////이부분 코드 다시보기
//public class DBAdapter extends CursorAdapter{
//
//    public DBAdapter(Context context, Cursor c)
//    {
//        super(context,c);
//    }
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        ((TextView) view).setText(cursor.getString(0) + " / " + cursor.getString(1));
//    }
//
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
//        return view.findViewById(android.R.id.text1);
//    }
//}
