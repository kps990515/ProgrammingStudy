package org.andriodtown.androidmemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.andriodtown.androidmemo.domain.Memo;
import org.andriodtown.androidmemo.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ListView listview;
    private Button btn_write;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        init();
        /*
         내용을 파일에 작성
         - 파일쓰기
            내부저장소 - internal : 개별앱만 접근가능, 파일탐색기에서 보이지 않음
            외부저장소 - external : 모든앱이 접근가능, 권한필요
         */
    }

    private void initView(){
        btn_write = (Button)findViewById(R.id.btn_write);
        listview = (ListView)findViewById(R.id.listview);
    }

    private static final int WRITE_ACTIVITY = 99;
    private void initListener(){
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, WRITE_ACTIVITY);
            }
        });
    }

    //startActivityForResut를 통해 호출된 액티비티가 종료되는 순간 호출되는 함수
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case WRITE_ACTIVITY:
                if(resultCode == RESULT_OK) {
                    init();
                }
                break;

        }
    }

    private ArrayList<Memo> loadData() {
        // 파일목록 가져오기
        // 1. 파일이 있는 디렉토리를 가져온다(디렉토리 바뀌면 망함)
        //String dir_path = getFilesDir().getAbsolutePath();
        //File file = new File(dir_path);
        //압축하면
        /*
        File files[] = getFilesDir().listFiles();

        ArrayList<String> list = new ArrayList<>();
        for(File item : files){
            list.add(item.getName());
        }
        */
        ArrayList<Memo> result = new ArrayList<>();
        //파일목록에서 파일을 하나씩 꺼낸 후
        // Memo클래스로 전환후 result에 담는다
        for(File item : getFilesDir().listFiles()){
            try {
                String text = FileUtil.read(this, item.getName());
                Memo memo = new Memo(text);
                result.add(memo);
            } catch (Exception e) {
                Toast.makeText(this, "에러:"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }

    private void init(){
        ArrayList<Memo> list = loadData();
        ListAdapter adapter = new ListAdapter(this, list);
        listview.setAdapter(adapter);
    }
}
