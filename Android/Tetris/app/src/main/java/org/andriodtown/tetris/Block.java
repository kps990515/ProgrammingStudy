package org.andriodtown.tetris;

import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by user on 2017-09-29.
 */

public class Block {

    Property property;
    Parent parent= null;
    int blockNumber = 0;
    int blockRotation = 0;
    int currentBlock[][];
    Blocks blocks;

    public Block(Property property){
        this.property = property;
        Random random = new Random();
        blockNumber = random.nextInt(7);
        blockRotation = 0;
    }

    // 나를 그리는 함수
    public void onDraw(Canvas canvas){
        currentBlock = blocks.blocks[blockNumber][blockRotation];
        property.paint.setColor(blocks.colors[blockNumber]);
        for(int i=0; i<currentBlock.length; i++){
            for(int j=0; j<currentBlock[0].length; j++){
                if(currentBlock[i][j]>0) {
                    canvas.drawRect(
                            parent.getX() + (property.b_x + i)*property.b_g_size,
                            parent.getY() + (property.b_y + j)*property.b_g_size,
                            parent.getX() + (property.b_x + i + property.b_g_size)*property.b_g_size,
                            parent.getY() + (property.b_y + j + property.b_g_size)*property.b_g_size ,
                            property.paint);
                }
            }
        }
    }

    // 이동함수
    public void up(){
        property.b_y-=property.b_g_size;
    }
    public void down(){
        property.b_y+=property.b_g_size;
    }
    public void left(){
        property.b_x-=property.b_g_size;
    }
    public void right(){
        property.b_x+=property.b_g_size;
    }

    public void setParent(Parent parent){
        this.parent = parent;
    }

    public interface Parent {
        float getX();
        float getY();
    }
}
