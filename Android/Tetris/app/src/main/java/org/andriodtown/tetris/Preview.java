package org.andriodtown.tetris;

import android.graphics.Canvas;

/**
 * Created by user on 2017-09-29.
 */

public class Preview implements Block.Parent{

    float x;
    float y;
    float g_size;
    int columns;
    int rows;

    Block block;

    public Preview(float x, float y, int columns, int rows, float g_size){
        this.x = x;
        this.y = y;
        this.columns = columns;
        this.rows = rows;
        this.g_size = g_size;
    }

    public void addBlock(Block block) {
        this.block = block;
        block.setParent(this);
    }

    public Block popBlock() {
        return block;
    }

    public void onDraw(Canvas canvas){

        block.onDraw(canvas);
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
