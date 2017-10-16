# Network

<네트워킹>
    1. 권한설정 -> 런타임권한(X)
    2. Thread -> 네트워크를 통한 데이터 이용은 Sub Thread
    3. HttpUrlConnection -> 내장 API
        -> Retrofit(내부에 Thread 포함)
        -> Rx (내부에 Thread + Thread 관리기능 포함, 예외처리 특화)

### Main파트
- hanlder는 밖으로 빼내는 것이 좋다
```java
public class MainActivity extends AppCompatActivity {
    String data = "";
    TextView txt;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
              case 999:
                    txt.setText(data);
            }
        }
    };
```        
### onCreate()
- thread생성
```java
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                txt = (TextView)findViewById(R.id.txt);

                NetworkThread thread = new NetworkThread(handler);
                thread.start();
            }
```    
### HttpURLConnection 사용하기
  1. URL 객체 선언(웹주소를 가지고 생성)
  2. URL 객체에서 서버 연결을 해준다   
    -> HttpURLConnection 생성 = Stream
  3. connection의 방식을 설정(default : GET)
  4. 연결되어있는 Stream을 통해 데이터를 가져온다
  5. 연결(Stream)을 닫는다

```java        
            class NetworkThread extends Thread{
                private Handler handler;
                NetworkThread(Handler handler){
                    this.handler = handler;
                }
                public void run(){
                    final StringBuilder result = new StringBuilder();
                    try {
                        URL url = new URL("http://fastcampus.co.kr");
                        HttpURLConnection con = (HttpURLConnection)url.openConnection();
                        con.setRequestMethod("GET");
                        // 통신이 성공인지 체크
                        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                            // 여기서 부터는 파일에서 데이터를 가져오는 것도 동일
                            InputStreamReader isr = new InputStreamReader(con.getInputStream());
                            BufferedReader br = new BufferedReader(isr);
                            String temp = "";
                            while ((temp = br.readLine()) != null) {
                                result.append(temp).append("\n");
                            }
                            br.close();
                            isr.close();
                        } else {
                            Log.e("ServerError", con.getResponseCode()+"");
                        }
                        con.disconnect();
                    }catch(Exception e){
                        Log.e("ERROR", e.toString());
                    }
                    // 핸들러로 메시지 전달하기
                    data = result.toString();
                    handler.sendEmptyMessage(999);
```

#### 다른 방법(제일 쉬움)
```java
Message msg = new Message();
msg.obj = result.toString();
handler.sendMessage((Message) msg.obj);
```

#### 메인쓰레드에 접근방법
```java
runOnUiThread(new Runnable() {
  @Override
  public void run() {
    txt.setText(result.toString());
    }
  });
```
