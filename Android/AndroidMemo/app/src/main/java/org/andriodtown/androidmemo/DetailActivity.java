package org.andriodtown.androidmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView txt_titlev;
    TextView txt_authorv;
    TextView txt_datetimev;
    TextView txt_contentv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
        init();



        findViewById(R.id.btn_backv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void init(){
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1); // 던져주는 값이 이상하면 -1
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String content = intent.getStringExtra("content");
        String datetime = intent.getStringExtra("datetime");

        txt_titlev.setText(title);
        txt_authorv.setText(author);
        txt_contentv.setText(content);
        txt_datetimev.setText(datetime);
    }

    public void initView(){
        txt_titlev=(TextView)findViewById(R.id.txt_titlev);
        txt_authorv=(TextView)findViewById(R.id.txt_authrov);
        txt_datetimev=(TextView)findViewById(R.id.txt_datetimev);
        txt_contentv=(TextView)findViewById(R.id.txt_contentv);
    }
}
