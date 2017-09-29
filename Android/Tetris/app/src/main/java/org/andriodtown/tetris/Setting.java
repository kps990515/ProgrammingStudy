package org.andriodtown.tetris;

/**
 * Created by user on 2017-09-29.
 */

public class Setting {
    // 게임판 크기
    int height;
    int width;
    // 게임판 가로 세로 칸수
    int rows;
    int columns;
    // 기본 크기
    float g_size;

    public Setting(int width, int height, int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.height = height;
        this.width = width;

        g_size = width/rows;
    }
}
