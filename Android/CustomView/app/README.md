# 그림판

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/%EB%A9%94%EB%AA%A8%EC%9E%A5.png)

### 포함되는 코드들
[activity_draw.xml](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/res/layout/activity_draw.xml)  
[DrawActivity.java](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/java/org/andriodtown/customview/DrawActivity.java)  
[FastDraw.java](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/java/org/andriodtown/customview/FastDraw.java)  
참고 [DrawView.java](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/java/org/andriodtown/customview/DrawView.java)


### 설명

- 상위 레이아웃은 ConstraintLayout(seekBar & radioGroup)
- 하위 레이아웃은 FrameLayout(그림판 부분)
- DrawActivity에서는 각 버튼들의 기능을 만든다
- [DrawView](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/CustomView/app/src/main/java/org/andriodtown/customview/DrawView.java) 에서는 터치움직임을 배열에 저장해서 출력하는 방식(느림)
- FastDraw는 path를 사용해서 끊김없이 사용가능


### DrawActivity(Main)
- xml에 파일에 custom 속성을 만든다

```java
public class DrawActivity extends AppCompatActivity {

    FrameLayout stage;
    RadioGroup radioColor;
    FastDraw draw;
    SeekBar seekBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        radioColor = (RadioGroup)findViewById(R.id.radioColor);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        stage = (FrameLayout)findViewById(R.id.stage);
        draw = new FastDraw(this);
        stage.addView(draw);
        textView = (TextView)findViewById(R.id.textView);
```

#### 라디오 버튼 파트
- 선택되면 draw의 paint 색상을 바꿔준다

```java
        radioColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch(id){
                    case R.id.radioBlack:
                        draw.setColor(Color.BLACK);
                        break;
                    case R.id.radioCyan:
                        draw.setColor(Color.CYAN);
                        break;
                    case R.id.radioMagenta:
                        draw.setColor(Color.MAGENTA);
                        break;
                    case R.id.radioYellow:
                        draw.setColor(Color.YELLOW);
                        break;
                }
            }
        });
```

#### seekBar 파트
- seekBar가 조작되기 전에는 5의 값을 default로 줌
- 조작되면 해당 위치에 맞는 너비 크기를 보여줌
- 조작이끝나면 해당 위치에 맞는 너비 크기를 draw에 return

```java
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            // seekBar가 변경되고 있을 시
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(progress+"");
            }
            // 맨 처음 시작할 떄
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setText(5+"");
            }
            // 조작을 멈췄을 때
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                draw.setStrokeWidth(Float.parseFloat(textView.getText().toString()));
            }
        });
    }
}
```

### FastDraw.java

#### 설명
- Path 사용(빠름)
- Path를 상속받고 width & color를 가지고 있는 PathTool 클래스 생성
- PathTool의 정보를 저장할 paths 리스트 생성
- 이전 color와 width의 정보를 저장할 변수를 설정
- 시작하면 init()실행되면서 paint의 style설정 & setColor, setStrokeWidth함수 실행
- 둘 중 하나라도 실행되면 PathTool이 새로 만들어지며 color & width & path의 값이 저장됨
- 실행안된 함수의 변수(width or color)는 이전 저장 변수에서 가져옴
- 터치조작이 끝나면 paths리스트에서 color & width & path를 꺼내온다
- paint와 tool를 통해 그리기

#### Main파트
- colorTemp에는 기본값 검정
- widthTemp에는 기본값 5f

```java
// path를 이용해 빠르게
public class FastDraw extends View {
    Path path;
    Paint paint;
    ArrayList<PathTool> paths = new ArrayList<>();
    int colorTemp=Color.BLACK;
    float widthTemp=5f;

    // 소스코드에서 사용하기 때문에 생성자 파라미터는 context만 필요
    public FastDraw(Context context) {
        super(context);
        paint = new Paint();
        init();
    }
```

#### init파트
- style & color & width 설정하기 위해 setStyle, setColor함수 호출

```java
    private void init(){
        paint.setStyle(Paint.Style.STROKE);
        setColor(Color.BLACK);
        setStrokeWidth(5f);
    }
```

#### setColor파트
- 메인에서 color값이 설정되면 값을 받아서 새로운 tool에 입력
- width의 값은 widthTemp에서 받아옴

```java
    //클릭된 color의 값을 넘겨받아 설정
    public void setColor(int color){
        if(widthTemp!=-1) {
            PathTool tool = new PathTool();
            tool.toolColor(color);
            tool.toolWidth(widthTemp);
            path = tool;
            paths.add(tool);
            colorTemp=color;
        }
    }
```

#### setStrokeWidth파트
- 메인에서 width값이 설정되면 값을 받아서 새로운 tool에 입력
- color의 값은 colorTemp에서 받아옴

```java
    public void setStrokeWidth(float width)
    {
        if(colorTemp!=-1) {
            PathTool tool = new PathTool();
            tool.toolWidth(width);
            tool.toolColor(colorTemp);
            path = tool;
            paths.add(tool);
            widthTemp=width;
        }
    }
```

#### onDraw파트
- paths에 저장된 tool들을 for문으로 불러옴
- 꺼내올 때 마다 color,width,path를 불러와 그리기

```java
    //화면을 그려주는 onDraw 오버라이드
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for(PathTool tool : paths)
        {
            paint.setColor(tool.getColor());
            paint.setStrokeWidth(tool.getWidth());
            canvas.drawPath(tool, paint);
        }
    }
```

#### onTouchEvent
- 최초 터치시에는 해당좌표 이동
- 터치지속되면 이동한 좌표만큼 path에 저장하고 draw호출

```java
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 최초 터치시 그리지 않고 해당좌표로 이동
                path.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                // 터치가 일어나면 path를 해당좌표로 이동하면서 그린다
                path.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        // onDraw를 호출하는 메서드를 호출
        // 다른 언어에서 그림을 그려주는 함수를 호출하는 메서드는 기존 그림 유지
        // 안드로이드는 그림을 지운다 -> 그래서 x,y좌표 저장할 저장소 필요
        invalidate();

        // return이 false일 경우 touch 이벤트를 연속해서 발생시키지 않는다
        // 즉 드래그를 하면 onTouchEvent가 재 호출 되지 않는다
        return true;
    }
}
```

#### PathTool 클래스 파트
- color, width 변수설정
- 함수는 tool에 color와 width설정하는 함수 2개
- tool에서 color와 width를 가져오는 함수 2개

```java
class PathTool extends Path{
    int color;
    float width;

    public void toolColor(int color){
        this.color = color;
    }

    public int getColor(){
        return this.color;
    }

    public void toolWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return this.width;
    }
}
```
