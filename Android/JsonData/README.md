# JsonData

### Manifest설정
- Glide, Gson Compile
```java
compile 'com.github.bumptech.glide:glide:3.+'
compile 'com.google.code.gson:gson:2.8.2'
```
### 메인
```java
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        load();
    }
```

### load()
- AsyncTask생성
- 받아온 정보를 parse()를 통해 Gson으로 처리하기
```java
    private void load(){
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... params) {
                String str = Remote.getData("https://api.github.com/users");
                return str;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                // jsonString을 parsing
                data = parse(jsonString);
                setList();
            }
        }.execute();
    }
```

### parse()
- 받아 올 정보의 종류를 [User](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/JsonData/app)클래스에 정의해놓고
- 정보를 Gson을 통해 처리한 후
- User를 담은 List에 add

```java
    private List<User> parse(String string){
        List<User> result = new ArrayList<>();
        // 앞, 뒤 대괄호 짜르기
        string = string.substring(2,string.length()-3);
        // 문자열 분리
        String [] array = string.split("\\},\\{");
        Gson gson = new Gson();
        for(String item : array){
            item = "{"+item+"}";
            User user = gson.fromJson(item,User.class);
            result.add(user);
        }
        return result;
    }
```

### setList()
- [ListAdapter](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/JsonData/app/src)을 통해 recyclerView설정
- Glide사용

```java
    List<User> data;
    private void setList(){
        ListAdapter adapter = new ListAdapter(this,data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
```
