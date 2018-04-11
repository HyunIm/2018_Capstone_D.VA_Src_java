/*
 * @file : MainActivity.java
 * @author : LimHyun (hyunzion@gmail.com)
 * @since : 2018 - 04 - 09
 * @brief : 사진 보여주기
 *          Test용 사진 3개를 Thread로 Animation해줌
 * */

package dva.imageview;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    ArrayList<Drawable> drawableList = new ArrayList<Drawable>();

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById((R.id.imageView));
        
        startAnimation();
    }

    public void startAnimation() {
         Resources res = getResources();

         // Test 용 이미지 3개
         drawableList.add(res.getDrawable(R.drawable.one));
         drawableList.add(res.getDrawable(R.drawable.two));
         drawableList.add(res.getDrawable(R.drawable.three));

         AnimThread thread = new AnimThread();
         thread.start();
    }

    // Thread
    class AnimThread extends Thread {
        public void run() {
            int index = 0;
            for (int i = 0; i < 100; i++) {
                final Drawable drawable = drawableList.get(index);
                index += 1;
                // 사진의 갯수만큼 지정
                if (index > 2) {
                    index = 0;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });
                try {
                    // 지속 시간 변경
                    Thread.sleep(1000);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
