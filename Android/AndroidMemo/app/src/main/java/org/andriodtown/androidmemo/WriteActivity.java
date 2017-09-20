package org.andriodtown.androidmemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.andriodtown.androidmemo.domain.Memo;
import org.andriodtown.androidmemo.util.FileUtil;

import java.io.IOException;

public class WriteActivity extends AppCompatActivity {
    Button btn_save;
    EditText edit_title;
    EditText edit_author;
    EditText edit_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        initView();
        initListener();
    }
    private Memo getMemo(){
        Memo memo = new Memo();
        memo.setNo(1);
        memo.setTitle(edit_title.getText().toString());
        memo.setAuthor(edit_author.getText().toString());
        memo.setContent(edit_content.getText().toString());
        memo.setDatetime(System.currentTimeMillis());
        return memo;
    }
    public void write(Memo memo){
        try {
            String filename = System.currentTimeMillis() + ".txt";
            FileUtil.write(this, filename, memo.toString());
            setResult(RESULT_OK); //나를 호출한 액티비티로 성공 / 실패값을 넘겨준다
            Toast.makeText(this, "등록!", Toast.LENGTH_SHORT).show();
        }catch(IOException e){
            Toast.makeText(this, "에러"+e.toString(), Toast.LENGTH_SHORT).show();
        }
        //현재 액티비티 종료
        finish();
    }
    public void initView(){
        edit_author = (EditText)findViewById(R.id.edit_author);
        edit_title = (EditText)findViewById(R.id.edit_title);
        edit_content = (EditText)findViewById(R.id.edit_content);
        btn_save = (Button)findViewById(R.id.btn_save);
    }
    public void initListener(){
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memo memo = getMemo();
                write(memo);
            }
        });
    }
}
