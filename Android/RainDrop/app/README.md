# CustomView

### Main파트
- CustomView는 View를 상속받는다
```java
public class CustomView extends View {
    Paint paint;
    List<RainDrop> rainDrops = new ArrayList<>();
    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.CYAN);
    }
```

### onDraw()
- onDraw()가 호출되면 List에 저장된 rainDrop들을 화면에 그려준다

```java
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(rainDrops.size() > 0) {
            // 일반 for문을 쓰는 이유는 강화된 for문으로는 remove불가
            for(int i=0; i<rainDrops.size();i++) {
                RainDrop rainDrop = rainDrops.get(i);
                paint.setColor(rainDrop.color);
                canvas.drawCircle(rainDrop.x
                        , rainDrop.y
                        , rainDrop.size
                        , paint);
            }
        }
    }
```

### addRainDrop()
- Main쓰레드에서 raindrop이 생성되면 넘어 올때 List에 저장하는 함수
```java
    public void addRainDrop(RainDrop rainDrop) {
        this.rainDrops.add(rainDrop);
    }
```

### runStage() - 서브쓰레드
- raindrop중 바닥을 지나간 것은 List에서 제외
- 나머지는 계속 raindrop이 아래로 향하도록 y+speed해줌
- postInvalidate를 통해 onDraw()호출

```java
    // stage view를 갱신시켜주는 함수
    public void runStage(){
        new Thread(){
            public void run(){
                while(MainActivity.runFlag){
                    // 일반 for문을 쓰는 이유는 강화된 for문으로는 remove불가
                    for(int i=0; i<rainDrops.size(); i++){
                        RainDrop rainDrop = rainDrops.get(i);
                        if(rainDrop.y>rainDrop.floor){
                            rainDrops.remove(i);
                            i--;
                        }else{
                            rainDrop.y+=rainDrop.speed;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
```
