package org.andriodtown.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class DrawActivity extends AppCompatActivity {

    FrameLayout stage;
    RadioGroup radioColor;
    FastDraw draw;
    SeekBar seekBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        //라디오 버튼이 선택되면 draw의 paint 색상을 바꿔준다
        radioColor = (RadioGroup)findViewById(R.id.radioColor);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        stage = (FrameLayout)findViewById(R.id.stage);
        draw = new FastDraw(this);
        stage.addView(draw);
        textView = (TextView)findViewById(R.id.textView);

        radioColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                switch(id){
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
                textView.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                textView.setText(5+"");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                draw.setStrokeWidth(Float.parseFloat(textView.getText().toString()));
            }
        });
    }
}
