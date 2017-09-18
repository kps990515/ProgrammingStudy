package org.andriodtown.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import static android.view.MotionEvent.ACTION_DOWN;
import static org.andriodtown.customview.R.styleable.AniButton_animation;


// 버튼이 아닌 AppCompatButton을 상속하는 이유는
// 모든 버전 호환을 위해(v4,v7)
public class AniButton extends AppCompatButton implements View.OnTouchListener {
    /*
     에니메이션 속성이 true일 경우
     scale애니메이션을 사용해서 클릭시 살짝 커졌다 작아지는 버튼 만들기
     */
    public AniButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 1. attrs.xml에 정의된 속성을 가져온다
        // context에 담을 속성은 activty.xml에 있는 버튼 정보
        // AttributeSet attrs에 담아서 context에 보낸다
        // 그 정보를 TypedArray에 저장
        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.AniButton);
        // 2. 해당 이름으로 정의된 속성의 개수를 가져온다
        int size = typed.getIndexCount();
        Log.d("AniButton","size="+size);
        // 3. 반복문을 돌면서 해당 속성에 대한 처리를 해준다
        for(int i=0; i<size ; i++)
        {
            // 3.1 현재 배열에 있는 속성 아이디 가져오기
            int current_attr = typed.getIndex(i);
            switch(current_attr) {
                case AniButton_animation:
                    // 여기서 가져온 값은 xml에 정의한 custom.animation 값
                    String animation = typed.getString(current_attr);
                    //이렇게 하면 animation값이 null이라도 에러 안남
                    if("true".equals(animation))
                    {
                        String currentText = getText() + "";
                        setText("[animation]\n" + currentText);
                        setOnTouchListener(this);
                        break;
                    }
                case R.styleable.AniButton_delimeter:
                    break;
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case ACTION_DOWN:
                runAnimation(v);
                break;

        }
        return false;
    }
    public void runAnimation(View v){
        Animation anim = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale);
        startAnimation(anim);
    }
}
