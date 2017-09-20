package org.andriodtown.androidmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.andriodtown.androidmemo.domain.Memo;

public class DetailActivity extends AppCompatActivity {

    private TextView txt_titlev;
    private TextView txt_authorv;
    private TextView txt_datetimev;
    private TextView txt_contentv;

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

        /* 이렇게 해도 되지만 static할때보다 메모리가 더 커질수 있다. 복사값이 2개라서
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String content = intent.getStringExtra("content");
        String datetime = intent.getStringExtra("datetime");
        */

        // 이렇게 할 때는 문제는 data와 listadapter가 완전히 결합되버린다는 것
        // 또한 data가 static 선언됨
        // 그래도 이게 더 효율적
        Memo memo = ListAdapter.data.get(position);

        txt_titlev.setText(memo.getTitle());
        txt_authorv.setText(memo.getAuthor());
        txt_contentv.setText(memo.getContent());
        txt_datetimev.setText(memo.getDatetime()+"");
    }

    public void initView(){
        txt_titlev=(TextView)findViewById(R.id.txt_titlev);
        txt_authorv=(TextView)findViewById(R.id.txt_authrov);
        txt_datetimev=(TextView)findViewById(R.id.txt_datetimev);
        txt_contentv=(TextView)findViewById(R.id.txt_contentv);
    }
}
