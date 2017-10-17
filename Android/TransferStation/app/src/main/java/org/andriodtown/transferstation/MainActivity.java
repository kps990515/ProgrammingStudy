package org.andriodtown.transferstation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.andriodtown.transferstation.model.Row;
import org.andriodtown.transferstation.model.SubTran;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Row> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        load();
    }

    private void load(){
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... params) {
                String str = Remote.getData("http://openapi.seoul.go.kr:8088/6f65416d616d6e6932346a4e516453/json/StationDayTrnsitNmpr/1/44/");
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                SubTran subTran = gson.fromJson(s,SubTran.class);
                Row[] row = subTran.getStationDayTrnsitNmpr().getRow();
                data = Arrays.asList(row);
                setList();
            }
        }.execute();
    }

    private List<Row> parse(String string){
        Gson gson = new Gson();
        SubTran subTran = gson.fromJson(string,SubTran.class);
        if(!subTran.getStationDayTrnsitNmpr().getList_total_count().equals("0")) {
            Row[] row = subTran.getStationDayTrnsitNmpr().getRow();
            return Arrays.asList(row);
        }

        return new ArrayList<Row>();
    }

    private void setList(){
        ListAdapter adapter = new ListAdapter(this,data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
