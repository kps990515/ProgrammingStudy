# BasicList

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/BasicList/Screenshot_1507169805.png)

### 메인부분
- 데이터를 정의
- 아답터 생성
- 아답터 리스트뷰 연결

```java
/*
    리스트 사용하기
 */
public class MainActivity extends AppCompatActivity {

    // 1. 데이터를 정의
    ArrayList<String> data = new ArrayList<>();

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 데이터를 정의
        for(int i=0; i<100; i++){
            data.add("임시값"+i);
        }

        // 2. 데이터와 리스트뷰를 연결하는 아답터를 생성
        CustomAdapter adapter = new CustomAdapter(data,this);

        // 3. 아답터와 리스트뷰를 연결
        listview = (ListView)findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }
}
```

### CustomAdapter부분

```java
// 기본 아답터 클래스를 상속받아서 구현
class CustomAdapter extends BaseAdapter{
    // 데이터 저장소를 아답터 내부에 두는 것이 컨트롤하기 편함
    ArrayList<String> data = new ArrayList<>();
    Context context;

    // 생성자
    public CustomAdapter(ArrayList<String>data, Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    // 현재 데이터의 총 개수
    public int getCount() {
        return data.size();
    }

    @Override
    // 현재 뿌려질 데이터를 리턴
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    // 뷰의 아이디를 리턴
    public long getItemId(int position) {
        return position;
    }

    @Override
    // 목록에 나타내는 아이템 하나하나를 그려준다
    // 이렇게 하는 이유는 listview에 나올 수 있는 view는 한정되어있어서 재활용!!!
    // 이렇게 안하면 계속 view, textview 새로 생성
    public View getView(int position, View view, ViewGroup viewGroup) {

        // 레이아웃 inflator로 xml파일을 View객체로 변환
        // 여기서 null은 뭔가를 눌렀을 때 리스트그룹이 새로 나오는 것이 없어서
        // null이라서 그냥 전체가 펼쳐짐
        Holder holder = null;
        if(view == null) {
            // 아이템 view를 재 사용하기 위하여 null 체크
            // 스크롤해서 view하나가 사라지면 새로운 view는 그것을 재사용
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);

            // view에 있는 텍스트 내용을 재사용하기 위해 null 체크
            // 아이템이 최초 호출될 경우 Holder에 위젯들을 담고
            // 여기서 holder클래스 생성자를 통해 listener까지 붙여줌
            holder = new Holder(view);

            // 홀더를 view에 붙여놓는다
            view.setTag(holder);
        }
        else{
            // 이미 view가 있을 경우
            // view에 붙어있는 홀더를 가져온다
            holder = (Holder)view.getTag();
        }

        // 뷰안에 있는 텍스트 위젯에 값을 입력한다
        holder.textView.setText(data.get(position));

        return view;
    }

```

### Holder클래스

```java
class Holder{
    TextView textView;
    //생성자
    public Holder(View view){
        textView = (TextView)view.findViewById(R.id.textView);
        setClickListener();
    }

    public void setClickListener(){
        textView.setOnClickListener(new View.OnClickListener() {
            // 화면에 보여지는 view는
            // 기본적으로 자신이 속한 컴포넌트의 콘텍스트를 그대로 가지고 있다

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                // intent에 내용물을 담아서 전달
                intent.putExtra("valueKey", textView.getText());
                v.getContext().startActivity(intent);
            }
        });
    }
}
```

### DetailActivity.java

```java
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // 인텐트를 통해 넘어온 값 꺼내기
        Intent intent = getIntent(); // startActivity를 통해 넘어온 intent를 꺼낸다

        /* 인텐트에서 값의 묶음인 번들을 꺼내고
         Bundle bundle = intent.getExtras();
         번들에서 최종 값을 꺼낸다
         String result = bundle.getString("valueKey");
        */

        // 인텐트에서 바로 값을 꺼내기
        String result = intent.getStringExtra("valueKey");

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(result);

    }
```
