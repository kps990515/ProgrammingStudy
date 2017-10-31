# StorageActivity
- strage와 실시간 데이터베이스 사용
- 실시간으로 유저의 목록이 뜨고
- 유저를 선택하면 id,token이 설정되며 메시지 발송가능

### 선언부

```java
public class StorageActivity extends AppCompatActivity implements UserAdapter.Callback{
    // 파일저장소
    private StorageReference storageRef;
    // 실시간 데이터베이스
    FirebaseDatabase database;
    DatabaseReference userRef;

    private EditText edit_msg;
    private RecyclerView userList;
    private TextView txt_id;
    private TextView txt_token;
    private UserAdapter adapter;
```

### onCreate()
```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        storageRef = FirebaseStorage.getInstance().getReference();
        database  = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");
        initView();
    }
```

### initView()
- [UserAdapter](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FirebaseBasic2/app/src/README.md)를 userList에 연결
- userRef에 addValueEventListener달아서 데이터베이스에 변동이 있을 시 UserAdapter에 넘겨서 RecyclerView 동기화

```java
    private void initView() {
        edit_msg = findViewById(R.id.edit_msg);
        txt_id = findViewById(R.id.txt_id);
        txt_token = findViewById(R.id.txt_token);
        userList = findViewById(R.id.userList);
        adapter = new UserAdapter(this);
        userList.setAdapter(adapter);
        userList.setLayoutManager(new LinearLayoutManager(this));

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> data = new ArrayList<>();
                for(DataSnapshot item : dataSnapshot.getChildren()){
                    String id = item.getKey();
                    String token = (String)item.getValue();

                    data.add(new User(id,token));
                }
                adapter.setDataAndRefresh(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void send(View view){

    }
```

### chooseFile()
- 버튼을 누르면 intent를 생성하고
- intent의 타입을 application으로 설정하여 startActivityForResult를 실행시킨다
- requestCode = 999로 설정

```java
    // 파일 탐색기 호출
    public void chooseFile(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/*"); // 갤러리 image/*, 동영상 video/*
        startActivityForResult(intent.createChooser(intent, "Select App"), 999);
    }
```

### onActivityResult()
- chooseFile에서 실행한 Activity가 종료되면
- requestCode, resultCode, data를 받아온다
- resultCode가 성공코드이면 data의 uri를 받아와서
- upload(uri)를 실행시킨다

```java
    // 파일이 선택되면 호출
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri uri = data.getData();
            upload(uri);
        }
    }
```

### upload()
- 파일의 uri를 받아와서
- storageRef에 files/파일이름의 형식으로 노드를 생성해준다
- putFile을 통해 파일을 storage에 넣고 성공 실패를 받아온다

```java
    public void upload(Uri file){
        // 실제 파일이 있는 경로
        // Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
        // 파이어베이스의 스토리지 node
        String temp[] = file.getPath().split("/");
        String filename = temp[temp.length-1];
        StorageReference riversRef = storageRef.child("files/"+filename);

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(StorageActivity.this, exception.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
```

### setIdAndToken
- [UserAdapter](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FirebaseBasic2/app/src/README.md)의 interface를 구현한다
- userAdapter에서 유저클릭시 id,string을 받아와서 txt_id, txt_token에 해당 정보를 띄어준다

```java
    @Override
    public void setIdAndToken(String id, String token) {
        txt_id.setText(id);
        txt_token.setText(token);
    }
}
```
