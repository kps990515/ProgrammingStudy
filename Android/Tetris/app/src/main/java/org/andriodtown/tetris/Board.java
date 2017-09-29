package org.andriodtown.tetris;

import android.graphics.Canvas;

/**
 * 테트리스 게임이 진행되는 영역 클래스
 */

public class Board implements Block.Parent {

    float g_size;
    float x;
    float y;
    float columns;
    float rows;

    Block block;

    public Board(float x, float y, int columns, int rows, float g_size){
        this.x = x;
        this.y = y;
        this.columns = columns;
        this.rows = rows;
        this.g_size = g_size;
    }

    public void addBlock(Block block) {
        this.block = block;
        block.property.b_x=4;
        block.setParent(this);
    }

    public void onDraw(Canvas canvas){
        block.onDraw(canvas);
    }

    public void up(){
        block.blockRotation = (block.blockRotation+1) % block.currentBlock.length;
    }

    public void down(){

    }

    public void left(){

    }

    public void right(){

    }

    @Override
    public float getX() {
        return x*g_size;
    }

    @Override
    public float getY() {
        return y*g_size;
    }
}
