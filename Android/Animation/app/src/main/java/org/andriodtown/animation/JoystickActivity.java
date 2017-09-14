package org.andriodtown.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class JoystickActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnleft;
    private Button btnright;
    private Button btnup;
    private Button btndown;
    private Button btnplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        initView();
        initListener();
    }

    public void initView()
    {
        btnleft = (Button) findViewById(R.id.btnleft);
        btnright = (Button) findViewById(R.id.btnright);
        btnup = (Button) findViewById(R.id.btnup);
        btndown = (Button) findViewById(R.id.btndown);
        btnplayer = (Button) findViewById(R.id.btnplayer);
    }

    public void initListener()
    {
        btnleft.setOnClickListener(this);
        btnright.setOnClickListener(this);
        btnup.setOnClickListener(this);
        btndown.setOnClickListener(this);
    }
    int playerX=0;
    int playerY=0;
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnleft: left(); break;
            case R.id.btnright: right(); break;
            case R.id.btnup: up(); break;
            case R.id.btndown: down(); break;
        }
    }
    private void left()
    {
        playerX-=100;
        move();
    }
    private void right()
    {
        playerX+=100;
        move();
    }
    private void up()
    {
        playerY-=100;
        move();
    }
    private void down()
    {
        playerY+=100;
        move();
    }
    private void move()
    {
        ObjectAnimator aniY = ObjectAnimator.ofFloat(
                btnplayer, "translationY", playerY
        );
        ObjectAnimator aniX = ObjectAnimator.ofFloat(
                btnplayer, "translationX", playerX
        );
        AnimatorSet aniset = new AnimatorSet();
        aniset.playTogether(aniY, aniX);
        aniset.start();
    }
}
