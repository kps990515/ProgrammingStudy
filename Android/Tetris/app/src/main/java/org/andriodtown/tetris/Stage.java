package org.andriodtown.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * 게임 전체를 그려주는 클래스
 */

public class Stage extends View{

    Setting setting;
    Board board;
    Preview preview;

    public Stage(Context context, Setting setting) {
        super(context);
        this.setting = setting;
        board = new Board(1,1,11,16,setting.g_size);
        preview = new Preview(13, 1, 4, 4, setting.g_size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        preview.onDraw(canvas);
        board.onDraw(canvas);
    }

    // 0. 화면을 그리기 전에 기본세팅
    public void init(){
        addBlockToPreview();
        sendPreviewToBoard();
        addBlockToPreview();
    }

    // 1. 블럭 생성
    public Block newBlock(){
        Property property = new Property();
        property.b_x = 0;
        property.b_y = 0;
        property.paint = new Paint();
        property.paint.setColor(Color.CYAN);
        property.b_g_size = setting.g_size;
        return new Block(property);
    }

    // 2. 블럭을 preview에 담기
    public void addBlockToPreview(){
        Block block = newBlock();
        preview.addBlock(block);
    }
    // 3. 블럭을 preview에서 Board로 옮김
    public void sendPreviewToBoard(){
        Block block = preview.popBlock();
        board.addBlock(block);
    }

    public void up(){
        board.up();
    }

    public void down(){
        board.down();
    }

    public void left(){
        board.left();
    }

    public void right(){
        board.right();
    }
    //
}
