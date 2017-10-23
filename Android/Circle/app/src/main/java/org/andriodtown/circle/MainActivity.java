package org.andriodtown.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    MyCircle circle;
    Button button;
    EditText editText;
    FrameLayout paper;
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.ll);
        paper = (FrameLayout) findViewById(R.id.paper);
        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);

        circle = new MyCircle(this,100);
        paper.addView(circle);
    }

    public void onButton(View v){
        int radius = Integer.parseInt(editText.getText().toString());
        paper.removeView(circle);
        circle = new MyCircle(this,radius);
        paper.addView(circle);

    }

    class MyCircle extends View {
        int radius;
        public MyCircle(Context context, int radius) {
            super(context);
            this.radius = radius;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint p = new Paint();
            p.setColor(Color.GREEN);
            canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2-50,radius,p);
        }
    }
}