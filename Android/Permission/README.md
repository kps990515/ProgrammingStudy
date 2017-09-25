# Permission

### MainActivity
- BaseActivity를 상속받기 때문에 init()만 구현화한다
```java
public class MainActivity extends BaseActivity {
@Override
    public void init() {
        setContentView(R.layout.activity_main);
    }
}
```

### BaseActivity

#### 선언부
- 필요한 permission들을 permissions 배열에 담아 놓는다
- abstract로 한 이유는 main에서 init()을 강제하고 Override하기 위해서

```java

public abstract class BaseActivity extends AppCompatActivity {

    private static final int REQ_CODE = 999;
    private String permissions[] = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
```

#### onCreate()
- 내가 사용하는 버전이 마쉬멜로보다 높으면 checkPermission()으로
- 아니면 바로 실행

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        // 앱 버전체크
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkPermission();
        }
        else{
            init();
        }
    }

    public abstract void init();
```

#### checkPermission()
- read & write permission이 있는지 여부 확인
- 둘 다 있으면 init()
- 하나라도 없으면 requestPermissions()
```java
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        // 1. 권한이 있는지 여부 확인
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
            //승인처리
            init();

            // 2. 권한이 없으면 권한 요청
        }else{
            String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
            // 2.2 권한요청
            requestPermissions(permissions, REQ_CODE);
        }
    }
```

#### onRequestPermissionsResult()
- 권한 승인이 다 됬는지를 체크해서 init();
```java
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 3. 권한승인 여부 체크
        switch (requestCode){
            case REQ_CODE:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                        grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    //진행 허용처리
                    init();
                }
                break;
        }
    }

}
