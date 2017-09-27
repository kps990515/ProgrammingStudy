# FragmentBasic2

### Manifest 참고사항
- 가로, 세로 변경 시 글씨가 겹쳐보이는 현상은 변경 될 때마다 onCreate()가 실행되기 때문
- configChange를 설정함으로서 가로 세로, 사이즈 바귈 때 onCreate()다시 안불러오게 해야함

### MainActivity

### 선언파트
- FragmentBasic과 동일하게 Callback implements해준다
- savedInstanceState는 액티비티 상태를 저장해 놓는다
- savedInstanceState가 null이 아닌 경우 onCreate()를 다시 하지 않도록 설정해준다(가로, 세로 때문에)

```java
public class MainActivity extends AppCompatActivity implements ListFragment.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 코드 검색해보기
        if(savedInstanceState!=null){
            return;
        }
        init();
    }
```

### init()
- 화면이 세로이면 initFragment()실행

```java
        public void init() {
        // 화면 세로 가로 체크
            if (getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_PORTRAIT) {
                     initFragment();
            }
        }
```

### initFragment()
- FragmentManager를 불러오고
- Transaction 실행하고
- Transaction에 [ListFragment](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FragmentBasic2/app)를 담은 화면을 더해서
- Commit 시켜준다

```java
        private void initFragment(){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new org.andriodtown.fragmentbasic2.ListFragment())
                    .commit();
        }
```

### goDetail버튼
- 화면이 세로이면
- [DetailFragment](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/FragmentBasic2/app/src/main) & Bundle을 new 해준다
- bundle에다가 value값을 넣어준다(value는 textView의 값)
- detailFragment에 bundle을 넣어준다
- detailFragment를 화면에 넣어준다

- 화면이 가로면
- Fragment2를 DetailFragment의 화면으로 만든다
- detailFragment가 존재하면 value의 값을 출력한다

```java
    @Override
    public void goDetail(String value) {
        if(getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT){
            // 디테일 프래그먼트를 화면에 보이면서 값을 전달
            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("value", value);
            detailFragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container,detailFragment)
                    .addToBackStack(null)
                    .commit();
        }else {
            // 현재 레이아웃에 삽입되어 있는 프래그먼트를 가져온다
            DetailFragment detailFragment = (DetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment2);
            if(detailFragment != null) {
                detailFragment.setText(value);
            }
        }
    }
}
```        
