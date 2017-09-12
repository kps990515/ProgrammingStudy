package org.andriodtown.basiclayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static org.andriodtown.basiclayout.R.id.btnCal;
import static org.andriodtown.basiclayout.R.id.btnFrame;
import static org.andriodtown.basiclayout.R.id.btnGrid;
import static org.andriodtown.basiclayout.R.id.btnLinear;
import static org.andriodtown.basiclayout.R.id.btnRelative;

// 안드로이드 화면 구조
// App(어플) -> Activity(화면 한개 단위) -> Fragment(화면 조각 - 옵션) -> Layout(뷰 그룹: 컨테이너) -> Widget(뷰)

public class MainActivity extends AppCompatActivity {
                                    //Activity 기반클래스를 상속받아서 구성
    // 1. 레이아웃에 정의된 위젯의 아이디로 해당 객체를 선언
    // Button btnFrame, btnLinear, btnGrid, btnRelative;

    @Override
    //onCreate : 화면이 호출될 때 쓰는 함수
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //2. 레이아웃 xml 파일을 메모리에 로드
        setContentView(R.layout.activity_main);

        //3. 선언된 변수에 실제 위젯을 할당
        //btnFrame = (Button) findViewById(btnFrame);
        //btnLinear = (Button) findViewById(btnLinear);
        //btnGrid = (Button) findViewById(btnGrid);
        //btnRelative = (Button) findViewById(btnRelative);

        //4. 위에서 저장한 변수를 사용
        // 코드 줄이기
        findViewById(R.id.btnFrame).setOnClickListener(onClickListener);
        findViewById(R.id.btnLinear).setOnClickListener(onClickListener);
        findViewById(R.id.btnGrid).setOnClickListener(onClickListener);
        findViewById(R.id.btnRelative).setOnClickListener(onClickListener);
        findViewById(btnCal).setOnClickListener(onClickListener);
    }
        // 리스너를 전역 변수로 선언
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v){

                // 액티비티(메이저 컴포넌트) 실행
                // 1. 인텐트 (시스템에 전달되는 메시지 객체) 생성
                Intent intent = null;
                switch(v.getId()){
                    case btnFrame:
                        intent = new Intent(MainActivity.this, FrameActivity.class);
                        break;
                    case btnLinear:
                        intent = new Intent(MainActivity.this, LinearActivity.class);
                        break;
                    case btnGrid:
                        intent = new Intent(MainActivity.this, GridActivity.class);
                        break;
                    case btnRelative:
                        intent = new Intent(MainActivity.this, RelativeActivity.class);
                        break;
                    case btnCal:
                        intent = new Intent(MainActivity.this, activity_calculator.class);
                        break;
                }

                //2. 액티비티 실행요청
                startActivity(intent);
            }
        };
    }
