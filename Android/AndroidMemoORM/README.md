# AndroidMemoORM

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AndroidMemoORM/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C1.PNG)

RecyclerView를 사용한 목록 만들기
1. 데이터를 정의
2. Adapter를 재정의
3. 재정의한 Adapter를 생성하면서 데이터를 담아준다
4. Adapter와 RecyclerView 컨테이너를 연결
5. RecyclerView에 레이아웃 매니저를 설정

레이아웃 매니저 종류
1. LinearLayoutManager  
 리사이클러 뷰에서 가장 많이 쓰이는 레이아웃으로 수평, 수직 스크롤을 제공하는 리스트를 만들 수 있다.
2. StaggeredGridLayoutManager  
이 레이아웃을 통해 뷰마다 크기가 다른 레이아웃을 만들 수 있다. 마치 Pinterest 같은 레이아웃 구성가능.
3. GridLayoutManager  
갤러리(GridView) 같은 격자형 리스트를 만들 수 있습니다.  
사용예시// StaggeredGrid 레이아웃을 사용한다

```java
RecyclerView.LayoutManager lm
            = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
lm = new LinearLayoutManager(this);
lm = new GridLayoutManager(this,3);
```

### MainActivity

#### 선언부
- 권한 요청 코드 & 권한 정의 배열

```java
public class MainActivity extends AppCompatActivity {

    // 0. 권한 요청 코드
    private static final int REQ_CODE = 999;
    // 1. 권한 정의
    private String permissions[] = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    PermissionUtil pUtil;
```

#### onCreate()
- Permission체크를 위해 pUtil선언 및 new
- [PermissionUtil](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemoORM/.idea)에서 권한체크 후 init()실행
```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pUtil = new PermissionUtil(REQ_CODE,permissions);
        if(pUtil.checkPermission(this)){
            init();
        }
    }
```

#### onRequestPermissionsResult()
- requestPermissions한 후 CallBack에 대한 응답

```java
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(pUtil.afterPermissionResult(requestCode, grantResults)){
            init();
        }else{
            Toast.makeText(getApplicationContext(), "권한필요!",Toast.LENGTH_SHORT).show();

        }
    }
```

#### init()
- [PicNoteDAO](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemoORM/.idea/copyright)를 new로 해서 불러옴
- DAO를 통해 모든 데이터를 읽어와서 data에 넣기
- [CustomAdapter](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/AndroidMemoORM/app/src)를 new하고 데이터 붙이기
- RecyclerView에 어뎁터 붙이기

```java
    public void init(){
        PicNoteDAO dao = new PicNoteDAO(this);
        List<PicNote> data = dao.readAll();

        //3. 재정의한 Adapter를 생성하면서 데이터를 담아준다
        CustomAdapter adapter = new CustomAdapter();
        adapter.setData(data);
        //4. Adapter와 RecyclerView 컨테이너를 연결
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        //5. RecyclerView에 레이아웃 매니저를 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
```

#### post()
- post를 누르면 그림판으로 이동

```java
    public void post(View view){
        Intent intent = new Intent(this,DrawActivity.class);
        startActivity(intent);
    }
```
