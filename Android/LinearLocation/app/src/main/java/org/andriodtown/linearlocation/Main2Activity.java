package org.andriodtown.linearlocation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private ConstraintLayout stage;
    private Button btnGoal;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initListener();
    }
    @Override
    public void onClick(View view) {
        // 클릭된 버튼을 사용하기 위해 시스템에서 넘겨받은 뷰를
        // 원래의 버튼으로 캐스팅해둔다.
        if(view instanceof Button){ // view 변수가 Button 클래스의 인스턴스인지를 체크
            Button original = (Button) view;

            // 실제 날아갈 더미를 생성해서 상위 레이아웃에 담은후에 처리한다
            final Button dummy = new Button(this);
            // 생성된 더미에 클릭한 버튼의 속성값을 적용
            dummy.setText(original.getText().toString());
            dummy.setWidth(original.getWidth());
            dummy.setHeight(original.getHeight());
            dummy.setBackgroundColor(Color.RED);

            // 부모 레이아웃을 가져와서 원래 클래스로 캐스팅
            LinearLayout parent = (LinearLayout) original.getParent();

            // 부모 레이아웃의 위치값과 원래 버튼의 위치값을 더해서 좌표를 정한다.
            dummy.setX( original.getX() + parent.getX());
            dummy.setY( original.getY() + parent.getY());

            // 더미를 상위 레이아웃에 담는다
            stage.addView(dummy);
            int duration = 1000;
            ObjectAnimator aniY = ObjectAnimator.ofFloat(
                    dummy, "y", btnGoal.getY()
            );
            ObjectAnimator aniX = ObjectAnimator.ofFloat(
                    dummy, "x", btnGoal.getX()
            );
            ObjectAnimator aniR = ObjectAnimator.ofFloat(
                    dummy, "rotation", 360*duration
            );
            AnimatorSet aniSet = new AnimatorSet();
            aniSet.setInterpolator(new AccelerateDecelerateInterpolator());
            aniSet.playTogether(aniY, aniX, aniR);
            aniSet.setDuration(duration);
            // 애니메이션 종료를 체크하기 위한 리스너를 달아준다.
            aniSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    stage.removeView(dummy);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });


            aniSet.start();
        }
    }

    private void initView() {
        stage = (ConstraintLayout) findViewById(R.id.stage);
        btnGoal = (Button) findViewById(R.id.btnGoal);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
    }
    private void initListener(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
}
