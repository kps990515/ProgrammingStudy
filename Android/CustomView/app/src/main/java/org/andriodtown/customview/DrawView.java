package org.andriodtown.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import static android.R.attr.x;
import static android.R.attr.y;

// 문제는 이렇게 하면 인식속도가 느림
public class DrawView extends View{

        // 반지름
        float r=10;
        // 좌표값을 저장하는 저장소
        ArrayList<Float> xlist = new ArrayList<>();
        ArrayList<Float> ylist = new ArrayList<>();

        // 소스코드에서 사용하기 때문에 생성자 파라미터는 context만 필요
        public DrawView(Context context) {
            super(context);
        }
        //화면을 그려주는 onDraw 오버라이드
        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            // 1. 화면에 터치가 되면
            // 2. 연속해서 그림을 그려준다
            // 2.1 터치된 좌표에 작은 동그라미를 그려준다

            if(x>-1 && y>-1){
                Paint paint = new Paint();
                paint.setColor(Color.CYAN);
                //저장된 그림을 출력
                for(int i=0; i<xlist.size(); i++)
                {
                    canvas.drawCircle(xlist.get(i),ylist.get(i),r,paint);
                }
                //새로운 점들을 그려나가기
                canvas.drawCircle(x,y,r,paint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // 터치가 일어나면 좌표값을 세팅해준다
            float x = event.getX();
            float y = event.getY();
            // 좌표값 저장
            xlist.add(x);
            ylist.add(y);

            // onDraw를 호출하는 메서드를 호출
            // 다른 언어에서 그림을 그려주는 함수를 호출하는 메서드는 기존 그림 유지
            // 안드로이드는 그림을 지운다 -> 그래서 x,y좌표 저장할 저장소 필요
            invalidate();

            // return이 false일 경우 touch 이벤트를 연속해서 발생시키지 않는다
            // 즉 드래그를 하면 onTouchEvent가 재 호출 되지 않는다
            return true;
        }
}
