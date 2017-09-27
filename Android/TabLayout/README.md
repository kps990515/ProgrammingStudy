# TabLayout

### MainActivity

#### 선언파트
- 화면 이동하는 버튼이 있는 tabLayout
- 여러 Fragment를 담는 viewPager

```java
public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTabLayout();
        setViewPager();
        // TabLayout을 ViewPager에 연결
        setListener();

    }
```

#### setListener()
- tabLayout 누르면 viewPager 변경
- viewPager 변경 시 tabLayout 변경

```java
    private void setListener(){
        // tabLayout을 누르면 viewPager 자동변경
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        // viewPager를 바꾸면 tabLayout 자동변경
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
```

#### setTabLayout()
- TabLayout에 tab 이식하기

```java
    private void setTabLayout(){
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("One"));
        tabLayout.addTab(tabLayout.newTab().setText("Two"));
        tabLayout.addTab(tabLayout.newTab().setText("Three"));
        tabLayout.addTab(tabLayout.newTab().setText("Four"));
    }
```

#### setViewPager()
- viewPager에 [CustomAdapter](https://github.com/kps990515/ProgrammingStudy/tree/master/Android/TabLayout/app) 이식

```java
    private void setViewPager(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        /*
        List<Fragment> data = new ArrayList<>();
        data.add(new OneFragment());
        data.add(new TwoFragment());
        data.add(new ThreeFragment());
        data.add(new FourFragment());
        */
        CustomAdapter customAdapter = new CustomAdapter(getSupportFragmentManager());
        viewPager.setAdapter(customAdapter);
    }
}
```
