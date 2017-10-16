package org.andriodtown.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/*
    AsyncTask = 세개의 기본함수를 지원하는 Thread(Thread + Handler)
    -> 1,3번은 Main Thread에서 2번만 백그라운드(Sub Thread)에서

    1. onPreExecute : doInBackground()함수가 실행되기 전에 실행되는 함수

    2. doInBackground : 백그라운드(Sub Thread)에서 코드를 실행하는 함수
        -> onPostExecute는 doInBackground로 부터 데이터를 받을 수 있다

    3. onPostExecute : doInBackground()함수가 실행된 후에 실행되는 함수
 */
public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);

        getServer("http://google.com");

    }
    private void getServer(String url){
        AsyncTask<String,Void,String> task = new AsyncTask<String,Void,String>() {
            // task
            // 1. doInBackground 함수의 파라미터(1번째 String)로 사용
            // 2. onProgressUpdate 함수의 파라미터로 사용
                // 주로 진행상태의 % 값으로(int) 사용된다
            // 3. doInBackground의 리턴값(3번째 string)이면서 onPostExecute의 파라미터(3번째 string)
            @Override
            protected String doInBackground(String... args) {
                String param1 = args[0];
                String result = Remote.getData(param1);
                int start = result.indexOf("Google");
                int end = result.indexOf("</title>");
                return result.substring(start,end);
            }

            @Override
            protected void onPostExecute(String s) {
                textView.setText(s);
            }
        };
        task.execute(url);
    }
}
