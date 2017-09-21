# AndroidMemo

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemo/%EC%95%88%EB%93%9C%EB%A9%94%EB%AA%A8.png)

### 포함되는 코드들

[MainActivity](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemo/app/src/main/java/org/andriodtown/androidmemo/MainActivity.java)  
[WriteActivity](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemo/app/src/main/java/org/andriodtown/androidmemo/WriteActivity.java)  
[DetailActivity](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemo/app/src/main/java/org/andriodtown/androidmemo/DetailActivity.java)  
[ListAdapter](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemo/app/src/main/java/org/andriodtown/androidmemo/ListAdapter.java)  
[Memo](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemo/app/src/main/java/org/andriodtown/androidmemo/domain/Memo.java)  
[FileUtil](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemo/app/src/main/java/org/andriodtown/androidmemo/util/FileUtil.java)

### 설명
 1. 커스텀 리스트뷰를 만들어서 목록에 번호 & 제목 & 날짜를 띄어준다
 2. 쓰기 버튼을 누르면 작성페이지로 넘어가서 제목 & 작성자 & 내용 입력 가능
 3. 저장을 누르면 목록에 띄어줌
 4. 목록에 해당 내용을 누르면 상세페이지로 이동 -> 제목 & 작성자 & 날짜 & 내용 띄어줌

### MainActivity.java
- 어플 내부 저장소로 접근해서 File들을 불러온다
- File들의 내용을 가져와서 Memo형식으로 넣는다
- Memo들을 ArrayList에 넣는다
- [WriteActivity](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemo/app/src/main/java/org/andriodtown/androidmemo/WriteActivity.java) 이동

#### 메인파트

```java
public class MainActivity extends AppCompatActivity{
    private ListView listview;
    private Button btn_write;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        init();
        /*
         내용을 파일에 작성
         - 파일쓰기
            내부저장소 - internal : 개별앱만 접근가능, 파일탐색기에서 보이지 않음
            외부저장소 - external : 모든앱이 접근가능, 권한필요
         */
    }
```

### init() & loadData()
- 데이터정의 & 어뎁터생성 & 연결
- [ListAdapter](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemo/app)이동  
```java
private void init(){
        ArrayList<Memo> list = loadData();
        ListAdapter adapter = new ListAdapter(this, list);
        listview.setAdapter(adapter);
    }

    private ArrayList<Memo> loadData() {
        // 파일목록 가져오기
        // 1. 파일이 있는 디렉토리를 가져온다(디렉토리 바뀌면 망함)
        //String dir_path = getFilesDir().getAbsolutePath();
        //File file = new File(dir_path);
        //압축하면
        /*
        File files[] = getFilesDir().listFiles();

        ArrayList<String> list = new ArrayList<>();
        for(File item : files){
            list.add(item.getName());
        }
        */
        ArrayList<Memo> result = new ArrayList<>();
        //파일목록에서 파일을 하나씩 꺼낸 후
        // Memo클래스로 전환후 result에 담는다
        for(File item : getFilesDir().listFiles()){
            try {
                String text = FileUtil.read(this, item.getName());
                Memo memo = new Memo(text);
                result.add(memo);
            } catch (Exception e) {
                Toast.makeText(this, "에러:"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }
```

### initview() 생략

### initListener & onActivityResult

- 실행할 activity를 숫자로 값을 주고
- 실행완료되면 완료코드를 받는다

```java
private static final int WRITE_ACTIVITY = 99;
    private void initListener(){
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, WRITE_ACTIVITY);
            }
        });
    }

    //startActivityForResut를 통해 호출된 액티비티가 종료되는 순간 호출되는 함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case WRITE_ACTIVITY:
                if(resultCode == RESULT_OK) {
                    init();
                }
                break;

        }
    }
```
