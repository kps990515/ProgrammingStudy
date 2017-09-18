# CustomView

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/anibutton.png)

### 포함되는 코드들
[scale.xml](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/res/anim/scale.xml)  
[activty_main.xml](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/res/layout/activity_main.xml)  
[attrs.xml](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/res/values/attrs.xml)  
 [AniButton.java](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/java/org/andriodtown/customview/AniButton.java)   
[CustomView](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/java/org/andriodtown/customview/CustomView.java)  
[MainActivity.java](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/java/org/andriodtown/customview/MainActivity.java)


### 커스텀뷰 만들기
 1. 커스텀 attrs.xml 파일에 정의(Value파일)
 2. 커스텀할 객체(위젯)을 상속받은 후 재정의
 3. 커스텀한 객체(위젯)을 레이아웃 태그로 사용

### 설명

- attrs.xml파일을 통해 custom 속성을 만든다
- AniButton.java를 통해 attrs에 정의된 버튼의 정보를 가져오고  
- activty_main에 버튼의 animation이 true로 설정되어 있으면
- 버튼에 animation이라는 이름을 집어넣는다
- 버튼 터치시 흔들리는 기능 구현  

### attrs.xml
- xml에 파일에 custom 속성을 만든다

```java
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="AniButton"> <!--재정의할 객체이름-->
        //format 1. string(직접 입력) 2. reference(다른 value에서 가져옴)
        <attr name="animation" format="string"/>
        <attr name="delimeter" format="string"/>
    </declare-styleable>
</resources>
```

### AniButton.java
-attrs의 정보를 가져와서 CustomButton의 속성을 만든다

```java
// 버튼이 아닌 AppCompatButton을 상속하는 이유는
// 모든 버전 호환을 위해(v4,v7)
public class AniButton extends AppCompatButton implements View.OnTouchListener {
/*
에니메이션 속성이 true일 경우
scale애니메이션을 사용해서 클릭시 살짝 커졌다 작아지는 버튼 만들기
*/
public AniButton(Context context, AttributeSet attrs) {
  super(context, attrs);

  // 1. attrs.xml에 정의된 속성을 가져온다
  // context에 담을 속성은 activty.xml에 있는 버튼 정보
  // AttributeSet attrs에 담아서 context에 보낸다
  // 그 정보를 TypedArray에 저장
  TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.AniButton);
  // 2. 해당 이름으로 정의된 속성의 개수를 가져온다
  int size = typed.getIndexCount();
  Log.d("AniButton","size="+size);
  // 3. 반복문을 돌면서 해당 속성에 대한 처리를 해준다
  for(int i=0; i<size ; i++)
  {
      // 3.1 현재 배열에 있는 속성 아이디 가져오기
      int current_attr = typed.getIndex(i);
      switch(current_attr) {
          case AniButton_animation:
              // 여기서 가져온 값은 xml에 정의한 custom.animation 값
              String animation = typed.getString(current_attr);
              //이렇게 하면 animation값이 null이라도 에러 안남
              if("true".equals(animation))
              {
                  String currentText = getText() + "";
                  setText("[animation]\n" + currentText);
                  setOnTouchListener(this);
                  break;
              }
          case R.styleable.AniButton_delimeter:
              break;
      }
  }
}
// 터치할시 애니메이션 작동
@Override
public boolean onTouch(View v, MotionEvent event) {
  switch(event.getAction()){
      case ACTION_DOWN:
          runAnimation(v);
          break;

  }
  return false;
}
public void runAnimation(View v){
  Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale);
  startAnimation(anim);
}
}
```

### MainActivity
- CustomView를 가져와서 특정 위치에 삽입
- 버튼 클릭시 toast메시지 띄우기

```java
public class MainActivity extends AppCompatActivity {

    ConstraintLayout stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stage = (ConstraintLayout)findViewById(R.id.stage);
        // 커스텀뷰를 가져와서 특정위치에 집어넣는다
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
```

### CustomView
- onDraw(canvas)로 그릴준비
- paint로 그릴 내용물의 속성결정
- canvas에 그리기

```java
public class CustomView extends View {

  // 내가 소스코드에서 생성할 떄
  public CustomView(Context context){
      super(context);
  }
  //xml에서 태그로 사용할 때 시스템에서 호출해주는 생성자
  public CustomView(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
  }

  @Override
  protected void onDraw(Canvas canvas){
      super.onDraw(canvas);

      // 색, 두께 정하기
      Paint paint = new Paint(); //물감 - 겉모양 결정도구
      paint.setColor(Color.CYAN);
      paint.setStrokeWidth(2); // 굵기

      // 화면에 사각형 그리기
      // left = 시작점이 0일때 어디서 시작할지
      // right = 시작점이 0일때 어디까지 갈지...
      canvas.drawRect(100,100,300,200,paint);
  }
}
```
