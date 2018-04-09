package dva.findthis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * @title : public void onButtonLogin(View v)
     * @brief : Login 버튼을 누를 시 SignUp class로 이동
     * */
    public void onButtonLogin(View v)
    {
        Intent MoveLogin = new Intent(getApplicationContext(), Login.class);
        startActivity(MoveLogin);
    }
}
