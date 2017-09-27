# ListFragment

### 선언파트

```java
public class ListFragment extends Fragment {
    public ListFragment() {
        // Required empty public constructor
    }

    Context context;
    Callback callback;
```

### onAttach()
- context(MainActivity)를 받아와서
- context가 Callback형식이면 context를 Callback interface으로 형변환

```java
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if(context instanceof Callback){
            callback = (Callback)context;
        }
    }
```

### onCreateView()

```java
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        init(view);
        return view;
    }
```

### init()
- 데이터를 정의하고
- 데이터를 [CustomAdapter](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FragmentBasic2/app/src)에 붙이고
- recyclerView에 CustomAdapter를 붙이고
- recyclerView에 recyclerView 형식 중에 하나를 선택해 설정

```java
    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    public void init(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // 데이터 임의생성 후 넘겨준다
        List<String>data = new ArrayList<>();
        for(int i=0; i<100; i++){
            data.add("tempData"+i);
        }
        customAdapter = new CustomAdapter(context,callback, data);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
```

### Callback Interface

```java
    public interface Callback{
        public void goDetail(String value);
    }
}
```
