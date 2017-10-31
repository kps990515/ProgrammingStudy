# FirebaseBasic2
![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/FirebaseBasic2/Screenshot_1509497090.png)
- 유저가 가입하는 signup파트와
- 가입한 유저가 로그인하는 signin파트로 구성
- signup시 이메일 형식, 비밀번호 형식 체크

### 선언부
```java
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
```

### onStart()
```java
    @Override
    public void onStart() {
        super.onStart();
        // 유저가 로그인 되어있으면 사용자를 파이어베이스에서 가져온다
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
```

#### signup()
- email을 정규식을 통해 맞는 형식인지 체크
- password를 정규식을 통해 맞는 형식인지 체크
- 틀리면 오류를 나타내는 TextView를 visible로 변경하고 메시지 띄움
- createUserWithEmailAndPassword를 통해 유저를 생성하고
- addOnCompleteListener를 통해 성공했는지 유무 확인
- 성공시 인증메일 발송 + 사용자 앱의 토큰을 받아와 데이터베이스 유저테이블 -> 유저UID -> 토큰 값을 저장

```java
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
                            // 데이터베이스에 사용자 token추가
                            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                            userRef.child(user.getUid()).setValue(refreshedToken);
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
```

### isValidEmail, isValidPassword
- 정규식을 통해 형식 체크

```java
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
```

### signin()
- email,password를 통해 signInWithEmailAndPassword를 실행하고
- addOnCompleteListener달아서 성공시 현재 유저를 설정하고 [StorageActivity](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FirebaseBasic2/app/README.md)로 이동시킨다

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
