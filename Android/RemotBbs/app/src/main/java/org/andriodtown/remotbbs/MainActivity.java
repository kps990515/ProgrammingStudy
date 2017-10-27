package org.andriodtown.remotbbs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.andriodtown.remotbbs.model.Result;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btn_post;
    public static final int POST = 999;
    Intent postIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postIntent= new Intent(this,PostActivity.class);
        load();
        initView();
    }

    private int page = 1;
    private void load(){
        new AsyncTask<Void,Void,Result>() {
            @Override
            protected Result doInBackground(Void... voids) {
                String result = Remote.getData("http://192.168.1.172:8090/bbs?type=all&page="+page);
                Gson gson = new Gson();
                Result data = gson.fromJson(result, Result.class);
                return data;
            }

            @Override
            protected void onPostExecute(Result result) {
                if(result.getSucess()){
                    if(page==1){
                        setList(result);
                    }
                    else if(page>1){
                        addList(result);
                    }
                    page++;
                }

            }
        }.execute();
    }
    RecyclerAdapter adapter;
    LinearLayoutManager layoutManager;
    private void setList(Result result){
        adapter = new RecyclerAdapter(result.getData());
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void addList(Result result){
        adapter.addDataAndRefresh(result.getData());
    }

    private void initView(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                int totalCount = layoutManager.getItemCount();
                int lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition(); //화면에 보이는 마지막 아이템위치
                // 현재 바닥에 도달했으면
                if(lastItemPosition==totalCount-5){
                    load();
                }
            }
        });
        btn_post = findViewById(R.id.btn_post);
    }

    public void post(View v){
        // intent를 실행하고 다시 정보를 받아온다
        startActivityForResult(postIntent,POST);
    }
    // 받아온 정보가 들어가는 함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case POST:
                if(resultCode == RESULT_OK){

                }else{

                }
                break;
        }
    }
}
