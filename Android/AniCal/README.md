# 계산기 애니메이션
- 버튼을 누르면 rotate & translate & alpha를 묶은 set이 실행되면서 버튼이 날라가면서 사라진다

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AniCal/%EC%98%88%EC%8B%9C.png)

### [전체코드](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/AniCal/app/src/main/java/org/andriodtown/anical/activity_calculator.java)

### 추가한 코드
- effect라는 함수를 만들어서 각 버튼을 클릭할 때 버튼이  
 argument로 날라가고 effect에서는 parameter로 버튼을 받게 설정
- setFillAfter를 통해서 효과가 유지되도록 설정
- 여러 애니메이션 효과를 set으로 묶어서 동시 실행되도록
```java
public void effect(Button btn) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.cold);
        btn.startAnimation(animation);
        animation.setFillAfter(true);
    }
//xml 코드
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <rotate
        android:fromDegrees="0"
        android:toDegrees="3600"
        android:duration="1000"
        />
    <translate
        android:fromXDelta="0"
        android:toXDelta="100"
        android:duration="1000"
        />
    <alpha
        android:duration="1000"
        android:fromAlpha="1.0"
        android:toAlpha="0.0"
        android:fillAfter="true"/>
</set>
```
