package org.andriodtown.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.Button;

/**
 * Created by user on 2017-09-14.
 */

public class PropAniActivity extends AppCompatActivity {

    Button btngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop_ani);
        btngo = (Button) findViewById(R.id.btngo);
    }

    public void move(View view)
    {   /*
        // 1. 대상을 정의한다
        // 2. 애니메이터를 설정한다
        ObjectAnimator ani = ObjectAnimator.ofFloat(
                btngo,          // 가. 움직일 대상
                "translationY", // 나. 애니메이션 속성(움직임)
                100             // 다. 속성 값
        );
        //애니메이터 실행
        ani.start();
        */

        //복합 애니메이션
        ObjectAnimator aniY = ObjectAnimator.ofFloat(
                btngo,          // 가. 움직일 대상
                "translationY", // 나. 애니메이션 속성(움직임)
                300             // 다. 속성 값
        );
        ObjectAnimator aniX = ObjectAnimator.ofFloat(
                btngo,          // 가. 움직일 대상
                "translationX", // 나. 애니메이션 속성(움직임)
                300             // 다. 속성 값
        );
        // 애니메이션 셋에 담아서 동시에 실행할 수 있음
        AnimatorSet aniset = new AnimatorSet();
        aniset.playTogether(aniY, aniX);
        aniset.setDuration(3000);

        aniset.setInterpolator(new AnticipateInterpolator()); // 이동거리마다 속도 변화
        // 일정한 속도 유지 : LinearInterpolator
        // 점점빠르게 : accelerate_interpolator
        // 점점 느리게 이동 : decelerate_interpolator
        // 위 둘을 동시에 : accelerate_decelerate_interpolator
        // 시작위치에서 조금 뒤로 당겼다 이동 : anticipate_interpolator
        // 도착위치를 조금 지나쳤다가 도착위치로 이동 : overshoot_interpolator
        // 위 둘을 동시에 : anticipate_overshoot_interpolator
        // 도착위치에서 튕김 : bounce_interpolator
        aniset.start();
    }

    public void goJoystick(View view)
    {
        Intent intent = new Intent(this, JoystickActivity.class);
        startActivity(intent);

    }
}
