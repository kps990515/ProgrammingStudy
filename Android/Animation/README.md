# 기초 애니메이션

## [애니메이터로 방향키](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/Animation/app)

### 메인
![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/Animation/%EC%98%88%EC%8B%9C.png)

#### 선언 파트
```java
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //버튼선언
    Button btnMove;
    Button btnRotate;
    Button btnScale;
    Button btnAlpha;
    Button btnObject;
```

#### onCreate
```java
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
```

#### onClick
```java
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
```

### 애니메이션 실행 함수

#### move
```java
    // 버튼에서 호출되는 함수
    public void move()
    {   // View 애니메이션 실행하기
        // 1. 애니메이션 xml 정의
        // 2. AnimationUtil로 정의된 애니메이션을 로드
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);
        // 3. 로드된 애니메이션을 실제 위젯에 적용
        btnObject.startAnimation(animation);
    }
    //xml
    <translate
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXDelta="0"
    android:fromYDelta="0"
    android:toXDelta="100"
    android:toYDelta="300"
    android:fillAfter="true"
    android:duration="3000"
    >
</translate>
```

#### rotate
```java
    public void rotate()
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        btnObject.startAnimation(animation);
    }
    ///xml
    <rotate
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromDegrees="0"
    android:toDegrees="180"
    android:pivotX="50%"
    android:pivotY="50%"
    android:fillAfter="true"
    android:duration="3000"
    >
</rotate>
```

#### scale
```java
    public void scale()
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale);
        btnObject.startAnimation(animation);
    }
    ///xml
    <scale
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXScale="1.0"
    android:fromYScale="1.0"
    android:toXScale="0.5"
    android:toYScale="5"
    android:pivotX="50%"
    android:pivotY="50%"
    android:fillAfter="true"
    android:duration="3000"
    >
</scale>
```

#### alpha
```java
    public void alpha()
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        btnObject.startAnimation(animation);
    }
    //xml
    <alpha
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromAlpha="0.0"
    android:toAlpha="1.0"
    android:fillAfter="true"
    android:duration="3000"
    >
</alpha>
```
