package org.andriodtown.raindrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-10-10.
 */

public class CustomView extends View {
    Paint paint;
    List<RainDrop> rainDrops = new ArrayList<>();
    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.CYAN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(rainDrops.size() > 0) {
            // 일반 for문을 쓰는 이유는 강화된 for문으로는 remove불가
            for(int i=0; i<rainDrops.size();i++) {
                RainDrop rainDrop = rainDrops.get(i);
                paint.setColor(rainDrop.color);
                canvas.drawCircle(rainDrop.x
                        , rainDrop.y
                        , rainDrop.size
                        , paint);
            }
        }
    }

    public void addRainDrop(RainDrop rainDrop) {
        this.rainDrops.add(rainDrop);
    }
    // stage view를 갱신시켜주는 함수
    public void runStage(){
        new Thread(){
            public void run(){
                while(MainActivity.runFlag){
                    // 일반 for문을 쓰는 이유는 강화된 for문으로는 remove불가
                    for(int i=0; i<rainDrops.size(); i++){
                        RainDrop rainDrop = rainDrops.get(i);
                        if(rainDrop.y>rainDrop.floor){
                            rainDrops.remove(i);
                            i--;
                        }else{
                            rainDrop.y+=rainDrop.speed;
                        }
                    }
                    postInvalidate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
