# AndroidMemoDB
- SQLite를 연동해서 메모장 만들기
- 안드로이드 sqlite 사용하기
    1. db파일을 직접 코드로 생성
    2. 로컬에서 만든 파일을 asset에 담은 후 복사 붙여넣기  
        -> 우편번호처럼 기반 데이터가 필요한 db일 경우


### 코드
[ListActivity](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemoDB/app/src/main/java/org/andriodtown/androidmemodb/ListActivity.java)  
[DBHelper](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemoDB/app/src/main/java/org/andriodtown/androidmemodb/DBHelper.java)  
[Memo](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemoDB/app/src/main/java/org/andriodtown/androidmemodb/domain/Memo.java)  
[MemoDAO](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemoDB/app/src/main/java/org/andriodtown/androidmemodb/domain/MemoDAO.java)  

### ListActivity

```java
public class ListActivity extends AppCompatActivity implements View.OnClickListener{

//선언생략

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initview();
        initListener();
        init();
    }
```

#### init()
- 시작하면 MemoDAO를 new로 생성한다(조작위해)
- [MemoDAO](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemoDB/gradle/wrapper)로 이동
```java
    MemoDAO dao = null;
    private void init(){
        dao = new MemoDAO(this);
    }
```

#### onClick()
- 해당버튼 당 기능입력

```java
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_create:
                create();
                break;
            case R.id.btn_read:
                read();
                break;
            case R.id.btn_update:
                break;
            case R.id.btn_delete:
                break;
        }
    }
```

#### create()
- 생성버튼을 눌렀을 때
- 화면값 가져와서 쿼리를 만들고 dao에 넘겨준다
- 입력값 초기화

```java
    public void create(){
        // 1. 화면에서 입력된 값을 가져온다
        String title = edit_title.getText().toString();
        String content = edit_content.getText().toString();

        // 2. 쿼리를 만든다
        String query = "insert into memo(title, content, n_date)" +
                       " values('"+title+"','"+content+"',datetime('now','localtime')";

        // 3. DB에 실행
        dao.create(query);

        // 4. 입력 화면 초기화
        edit_title.setText("");
        edit_content.setText("");

        //5. 결과 안내
        Toast.makeText(this, "입력되었습니다!!!", Toast.LENGTH_SHORT).show();

        // 6. 읽기
        read();
    }
```

#### read()
- dao.read를 실행시켜서 모든 memo를 data에 저장
- data값을 꺼내서 화면에 출력

```java
    public void read(){
        // 0. 쿼리가 있어야하지만 생략 DAO에 이미 있음
        // 1. DB 실행 후 결과값 받아서 처리
        ArrayList<Memo> data = dao.read();
        txt_result.setText("");
        for(Memo memo : data){
            txt_result.append(memo.toString());
        }
    }
```

#### onDestroy
- 모든 조작이 끝나면 db닫아주기

```java
    @Override
    protected void onDestroy() {
        // 사용한 Database 클래스는 닫아준다.
        if(dao != null){
            dao.close();
        }
        super.onDestroy();
    }
}
```

#### initview & initListener 생략
