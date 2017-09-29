package org.andriodtown.threadbasic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    Rotator rotator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        rotator = new Rotator(handler);
        rotator.start();
    }

    public void stop(View view){
        rotator.setStop();
    }

    public static final int ACTION_SET = 99;

    // seekbar를 변경할 Handler 작성
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch (msg.what){
                case ACTION_SET:
                    float currentR = button.getRotation();
                    button.setRotation(currentR+6);
                    break;
            }
        }
    };
}

class Rotator extends Thread{

    Handler handler;
    boolean RUNNING = true;

    public Rotator(Handler handler){
        this.handler=handler;
    }

    // start메서드가 호출되면 실행되는 메소드
    // run 함수 안의 코드만 subThread에서 실행
    public void run(){
        while(RUNNING) {
            // Handler측으로 메시지를 보낸다
            // 방법 1 - message에 담아서 보냄(다른 값도 같이 보내기 가능)
            Message message = new Message();
            message.what = MainActivity.ACTION_SET;
            handler.sendMessage(message);
            // 방법 2 - emptymessage에 정의된 값을 참조해서 보냄(다른 값 보내기 불가)
            //handler.sendEmptyMessage(MainActivity.ACTION_SET);


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void setStop(){
        RUNNING=false;
    }
}