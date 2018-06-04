//package com.example.a201311177.dva_app_project;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.widget.Checkable;
//import android.widget.RelativeLayout;
//
//import java.util.jar.Attributes;
//
//public class CheckableRelativeLayout extends RelativeLayout implements Checkable
//{
//    final String NS = "http://schemas.android.com/apk/res/com.huewu.example.checkable";
//
//    final String ATTR = "checkable";
//
//    int checkableId;
//
//    Checkable checkable;
//
//
//    public CheckableRelativeLayout(Context context, Attributes attrs) {
//        super(context, (AttributeSet) attrs);
//        checkableId = ((AttributeSet) attrs).getAttributeResourceValue(NS, ATTR, 0);
//
//
//    }
//
//    @Override
//    public void setChecked(boolean checked) {
//
//        checkable = (Checkable) findViewById(checkableId);
//
//        if(checkable == null)
//
//            return;
//
//        checkable.setChecked(checked);
//
//    }
//
//    @Override
//    public boolean isChecked() {
//        checkable = (Checkable) findViewById(checkableId);
//
//        if(checkable == null)
//
//            return false;
//
//        return checkable.isChecked();
//
//    }
//
//    @Override
//    public void toggle() {
//        checkable = (Checkable) findViewById(checkableId);
//
//        if(checkable == null)
//
//            return;
//
//        checkable.toggle();
//
//    }
//}
