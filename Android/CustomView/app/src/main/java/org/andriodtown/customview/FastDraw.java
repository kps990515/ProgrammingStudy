package org.andriodtown.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

// path를 이용해 빠르게
public class FastDraw extends View {
    Path path;
    Paint paint;
    ArrayList<PathTool> paths = new ArrayList<>();
    int colorTemp=Color.BLACK;
    float widthTemp=5f;

    // 소스코드에서 사용하기 때문에 생성자 파라미터는 context만 필요
    public FastDraw(Context context) {
        super(context);
        paint = new Paint();
        init();
    }

    private void init(){
        paint.setStyle(Paint.Style.STROKE);
        setColor(Color.BLACK);
        setStrokeWidth(5f);
    }
    //클릭된 color의 값을 넘겨받아 설정
    public void setColor(int color){
        if(widthTemp!=-1) {
            PathTool tool = new PathTool();
            tool.toolColor(color);
            tool.toolWidth(widthTemp);
            path = tool;
            paths.add(tool);
            colorTemp=color;
        }
    }
    public void setStrokeWidth(float width)
    {
        if(colorTemp!=-1) {
            PathTool tool = new PathTool();
            tool.toolWidth(width);
            tool.toolColor(colorTemp);
            path = tool;
            paths.add(tool);
            widthTemp=width;
        }
    }
    //화면을 그려주는 onDraw 오버라이드
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        for(PathTool tool : paths)
        {
            paint.setColor(tool.getColor());
            paint.setStrokeWidth(tool.getWidth());
            canvas.drawPath(tool, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 최초 터치시 그리지 않고 해당좌표로 이동
                path.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                // 터치가 일어나면 path를 해당좌표로 이동하면서 그린다
                path.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        // onDraw를 호출하는 메서드를 호출
        // 다른 언어에서 그림을 그려주는 함수를 호출하는 메서드는 기존 그림 유지
        // 안드로이드는 그림을 지운다 -> 그래서 x,y좌표 저장할 저장소 필요
        invalidate();

        // return이 false일 경우 touch 이벤트를 연속해서 발생시키지 않는다
        // 즉 드래그를 하면 onTouchEvent가 재 호출 되지 않는다
        return true;
    }
}

class PathTool extends Path{
    int color;
    float width;

    public void toolColor(int color){
        this.color = color;
    }

    public int getColor(){
        return this.color;
    }

    public void toolWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return this.width;
    }
}