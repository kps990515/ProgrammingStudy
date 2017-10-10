package org.andriodtown.raindrop;

/**
 * Created by user on 2017-10-10.
 */

class RainDrop extends Thread{
    // 속성
    float x;
    float y;
    float speed;
    float size;
    int color;
    // 생명주기 - 바닥에 닿을 때 까지
    float floor;
    // 기능

    public RainDrop(float x, float y, float speed, float size, int color, float floor) {
        this.x =x;
        this.y=y;
        this.speed=speed;
        this.size=size;
        this.color=color;
        this.floor=floor;
    }
}
