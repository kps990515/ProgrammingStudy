package org.andriodtown.androidmemoorm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.andriodtown.androidmemoorm.dao.PicNoteDAO;
import org.andriodtown.androidmemoorm.model.PicNote;

import java.util.List;

/*
RecyclerView를 사용한 목록 만들기
1. 데이터를 정의
2. Adapter를 재정의
3. 재정의한 Adapter를 생성하면서 데이터를 담아준다
4. Adapter와 RecyclerView 컨테이너를 연결
5. RecyclerView에 레이아웃 매니저를 설정
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void post(View view){
        Intent intent = new Intent(this,DrawActivity.class);
        startActivity(intent);
    }

    public void init(){
        PicNoteDAO dao = new PicNoteDAO(this);
/*
        //RecyclerView를 사용한 목록 만들기
        //1. 데이터를 정의
        for(int i=0; i<1000; i++){
            PicNote picNote = new PicNote();
            picNote.setTitle("안녕"+i);
            picNote.setDatetime(System.currentTimeMillis());
            // DB에다가 넣기
            dao.create(picNote);
        }
*/
        //2. db에서 읽어오기
        List<PicNote> data = dao.readAll();

        //3. 재정의한 Adapter를 생성하면서 데이터를 담아준다
        CustomAdapter adapter = new CustomAdapter();
        adapter.setData(data);
        //4. Adapter와 RecyclerView 컨테이너를 연결
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        //5. RecyclerView에 레이아웃 매니저를 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // 레이아웃 매니저 종류
        /*
        1. LinearLayoutManager
           - 리사이클러 뷰에서 가장 많이 쓰이는 레이아웃으로 수평, 수직 스크롤을 제공하는 리스트를 만들 수 있다.
        2. StaggeredGridLayoutManager
           - 이 레이아웃을 통해 뷰마다 크기가 다른 레이아웃을 만들 수 있다. 마치 Pinterest 같은 레이아웃 구성가능.
        3. GridLayoutManager
           - 갤러리(GridView) 같은 격자형 리스트를 만들 수 있습니다.
        - 사용예시// StaggeredGrid 레이아웃을 사용한다
            RecyclerView.LayoutManager lm
                = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            lm = new LinearLayoutManager(this);
            lm = new GridLayoutManager(this,3);
        */
}
