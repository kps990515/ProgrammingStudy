# 애니메이터 완성

![예시](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/Animation/%EC%98%88%EC%8B%9C2.png)

### [전체코드](https://github.com/kps990515/ProgrammingStudy/blob/master/Android/Animation/app/src/main/java/org/andriodtown/animation/JoystickActivity.java)

#### onClick
```java
    int playerX=0;
    int playerY=0;
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnleft: left(); break;
            case R.id.btnright: right(); break;
            case R.id.btnup: up(); break;
            case R.id.btndown: down(); break;
        }
    }
```

#### 함수들
- 방향키를 누르면 그쪽 방향으로 이동
```java
    private void left()
    {
        playerX-=100;
        move();
    }
    private void right()
    {
        playerX+=100;
        move();
    }
    private void up()
    {
        playerY-=100;
        move();
    }
    private void down()
    {
        playerY+=100;
        move();
    }
```

### 애니메이터 선언
- 애니메이터는 굳이 xml로 분리안시켜도 된다
- 위에서 받아온 playerY, playerX만큼 이동시킨다
```java
    private void move()
    {
        ObjectAnimator aniY = ObjectAnimator.ofFloat(
                btnplayer, "translationY", playerY
        );
        ObjectAnimator aniX = ObjectAnimator.ofFloat(
                btnplayer, "translationX", playerX
        );
        AnimatorSet aniset = new AnimatorSet();
        // set을 만들어서 두개의 애니메이터 동시 시작
        aniset.playTogether(aniY, aniX);
        aniset.start();
    }
}
```
