# RainDrop
![설명](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/RainDrop/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C1.PNG)  
![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/RainDrop/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C2.PNG)

### 설명
- addrainDrop버튼을 누르면 자동적으로 [RainDrop](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/RainDrop/app/src/README.md)이 생성되고 원에 대한 세부정보가 List에 저장(메인쓰레드)
- 시간이 지날 때 마다 원은 점점 아래로 내려가며 바닥보다 더 내려갈 시 List에서 삭제(서브쓰레드)

### MainActivity

#### Main파트
- [CustomView](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/RainDrop/app/README.md)를 받아와서 stage에 addview
- 그 후 runStage(서브쓰레드) 실행
```java
public class MainActivity extends AppCompatActivity {
    FrameLayout stage;
    CustomView customView;
    // subThread 동작스위치
    public static boolean runFlag = true;
    int width = 0;
    int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stage = (FrameLayout) findViewById(R.id.stage);
        // CustomView를 생성하고
        customView = new CustomView(this);
        // Main View에 addView
        stage.addView(customView);
        // rungStage쓰레드 실행
        customView.runStage();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }
```

#### addRainDrop버튼(메인쓰레드)
- 자동적으로 RainDrop을 생성해주는 쓰레드
- 랜덤으로 RainDrop의 x,y,speed,size를 설정해서 [customView](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/RainDrop/app/README.md)의 List에 넘겨준다

```java
    public void addRainDrop(View v){
        new Thread(){
            Random random = new Random();
            public void run(){
                while(runFlag){
                    int x = random.nextInt(width);
                    int speed = random.nextInt(5)+1;
                    int size = random.nextInt(30)+10;

                    RainDrop rainDrop = new RainDrop(x, 0, speed, size, Color.CYAN, height);
                    customView.addRainDrop(rainDrop);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
```

#### Stop버튼
- 모든 쓰레드의 생존주기는 runFlag가 true일 때까지다
- Stop을 누르면 runFlag가 false로 바뀌면서 쓰레드 종료
- 다시 누르면 재 작동

```java
    public void Stop(View v){
        if(runFlag==true){
            runFlag=false;
        }else{
            runFlag=true;
            customView.runStage();
            addRainDrop(v);
        }
    }
```

### onDestroy()
-  subthread는 항상 onDestroy를 통해 꺼줘야한다

```java    
    @Override
    protected void onDestroy() {
        runFlag = false;
        super.onDestroy();
    }
}
```
