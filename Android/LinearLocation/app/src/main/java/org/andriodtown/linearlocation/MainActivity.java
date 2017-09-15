package org.andriodtown.linearlocation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout stage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btndummy).setOnClickListener(onClickListener);
        stage = (LinearLayout)findViewById(R.id.stage);

        //버튼에 커스텀 레이아웃 적용
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100,100);

        //버튼을 코드로 생성
        Button player = new Button(this);
        player.setLayoutParams(lp); // 여기다가 커스텀 레이아웃 넣기
        player.setText("P");
        player.setBackgroundColor(Color.BLUE);
        player.setY(150);

        //빨간색 버튼을 y축 250에 생성
        Button player2 = new Button(this);
        player2.setBackgroundColor(Color.RED);
        player2.setWidth(100);
        player2.setHeight(100);
        player2.setText("A");
        player2.setY(100);
        //컨테이너에 뷰를 담는다
        stage.addView(player);
        stage.addView(player2);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(v instanceof Button)
            {
                //getApplicationContext() : 현재실행 화면 가져오기
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        }
    };
}
