# MyService

### 메인파트
```java
public class MyService extends Service {
    public MyService() {
    }
```

### CustomBinder 클래스
- Binder는 자동적으로 Ibinder를 implements받고 있다
- 컴포넌트는 바인더를 통해 서비스에 접근 할 수 있다
```java
    class CustomBinder extends Binder {
        public CustomBinder(){

        }
        public MyService getService(){
            return MyService.this;
        }
    }
```

### onBind & onUnBind & getTotal
- onBind()실행되면 CustomBinder를 onServiceConnected()에 return 해준다
```java
    IBinder binder = new CustomBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Myservice","========onBind()");

        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Myservice","========onUnBind()");
        return super.onUnbind(intent);
    }

    public int getTotal(){
        return total;
    }
```

### Service onStart()
- foreground를 같이 실행시킨다
```java
    private int total = 0;
    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        startForeground();
        Log.d("Myservice","========onstartCommand()");
        for(int i=0; i<1000; i++){
            total+=i;
            System.out.println("서비스에서 동작중" + i);
        }
        return super.onStartCommand(intent, flags, startId);
    }
```

### foreground
- onstart로 service불러오면 메모리 초과나 일정시간이 지나면 자동 종료된다
- 그래서 foregound를 사용

```java
    // foreground 서비스하기
    // foreground 서비스 번호
    public static final int FLAG = 11111;
    private void startForeground(){
        // 1. foreground 서비스에서 보여질 notification(알림창) 만들기
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification 타이틀")
                .setContentText("Notification 내용")
                .build();

        // notification 노출시키기
        // 2. notification Manager를 통해 notification 출력
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(FLAG, notification);
    }
```

### stopForeground()
```java
    private void stopForeground(){
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(FLAG);
    }
```

### onCreate() & onDestroy()
```java
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Myservice","========onCreate()");
    }

    @Override
    public void onDestroy() {
        stopForeground();
        super.onDestroy();
        Log.d("Myservice","========onDestroy()");
    }
}
```
