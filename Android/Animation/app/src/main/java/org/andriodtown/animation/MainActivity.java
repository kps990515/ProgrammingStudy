package org.andriodtown.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //버튼선언
    Button btnMove;
    Button btnRotate;
    Button btnScale;
    Button btnAlpha;
    Button btnObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }
    //버튼 id부여
    private void initView()
    {
        btnMove = (Button) findViewById(R.id.btnMove);
        btnRotate = (Button) findViewById(R.id.btnRotate);
        btnScale = (Button) findViewById(R.id.btnScale);
        btnAlpha = (Button) findViewById(R.id.btnAlpha);
        btnObject = (Button) findViewById(R.id.btnObject);
    }
    //버튼에 listener 인터페이스 설정
    private void initListener()
    {
        btnMove.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnScale.setOnClickListener(this);
        btnAlpha.setOnClickListener(this);
        btnObject.setOnClickListener(this);
    }
    //버튼 분기처리
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnMove: move(); break;
            case R.id.btnRotate: rotate(); break;
            case R.id.btnScale: scale(); break;
            case R.id.btnAlpha: alpha(); break;
            case R.id.btnObject:
                // 화면 이동
                Intent intent = new Intent(this, PropAniActivity.class);
                startActivity(intent);
        }
    }
    // 버튼에서 호출되는 함수
    public void move()
    {   // View 애니메이션 실행하기
        // 1. 애니메이션 xml 정의
        // 2. AnimationUtil로 정의된 애니메이션을 로드
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);
        // 3. 로드된 애니메이션을 실제 위젯에 적용
        btnObject.startAnimation(animation);

    }
    public void rotate()
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        btnObject.startAnimation(animation);
    }
    public void scale()
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        btnObject.startAnimation(animation);
    }
    public void alpha()
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        btnObject.startAnimation(animation);
    }
}
