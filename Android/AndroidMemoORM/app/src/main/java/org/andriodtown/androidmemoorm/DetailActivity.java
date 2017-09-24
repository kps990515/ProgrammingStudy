package org.andriodtown.androidmemoorm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.andriodtown.androidmemoorm.util.FileUtil;

import java.io.IOException;

/*
상세보기 처리
 */

public class DetailActivity extends AppCompatActivity {

    private String filename;
    private String title;
    TextView dTitle;
    ImageView dImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dTitle = (TextView)findViewById(R.id.dTitle);
        dImage = (ImageView)findViewById(R.id.dImage);

        // 1. 리스트에서 넘어온 intent꺼내기
        Intent intent = getIntent();
        // 2. 인텐트에서 값을 꺼내서 담는다
        filename = intent.getStringExtra("filename");
        title = intent.getStringExtra("title");
        dTitle.setText(title);

        // 이미지를 화면에 주기 위해 파일명으로 bitmap읽어오기
        try {
            Bitmap bitmap = FileUtil.read(this,filename);
            dImage.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
