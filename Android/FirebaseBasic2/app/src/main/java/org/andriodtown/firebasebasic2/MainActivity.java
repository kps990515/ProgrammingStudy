package org.andriodtown.firebasebasic2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference userRef;
    EditText up_id;
    EditText up_pw;
    EditText in_id;
    EditText in_pw;
    TextView up_errid;
    TextView up_errpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        up_id = findViewById(R.id.up_id);
        up_pw = findViewById(R.id.up_pw);
        in_id = findViewById(R.id.in_id);
        in_pw = findViewById(R.id.in_pw);
        up_errid = findViewById(R.id.up_errid);
        up_errpw = findViewById(R.id.up_errpw);

        database  = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");

    }

    @Override
    public void onStart() {
        super.onStart();
        // 유저가 로그인 되어있으면 사용자를 파이어베이스에서 가져온다
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
    // 사용자 등록
    public void signup(View view){
        // @ 1개, . 1개, 영문, 숫자, _, -
        String email = up_id.getText().toString();
        // 특수문자 하나이상 !@#$%^&*()
        // 영문, 숫자
        String password = up_pw.getText().toString();

        if(!isValidEmail(email)||!isValidPassword(password)){
            if(!isValidEmail(email)){
                up_errid.setText("이메일 형식이 잘못됐습니다");
                up_errid.setVisibility(View.VISIBLE);
            }
            if(!isValidPassword(password)){
                up_errpw.setText("비밀번호 형식이 잘못됐습니다");
                up_errpw.setVisibility(View.VISIBLE);
            }
            return;
        }else{
            up_errid.setVisibility(View.INVISIBLE);
            up_errpw.setVisibility(View.INVISIBLE);
        }

        // 파이어베이스의 인증모듈로 사용자를 생성
        mAuth.createUserWithEmailAndPassword(email, password)
                // 완료확인 listener
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(MainActivity.this, "인증메일이 발송되었습니다",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                            // 데이터베이스에 사용자 정보추가
                            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                            User newuser = new User(user.getUid(), refreshedToken, user.getEmail());
                            userRef.child(user.getUid()).setValue(newuser);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    // Validation check
    // 정규식
    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }

    public static boolean isValidPassword(String password) {
        boolean err = false;
        String regex = "^[A-Za-z0-9]{8,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if(m.matches()) {
            err = true;
        }
        return err;
    }
    // 사용자 로그인
    public void signin(View view){
        String email = in_id.getText().toString();
        String password = in_pw.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()){
                                Intent intent = new Intent(MainActivity.this,StorageActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(MainActivity.this, "이메일을 확인하세요",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void getUserInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            boolean emailVerified = user.isEmailVerified();

            String uid = user.getUid();
        }
    }


}