# CustomAdapter
- 페이지 아답터에 프래그먼트 배열을 넘겨서 동작하게 한다
- tabfragment는 하나를 부를 때 양 옆 두개를 같이 부른다

### 선언 부분
- Fragment 4개를 사용하기 때문에 COUNT에 4설정

```java
public class CustomAdapter extends FragmentStatePagerAdapter {
    private static final int COUNT = 4;
    // List<Fragment> data;

    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }
```

#### getItem()
- 속도를 위해 코드를 바꾼다
- 이렇게 하면 다음꺼 생성하고 case바뀌면 자동으로 사라짐
- new를 하더라도 다른곳에서 참조 안하면 블록끝날 시 사라짐

```java
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 1:
                return new TwoFragment();
            case 2:
                return new ThreeFragment();
            case 3:
                return new FourFragment();
            default:
                return new OneFragment();
        }
    }
```

### getCount()

```java
    @Override
    public int getCount() {
        return COUNT;
    }
}
```
