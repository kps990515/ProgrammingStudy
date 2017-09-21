# WriteActivity

### 설명
 1. 메모를 새로 쓸 수있는 페이지
 2. getMemo()를 통해 새로운 Memo생성 / write에 넘겨줌
 3. write에서 memo받아 FileUtil 통해 저장소에 저장

### getMemo()파트
- 메모를 새로 만들고
- 페이지에 입력한 내용을 메모에 입력한다
- [Memo.java](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemo/app/src/main/java/org/andriodtown/androidmemo)로 이동

```java
private Memo getMemo(){
        Memo memo = new Memo();
        memo.setNo(count);
        memo.setTitle(edit_title.getText().toString());
        memo.setAuthor(edit_author.getText().toString());
        memo.setContent(edit_content.getText().toString());
        memo.setDatetime(System.currentTimeMillis());
        count++;
        return memo;
    }
```

### write()파트
- 새로 생성된 memo를 받아와서
- 파일유틸을 통해 파일이름과 메모내용을 전달한다
- [FileUtil.java](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemo/app/src/main/res)로 이동

```java
public void write(Memo memo){
        try {
            String filename = System.currentTimeMillis() + ".txt";
            FileUtil.write(this, filename, memo.toString());
            setResult(RESULT_OK); //나를 호출한 액티비티로 성공 / 실패값을 넘겨준다
            Toast.makeText(this, "등록!", Toast.LENGTH_SHORT).show();
        }catch(IOException e){
            Toast.makeText(this, "에러"+e.toString(), Toast.LENGTH_SHORT).show();
        }
        //현재 액티비티 종료
        finish();
    }
```

### 그 외 파트

```java
public class WriteActivity extends AppCompatActivity {
    private Button btn_save;
    private EditText edit_title;
    private EditText edit_author;
    private EditText edit_content;
    static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        initView();
        initListener();
    }

    public void initView(){
        edit_author = (EditText)findViewById(R.id.edit_author);
        edit_title = (EditText)findViewById(R.id.edit_title);
        edit_content = (EditText)findViewById(R.id.edit_content);
        btn_save = (Button)findViewById(R.id.btn_save);
    }
    public void initListener(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memo memo = getMemo();
                write(memo);
            }
        });
    }
}
