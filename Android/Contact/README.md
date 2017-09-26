# Contact

### 설명
- content provider가 다른 앱이 자신의 앱 데이터에 접근할 수 있도록 도와줌
- content resolver는 다른앱이 그 데이터를 받을수 있게 해줌  
  (sql데이터베이스 연결할때와 비슷)
- 문제는 여러 데이터를 한꺼번에 받아오면 느려짐
- URI = 파일의 경로를 URI라는 객체로 만들어서 사용
- task란 하나의 앱에서 다른앱을 실행시킬때 그 둘을 묶어주는 것  
(정확히는 앱에서 실행시키는 액티비티들을 묶어줌)

### Content Resolver 사용하기
1. Content Resolver를 불러오기
2. 데이터 URI 정의 <- 일종의 DB에서의 테이블 이름
3. 데이터 URI에서 가져올 컬럼명 정의
    - 조건절을 정의할 수도 있음
4. Content Resolver로 쿼리한 데이터를 Cursor에 담는다
5. Cursor에 담긴 데이터를 반복문을 돌면서 처리한다

### 전체 코드들
[Contact.java](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/Contact/app/src/main/java/org/andriodtown/contact/model/Contact.java)
[PermissionUtil](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/Contact/app/src/main/java/org/andriodtown/contact/util/PermissionUtil.java)
[CustomAdapter](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/Contact/app/src/main/java/org/andriodtown/contact/CustomAdapter.java)
[MainActivity](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/Contact/app/src/main/java/org/andriodtown/contact/MainActivity.java)

### MainActivity
- load()부분 빼고 다른 부분은 MemoORM과 동일
- 결국엔 RecyclerView

#### load()
1. ContentResolver를 만들고
2. URI 설정 후
3. URI에서 가져올 컬럼들을 설정 한 후
4. 결과물을 Cursor에 담고
5. Cursor를 반복문에 돌려 모든 정보를 가져온 후 저장한다

```java
private List<Contact> load() {
    List<Contact> contacts = new ArrayList<>();
    // 1. Content Resolver 불러오기
    ContentResolver resolver = getContentResolver();

    // 2. 데이터 URI 정의
    // 전화번호 uri : ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    // 주소록 uri : ContactsContract.Contacts.CONTENT_URI;
    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    // 3. 가져올 컬럼 정의
    // 이미 정의된 컬럼들이 존재한다
    String projection[] = {
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    // 4. 쿼리 결과를 Cursor에 담기
    Cursor cursor = resolver.query(uri, projection, null, null, null);

    // 5. Cursor 반복처리
    if (cursor != null) {
        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                switch (cursor.getColumnName(i)) {
                    case ContactsContract.CommonDataKinds.Phone.CONTACT_ID:
                        contact.setId(cursor.getInt(i));
                        break;
                    case ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME:
                        contact.setName(cursor.getString(i));
                        break;
                    case ContactsContract.CommonDataKinds.Phone.NUMBER:
                        contact.setNumber(cursor.getString(i));
                        break;
                    }
                }
            contacts.add(contact);
            }
        }
    return contacts;
    }
}
```

```java
public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE = 999;
    private String permissions[] = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE
    };
    PermissionUtil pUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pUtil = new PermissionUtil(REQ_CODE,permissions);
        if(pUtil.checkPermission(this)){
            init();
        }
    }

    public void init(){
        List<Contact> data = load();
        CustomAdapter adapter = new CustomAdapter();
        adapter.setData(data);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

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
