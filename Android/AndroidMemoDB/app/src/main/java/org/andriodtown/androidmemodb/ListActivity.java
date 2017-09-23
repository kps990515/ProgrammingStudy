package org.andriodtown.androidmemodb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.andriodtown.androidmemodb.domain.Memo;
import org.andriodtown.androidmemodb.domain.MemoDAO;

import java.util.ArrayList;
    /*
    안드로이드 sqlite 사용하기
    1. db파일을 직접 코드로 생성
    2. 로컬에서 만든 파일을 asset에 담은 후 복사 붙여넣기
        -> 우편번호처럼 기반 데이터가 필요한 db일 경우
     */

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edit_title;
    EditText edit_content;
    Button btn_create;
    Button btn_read;
    Button btn_update;
    Button btn_delete;
    TextView txt_result;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initview();
        initListener();
        init();
    }

    MemoDAO dao = null;
    private void init(){
        dao = new MemoDAO(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_create:
                createAfterRead();
                break;
            case R.id.btn_read:
                read();
                break;
            case R.id.btn_update:
                break;
            case R.id.btn_delete:
                break;
        }
    }

    // create버튼 눌렀을 떄
    private void createAfterRead(){
        Memo memo = getMemoFromScreen();
        // 생성
        create(memo);
        // 결과 안내
        showInfo("입력되었습니다");
        // 화면 초기화
        resetScreen();
        // 읽기
        read();
    }
    // -> 동작 순서
    private Memo getMemoFromScreen(){
        // 1. 화면에서 입력된 값을 가져온다
        String title = edit_title.getText().toString();
        String content = edit_content.getText().toString();
        // 2. 메모객체를 하나 생성해서 값을 담는다
        return new Memo(title,content);
    }
    public void create(Memo memo){
        dao.create(memo);
    }
    private void showInfo(String comment){
        Toast.makeText(this, comment, Toast.LENGTH_SHORT).show();
    }
    private void resetScreen(){
        // 입력 화면 초기화
        edit_title.setText("");
        edit_content.setText("");
    }

    public void read(){
        // 0. 쿼리가 있어야하지만 생략 DAO에 이미 있음
        // 1. DB 실행 후 결과값 받아서 처리
        ArrayList<Memo> data = dao.read();
        txt_result.setText("");
        for(Memo memo : data){
            txt_result.append(memo.toString());
        }
    }

    private void initview(){
        edit_title = (EditText)findViewById(R.id.edit_title);
        edit_content = (EditText)findViewById(R.id.edit_content);
        btn_create = (Button)findViewById(R.id.btn_create);
        btn_read = (Button)findViewById(R.id.btn_read);
        btn_update = (Button)findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        txt_result = (TextView)findViewById(R.id.txt_result);
    }

    private void initListener() {
        btn_create.setOnClickListener(this);
        btn_read.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        // 사용한 Database 클래스는 닫아준다.
        if(dao != null){
            dao.close();
        }
        super.onDestroy();
    }
}
