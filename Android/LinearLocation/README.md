# 1. LinearLayout버튼 겹치게하기

### 2. [다른 레이아웃에 버튼 날라가게 하기](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/LinearLocation/app)

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/LinearLocation/%EA%B2%B9%EC%B9%98%EA%B8%B0.png)
- LayoutParams : 원래 레이아웃은 xml에서 하지만 이걸 사용하면 activty에서도 레이아웃 설정가능

- Onclick함수에서 instance of Button  
Click된 객체가 Button일 경우 실행되는 함수

- onClickListener를 implements안한 경우 intent만들시  
지금 화면을 this로 불러올 수 없고 getApplicationContext를 사용
```java
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
        player2.setY(50);
        //컨테이너에 뷰를 담는다
        stage.addView(player);
        stage.addView(player2);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
          /// click된 것이 button인 경우
            if(v instanceof Button)
            {
                //getApplicationContext() : 현재실행 화면 가져오기
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        }
    };
}
```
