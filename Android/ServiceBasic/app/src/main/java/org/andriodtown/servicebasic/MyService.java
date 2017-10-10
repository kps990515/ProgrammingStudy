package org.andriodtown.servicebasic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    // Binder는 자동적으로 Ibinder를 implements받고 있다
    // 컴포넌트는 바인더를 통해 서비스에 접근 할 수 있다
    class CustomBinder extends Binder {
        public CustomBinder(){

        }
        public MyService getService(){
            return MyService.this;
        }
    }

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

    // foreground 서비스하기
    // foreground 서비스 번호
    public static final int FLAG = 11111;
    private void startForeground(){
        // foreground 서비스에서 보여질 notification(알림창) 만들기
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification 타이틀")
                .setContentText("Notification 내용")
                .build();

        // notification 노출시키기
        // notification Manager를 통해 notification 출력
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(FLAG, notification);
    }
    private void stopForeground(){
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(FLAG);
    }

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
