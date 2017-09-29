package org.andriodtown.tetris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private static final int ROWS = 18;
    private static final int COLUMNS = 18;
    private static Setting setting;

    FrameLayout container;
    Stage stage;
    Property property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 0. 게임세팅
        setGame();
        // 1. 그림판을 준비
        initView();
    }

    private void setGame(){

        // 0.화면의 사이즈를 구해서 stage에 넘김
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        setting = new Setting(width, height, ROWS, COLUMNS);
    }

    private void initView(){
        container = (FrameLayout)findViewById(R.id.container);
        // stage 생성
        stage = new Stage(this,setting);
        stage.init();
        // container에 stage 표시
        container.addView(stage);
    }

    public void up(View view){
        stage.up();
    }

    public void down(View view){
        stage.down();
    }

    public void left(View view){
        stage.left();
    }

    public void right(View view){
        stage.right();
    }
}
