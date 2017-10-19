package org.andriodtown.subway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btn_1;
    ImageButton btn_2;
    ImageButton btn_3;
    ImageButton btn_4;
    ImageButton btn_5;
    ImageButton btn_6;
    ImageButton btn_7;
    ImageButton btn_8;
    ImageButton btn_9;
    ImageButton btn_I;
    ImageButton btn_I2;
    ImageButton btn_B;
    ImageButton btn_S;
    ImageButton btn_K;
    ImageButton btn_A;
    ImageButton btn_G;
    ImageButton btn_SU;
    ImageButton btn_U;
    ImageButton btn_E;
    ImageButton btn_KK;
    ImageButton btn_ui;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }
    private void initView(){
        btn_1 = (ImageButton)findViewById(R.id.btn_1);
        btn_2 = (ImageButton)findViewById(R.id.btn_2);
        btn_3 = (ImageButton)findViewById(R.id.btn_3);
        btn_4 = (ImageButton)findViewById(R.id.btn_4);
        btn_5 = (ImageButton)findViewById(R.id.btn_5);
        btn_6 = (ImageButton)findViewById(R.id.btn_6);
        btn_7 = (ImageButton)findViewById(R.id.btn_7);
        btn_8 = (ImageButton)findViewById(R.id.btn_8);
        btn_9 = (ImageButton)findViewById(R.id.btn_9);
        btn_I = (ImageButton)findViewById(R.id.btn_I);
         btn_I2 = (ImageButton)findViewById(R.id.btn_I2);
         btn_B = (ImageButton)findViewById(R.id.btn_B);
         btn_S = (ImageButton)findViewById(R.id.btn_S);
         btn_K = (ImageButton)findViewById(R.id.btn_K);
         btn_A = (ImageButton)findViewById(R.id.btn_A);
         btn_G = (ImageButton)findViewById(R.id.btn_G);
         btn_SU = (ImageButton)findViewById(R.id.btn_SU);
         btn_U = (ImageButton)findViewById(R.id.btn_U);
         btn_E = (ImageButton)findViewById(R.id.btn_E);
         btn_KK = (ImageButton)findViewById(R.id.btn_KK);
         btn_ui = (ImageButton)findViewById(R.id.btn_ui);
    }

    private void initListener(){
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_I.setOnClickListener(this);
        btn_I2.setOnClickListener(this);
        btn_B.setOnClickListener(this);
        btn_S.setOnClickListener(this);
        btn_K.setOnClickListener(this);
        btn_A.setOnClickListener(this);
        btn_G.setOnClickListener(this);
        btn_SU.setOnClickListener(this);
        btn_U.setOnClickListener(this);
        btn_E.setOnClickListener(this);
        btn_KK.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v instanceof ImageButton){
            Intent intent = new Intent(this,LinesActivity.class);
            String line = "";
            switch(v.getId()) {
                case R.id.btn_1:line="1";break;
                case R.id.btn_2: line="2";break;
                case R.id.btn_3: line="3";break;
                case R.id.btn_4: line="4";break;
                case R.id.btn_5: line="5";break;
                case R.id.btn_6: line="6";break;
                case R.id.btn_7:line="7";break;
                case R.id.btn_8:line="8";break;
                case R.id.btn_9:line="9";break;
                case R.id.btn_I:line="I";break;
                case R.id.btn_I2:line="I2";break;
                case R.id.btn_B:line="B";break;
                case R.id.btn_S:line="S";break;
                case R.id.btn_K:line="K";break;
                case R.id.btn_A:line="A";break;
                case R.id.btn_G:line="G";break;
                case R.id.btn_SU:line="SU";break;
                case R.id.btn_U:line="U";break;
                case R.id.btn_E:line="E";break;
                case R.id.btn_KK:line="KK";break;
                case R.id.btn_ui:line="ui";break;
            }
            intent.putExtra("Line",line);
            startActivity(intent);
        }
    }
}
