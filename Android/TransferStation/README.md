# Gson & SodHanaLibrary

### SodHanaLibrary
- Json내용값을 입력해주면 자동으로 상위~하위 클래스로 변환해준다
- 동일 클래스들을 생성해주고 내용을 복사 붙여넣기 해줘서 사용

### 코드
- Row의 내용을 가져오고 싶을 때는
- 최상위 클래스 + get하위클래스 +...+get Row

```java
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Row> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        load();
    }

    private void load(){
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... params) {
                String str = Remote.getData("http://openapi.seoul.go.kr:8088/6f65416d616d6e6932346a4e516453/json/StationDayTrnsitNmpr/1/44/");
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                SubTran subTran = gson.fromJson(s,SubTran.class);
                Row[] row = subTran.getStationDayTrnsitNmpr().getRow();
                data = Arrays.asList(row);
                setList();
            }
        }.execute();
    }

    private void setList(){
        ListAdapter adapter = new ListAdapter(this,data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

```
