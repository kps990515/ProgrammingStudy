package org.andriodtown.rotating;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn: move(); break;
        }
    }

    public void move()
    {
        if(count==0) {
            count=1;
            ObjectAnimator btn1Y = ObjectAnimator.ofFloat(
                    btn1, "translationY", -150
            );
            ObjectAnimator btn1X = ObjectAnimator.ofFloat(
                    btn1, "translationX", -150
            );
            ObjectAnimator btn1r = ObjectAnimator.ofFloat(
                    btn1, "rotation", -0f, 360f * 3000
            );
            ObjectAnimator btn2Y = ObjectAnimator.ofFloat(
                    btn2, "translationY", -150
            );
            ObjectAnimator btn2X = ObjectAnimator.ofFloat(
                    btn2, "translationX", 150
            );
            ObjectAnimator btn2r = ObjectAnimator.ofFloat(
                    btn2, "rotation", -0f, 360f * 3000
            );
            ObjectAnimator btn3Y = ObjectAnimator.ofFloat(
                    btn3, "translationY", 150
            );
            ObjectAnimator btn3X = ObjectAnimator.ofFloat(
                    btn3, "translationX", -150
            );
            ObjectAnimator btn3r = ObjectAnimator.ofFloat(
                    btn3, "rotation", -0f, 360f * 3000
            );
            ObjectAnimator btn4Y = ObjectAnimator.ofFloat(
                    btn4, "translationY", 150
            );
            ObjectAnimator btn4X = ObjectAnimator.ofFloat(
                    btn4, "translationX", 150
            );
            ObjectAnimator btn4r = ObjectAnimator.ofFloat(
                    btn4, "rotation", -0f, 360f * 3000
            );
            AnimatorSet aniset = new AnimatorSet();
            aniset.playTogether(btn1X, btn1Y, btn2X, btn2Y, btn3X, btn3Y, btn4X, btn4Y, btn1r, btn2r, btn3r, btn4r);
            aniset.setDuration(3000);
            aniset.setInterpolator(new AccelerateDecelerateInterpolator());
            aniset.start();
        }
        else
        {
            count=0;
            ObjectAnimator btn1Y = ObjectAnimator.ofFloat(
                    btn1, "translationY", 0
            );
            ObjectAnimator btn1X = ObjectAnimator.ofFloat(
                    btn1, "translationX", 0
            );
            ObjectAnimator btn1r = ObjectAnimator.ofFloat(
                    btn1, "rotation", -0f, 360f * 3000
            );
            ObjectAnimator btn2Y = ObjectAnimator.ofFloat(
                    btn2, "translationY", 0
            );
            ObjectAnimator btn2X = ObjectAnimator.ofFloat(
                    btn2, "translationX", -0
            );
            ObjectAnimator btn2r = ObjectAnimator.ofFloat(
                    btn2, "rotation", -0f, 360f * 3000
            );
            ObjectAnimator btn3Y = ObjectAnimator.ofFloat(
                    btn3, "translationY", -0
            );
            ObjectAnimator btn3X = ObjectAnimator.ofFloat(
                    btn3, "translationX", 0
            );
            ObjectAnimator btn3r = ObjectAnimator.ofFloat(
                    btn3, "rotation", -0f, 360f * 3000
            );
            ObjectAnimator btn4Y = ObjectAnimator.ofFloat(
                    btn4, "translationY", -0
            );
            ObjectAnimator btn4X = ObjectAnimator.ofFloat(
                    btn4, "translationX", -0
            );
            ObjectAnimator btn4r = ObjectAnimator.ofFloat(
                    btn4, "rotation", -0f, 360f * 3000
            );
            AnimatorSet aniset = new AnimatorSet();
            aniset.playTogether(btn1X, btn1Y, btn2X, btn2Y, btn3X, btn3Y, btn4X, btn4Y, btn1r, btn2r, btn3r, btn4r);
            aniset.setDuration(3000);
            aniset.setInterpolator(new AccelerateInterpolator());
            aniset.start();
        }
    }
    public void initView()
    {
        btn = (Button)findViewById(R.id.btn);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
    }

    public void initListener()
    {
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn1.setOnClickListener(this);
    }
}
