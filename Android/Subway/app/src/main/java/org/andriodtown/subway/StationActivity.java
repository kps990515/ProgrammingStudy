package org.andriodtown.subway;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.andriodtown.subway.subwaytime.RealtimeArrivalList;
import org.andriodtown.subway.subwaytime.SbInfo;

import java.util.Arrays;
import java.util.List;

public class StationActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TextView txt_station;
    TextView txt_subway;
    RadioButton radio_week;
    RadioButton radio_sat;
    RadioButton radio_holi;
    ListView listView;
    RadioGroup radioGroup;
    String stationCD="";
    String stationNM="";
    List<RealtimeArrivalList>data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        Intent intent = getIntent();
        if(intent!=null){
            stationCD = intent.getStringExtra("StationCD");
            stationNM = intent.getStringExtra("StationNM");
        }
        load();
        initView();
        initTabs();
        initListener();
        set();

    }
    private void load(){
        new AsyncTask<Void,Void,String>(){

            @Override
            protected String doInBackground(Void... params) {
                String str = Remote.getData("http://swopenapi.seoul.go.kr/api/subway/48505a784c6b707333355679736259/json/realtimeStationArrival/0/10/"+stationNM);
                return str;
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                SbInfo sbInfo = gson.fromJson(s,SbInfo.class);
                RealtimeArrivalList [] ral = sbInfo.getRealtimeArrivalList();
                data = Arrays.asList(ral);
            }
        }.execute();
    }
    private void initView(){
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        txt_station = (TextView)findViewById(R.id.txt_station);
        txt_subway = (TextView)findViewById(R.id.txt_subway);
        radio_week = (RadioButton)findViewById(R.id.radio_week);
        radio_sat = (RadioButton)findViewById(R.id.radio_sat);
        radio_holi = (RadioButton)findViewById(R.id.radio_holi);
        listView = (ListView)findViewById(R.id.listView);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.check(R.id.radio_week);
    }
    private void initTabs(){
        tabLayout.addTab(tabLayout.newTab().setText(data.get(0).getStatnFid()));
        tabLayout.addTab(tabLayout.newTab().setText(data.get(0).getStatnTid()));
    }
    private void initListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.radio_week:

                        break;
                    case R.id.radio_sat:

                        break;
                    case R.id.radio_holi:

                        break;
                }
            }
        });
    }
    private void set(){
        txt_station.setText(stationNM);
    }

}
