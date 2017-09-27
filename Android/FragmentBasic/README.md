# FragmentBasic

### MainActivity
- 실행되는 순서
- Activity before add() -> after add() -> after commit ->OnStart()
- Fragment OnAttach() -> OnStart()
- Activity OnResume()
- Fragment OnResume()

#### onCreate()
- ListFragment.Callback을 implements해서 구체화시킨다

```java
public class MainActivity extends AppCompatActivity implements org.andriodtown.fragmentbasic.ListFragment.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

    }
```

#### initFragment()
1. FragmentManager 불러오기
2. FragmentManager를 통해 Transaction실행
3. Transaction을 통해 Activity에 [ListFragment](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FragmentBasic/app) 부착
4. Transaction Commit

```java
    public void initFragment(){
        // 1. 프래그먼트 매니저
        FragmentManager fManager = getSupportFragmentManager();
        // 2. 트랜잭션 처리자
        FragmentTransaction fTransaction = fManager.beginTransaction(); // 프래그먼트를 처리하기 위한 트랜잭션을 시작
        // 3. 액티비티 레이아웃에 프래그먼트를 부착하고
        Log.d("Activity","==========before add()");
        fTransaction.add(R.id.container, new org.andriodtown.fragmentbasic.ListFragment()); // ListFragment.onAttach(this); 가 자동실행되는 것
        Log.d("Activity","==========after add()");
        // 4. 커밋해서 완료
        fTransaction.commit();
        Log.d("Activity","==========after commit");
    }
```

#### goDetail()버튼
- 버튼을 누르면 [DetailFragment](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FragmentBasic/app/src)가 부착
- Fragment불러오는 순서는 initFragment와 동일
- 짧게 만든 코드
- addToBackStack을 한 이유는  
  원래는 Fragment는 Stack에 담기지 않는다 -> back을 해도 히스토리가 없어서 수행 불가  
  이 명령어로 호출하면 Transaction 전체를 Stack에 담는다  
    (Fragment가 담기는 건 아님)

```java
    @Override
    public void goDetail(){
        Log.d("Activity","==========BeforeAdd()");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container,new DetailFragment())
                // 원래는 Fragment는 Stack에 담기지 않는다 -> back을 해도 히스토리가 없어서 수행 불가
                // 이 명령어로 호출하면 Transaction 전체를 Stack에 담는다(Fragment가 담기는 건 아님)
                .addToBackStack(null)
                .commit();
        Log.d("Activity","==========AfterCommit()");
    }
```

```java
    @Override
    protected void onStart() {
        Log.d("Activity","==========OnStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("Activity","==========OnResume()");
        super.onResume();
    }
}
```
