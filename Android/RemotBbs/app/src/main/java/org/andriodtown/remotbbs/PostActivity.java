package org.andriodtown.remotbbs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.andriodtown.remotbbs.model.Data;
import org.andriodtown.remotbbs.model.Result;

public class PostActivity extends AppCompatActivity {

    Button send;
    EditText edit_title;
    EditText edit_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initView();

    }
    public void send(View v){
        String title = edit_title.getText().toString();
        String content = edit_content.getText().toString();
        Data data = new Data();
        data.setTitle(title);
        data.setContent(content);
        String deviceID = android.provider.Settings.Secure.getString(
                getContentResolver(), Settings.Secure.ANDROID_ID);
        data.setUser_id(deviceID);

        new AsyncTask<Data,Void,Result>(){

            @Override
            protected Result doInBackground(Data... data) {
                Gson gson = new Gson();
                String json = gson.toJson(data[0]);
                String result_string = Remote.sendPost("http://192.168.1.172:8090/bbs",json);
                Result result = gson.fromJson(result_string,Result.class);

                return result;
            }

            @Override
            protected void onPostExecute(Result result) {
                if(result==null || !result.getSucess()){
                    Toast.makeText(PostActivity.this, "오류",Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        }.execute(data);
    }

    private void initView(){
        send = findViewById(R.id.btn_send);
        edit_title = findViewById(R.id.edit_title);
        edit_content = findViewById(R.id.edit_content);
    }
}
