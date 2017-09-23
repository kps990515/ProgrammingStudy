package org.andriodtown.androidmemoorm;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.andriodtown.androidmemoorm.dao.PicNoteDAO;
import org.andriodtown.androidmemoorm.model.PicNote;
import org.andriodtown.androidmemoorm.util.FileUtil;

import java.io.IOException;

public class DrawActivity extends AppCompatActivity {

    FrameLayout stage;
    RadioGroup radioColor;
    FastDraw draw;
    SeekBar seekBar;
    TextView textView;
    PicNoteDAO dao;
    Button btn_save;
    EditText editTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        dbInit();

        //라디오 버튼이 선택되면 draw의 paint 색상을 바꿔준다
        radioColor = (RadioGroup) findViewById(R.id.radioColor);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        stage = (FrameLayout) findViewById(R.id.stage);
        draw = new FastDraw(this);
        stage.addView(draw);
        textView = (TextView) findViewById(R.id.textView);
        btn_save = (Button)findViewById(R.id.btn_save);
        editTitle = (EditText)findViewById(R.id.editTitle);

        radioColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch (id) {
                    case R.id.radioBlack:
                        draw.setColor(Color.BLACK);
                        break;
                    case R.id.radioCyan:
                        draw.setColor(Color.CYAN);
                        break;
                    case R.id.radioMagenta:
                        draw.setColor(Color.MAGENTA);
                        break;
                    case R.id.radioYellow:
                        draw.setColor(Color.YELLOW);
                        break;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setText(5 + "");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                draw.setStrokeWidth(Float.parseFloat(textView.getText().toString()));
            }
        });
    }
    private void dbInit(){
        dao = new PicNoteDAO(this);
    }


    public void captureCanvas(View view){
        // 0. 드로잉 캐쉬를 지워준다
        stage.destroyDrawingCache();
        // 1. 다시만든다
        stage.buildDrawingCache();
        // 2. 레이아웃에서 그려진 내용을 bitmap형태로 가져온다
        Bitmap bitmap = stage.getDrawingCache();
        // 3. 파일 저장
        String filename = editTitle.getText().toString();
        try {
            // data/data/패키지/files 밑에
            FileUtil.write(this,filename,bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 4. 데이터베이스의 경로에도 저장
        PicNote picNote = new PicNote();
        picNote.setBitmap(filename);
        picNote.setTitle(filename);
        picNote.setDatetime(System.currentTimeMillis());
        dao.create(picNote);
        // 5. Native에 다 썻다고 알려주기
        bitmap.recycle();
    }
}
