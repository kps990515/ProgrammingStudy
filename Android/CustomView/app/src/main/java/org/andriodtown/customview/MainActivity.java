package org.andriodtown.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/*
 커스텀뷰 만들기
 1. 커스텀 attrs.xml 파일에 정의(Value파일)
 2. 커스텀할 객체(위젯)을 상속받은 후 재정의
 3. 커스텀한 객체(위젯)을 레이아웃 태그로 사용
 */
public class MainActivity extends AppCompatActivity {

    ConstraintLayout stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stage = (ConstraintLayout)findViewById(R.id.stage);

        CustomView cv = new CustomView(this);
        cv.setX(300);
        cv.setY(300);
        stage.addView(cv);

        findViewById(R.id.aniButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "버튼 클릭", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        findViewById(R.id.aniButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)   {
                Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(intent);
            }
        });
    }
}
