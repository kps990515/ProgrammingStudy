package org.andriodtown.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/*
 커스텀뷰 만들기
 1. 커스텀 attrs.xml 파일에 정의(Value파일)
 2. 커스텀할 객체(위젯)을 상속받은 후 재정의
 3. 커스텀한 객체(위젯)을 레이아웃 태그로 사용
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
