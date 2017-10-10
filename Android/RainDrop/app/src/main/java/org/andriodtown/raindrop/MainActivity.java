package org.andriodtown.raindrop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    FrameLayout stage;
    CustomView customView;
    // subThread 동작스위치
    public static boolean runFlag = true;
    int width = 0;
    int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stage = (FrameLayout) findViewById(R.id.stage);
        customView = new CustomView(this);
        stage.addView(customView);

        customView.runStage();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    public void addRainDrop(View v){
        new Thread(){
            Random random = new Random();
            public void run(){
                while(runFlag){
                    int x = random.nextInt(width);
                    int speed = random.nextInt(5)+1;
                    int size = random.nextInt(30)+10;

                    RainDrop rainDrop = new RainDrop(x, 0, speed, size, Color.CYAN, height);
                    customView.addRainDrop(rainDrop);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public void Stop(View v){
        if(runFlag==true){
            runFlag=false;
        }else{
            runFlag=true;
            customView.runStage();
            addRainDrop(v);
        }
    }

    // subthread는 항상 onDestroy를 통해 꺼줘야한다
    @Override
    protected void onDestroy() {
        runFlag = false;
        super.onDestroy();
    }
}






