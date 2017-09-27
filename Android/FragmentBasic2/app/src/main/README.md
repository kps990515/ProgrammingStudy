# DetailFragment

```java
public class DetailFragment extends Fragment {
    public DetailFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        init(view);
        return view;
    }
```

### init()
- Main에서 bundle로 전달된 값을 꺼내고
- bundle에 값이 존재하는 경우 bundle의 값을 textView에 설정

```java
    private TextView textView;
    private void init(View view){
        textView = (TextView)view.findViewById(R.id.textView);
        // Argument로 전달된 값 꺼내기
        Bundle bundle = getArguments();
        if(bundle!=null) {
            setText(bundle.getString("value"));
        }
    }
    public void setText(String text){
        textView.setText(text);
    }
}
```
