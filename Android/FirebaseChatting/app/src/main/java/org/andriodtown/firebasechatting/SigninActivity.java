package org.andriodtown.firebasechatting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SigninActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public void signin(View view){

    }

    public void signup(View view){
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }


}
