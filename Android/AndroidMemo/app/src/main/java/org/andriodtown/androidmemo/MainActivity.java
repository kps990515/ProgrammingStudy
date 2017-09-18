package org.andriodtown.androidmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Long> datetime = new ArrayList<>();
    ArrayList<String> no = new ArrayList<>();
    ListView listview;
    Button btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

        name.add("김재호");
        datetime.add(getTime());

    }

    public long getTime(){
        long now = System.currentTimeMillis();

    }

    @Override
    public void onClick(View v) {

    }
    public void initView(){
        btn_write = (Button)findViewById(R.id.btn_write);
        listview = (ListView)findViewById(R.id.listview);
    }
    public void initListener(){
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WriteActivity.class);
                startActivity(intent);
            }
        });
    }
}
