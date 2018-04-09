/*
* @file : Login.java
* @author : LimHyun (hyunzion@gmail.com)
* @since : 2018 - 04 - 06
* @brief : 로그인 기능을 수행
* */

package dva.findthis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /*
    * @title : public void onButtonSignUp(View v)
    * @brief : SignUp 버튼을 누를 시 SignUp class로 이동
    * */
    public void onButtonSignUp(View v)
    {
        Intent MoveSignUp = new Intent(getApplicationContext(), SignUp.class);
        startActivity(MoveSignUp);
    }
}
