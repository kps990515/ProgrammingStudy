package org.andriodtown.subway;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.andriodtown.subway.model.Station.station.Row;
import org.andriodtown.subway.model.Station.station.StInfo;

import java.util.Arrays;
import java.util.List;

public class LinesActivity extends AppCompatActivity{
    String line;
    List<Row> data;
    RecyclerView recyclerView;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        if(intent!=null){
            line=intent.getStringExtra("Line");
        }

        load();
    }
    private void load(){
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... params) {
                String str = Remote.getData("http://openapi.seoul.go.kr:8088/48505a784c6b707333355679736259/json/SearchSTNBySubwayLineService/1/200/"
                                                    +line+"/");
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                StInfo stInfo = gson.fromJson(s,StInfo.class);
                Row[] row = stInfo.getSearchSTNBySubwayLineService().getRow();
                data = Arrays.asList(row);
                setList();
            }
        }.execute();
    }

    private void setList(){
        org.andriodtown.subway.ListAdapter listAdapter = new org.andriodtown.subway.ListAdapter(this,data);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
