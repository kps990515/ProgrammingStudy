package org.andriodtown.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;


// abstract로 한 이유는 main에서 init()을 강제하고 Override하기 위해서
public abstract class BaseActivity extends AppCompatActivity {

    private static final int REQ_CODE = 999;
    private String permissions[] = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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
