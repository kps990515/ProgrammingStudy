package org.andriodtown.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class CustomView extends View {

    // 내가 소스코드에서 생성할 떄
    public CustomView(Context context){
        super(context);
    }
    //xml에서 태그로 사용할 때 시스템에서 호출해주는 생성자
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        // 색, 두께 정하기
        Paint paint = new Paint(); //물감 - 겉모양 결정도구
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(2); // 굵기

        // 화면에 사각형 그리기
        // left = 시작점이 0일때 어디서 시작할지
        // right = 시작점이 0일때 어디까지 갈지...
        canvas.drawRect(100,100,300,200,paint);
    }
}
