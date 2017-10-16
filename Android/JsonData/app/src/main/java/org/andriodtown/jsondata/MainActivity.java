package org.andriodtown.jsondata;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.andriodtown.jsondata.user.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

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
                String str = Remote.getData("https://api.github.com/users");
                return str;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                // jsonString을 parsing
                data = parse(jsonString);
                setList();
            }
        }.execute();
    }

    private List<User> parse(String string){
        List<User> result = new ArrayList<>();
        // 앞, 뒤 대괄호 짜르기
        string = string.substring(2,string.length()-3);
        // 문자열 분리
        String [] array = string.split("\\},\\{");
        Gson gson = new Gson();
        for(String item : array){
            item = "{"+item+"}";
            User user = gson.fromJson(item,User.class);
            result.add(user);
        }
        return result;
    }

    List<User> data;
    private void setList(){
        ListAdapter adapter = new ListAdapter(this,data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
