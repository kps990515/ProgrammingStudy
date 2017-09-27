# ListFragment

### 선언파트
- constructor가 자동생성되지만 코드를 작성하면 안됨
- Fragment를 상속받는다

```java
public class ListFragment extends Fragment {
    public ListFragment() {
        // Required empty public constructor
        // 여기는 코드를 작성하면 안됨
    }
```

### onAttach
- Argument로 받아오는 Context는 여기서는 MainActivity
- context가 만약 Callback이면 (Main에서 이미 implements함)
- context를 인터페이스로 캐스팅해서 callback 으로사용
- 마지막에는 Fragment를 Activity에 Attach

```java
    @Override
    public void onAttach(Context context) { // 이 context가 나를 삽입한 액티비티(여기서는Main)
        Log.d("Fragment","==========OnAttach()");
        // 1. 나를 사용한 액티비티가 내가 제공한 인터페이스를 구현했는지 확인
        if(context instanceof Callback){ // Main은 Callback을 이식함
            callback = (Callback) context; // 2. 구현했다면 인터페이스로 캐스팅해서 사용(callback = Main)
        }
        super.onAttach(context);
    }
```

### onDetach()

```java
    @Override
    public void onDetach() {
        Log.d("Fragment","==========OnDetach()");
        super.onDetach();
    }
```

### Callback interface
- goDetail()을 구현하도록 강제한다

```java
public interface Callback{
    public void goDetail();
}
```

### onCreateView()
- callback을 null로 설정해 놓고
- ListFragment의 화면을 MainActivity에 CreateView한다
- goDetail버튼을 클릭 시 callback에서 goDetail()을 실행시킨다
- 여기서 callback은 MainActivity을 Callback으로 형변화 시킨 것
- 결국 Callback인터페이스를 호출하고 구현해 놓은 곳으로 가서 해당 Function 실행

```java
    Callback callback = null;
    Button goDetail;
    // 얘는 액티비티에 부착되면서 동작시작
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        goDetail = (Button)view.findViewById(R.id.btn_detail);
        goDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.goDetail();
                // 내가 설계해 놓은 인터페이스를 호출
                // 나를 사용하는 측은 callback interface를 구현해야만 한다
            }
        });
        return view;
    }
```

```java
    @Override
    public void onStart() {
        Log.d("Fragment","==========OnStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("Fragment","==========OnResume()");
        super.onResume();
    }
}
```
