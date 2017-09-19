package org.andriodtown.androidmemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.andriodtown.androidmemo.domain.Memo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.data;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Memo> memo = new ArrayList<>();
    ListView listview;
    Button btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();


        CustomAdapter adapter = new CustomAdapter(memo,this);

        listview = (ListView)findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }

    public String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
        String strCurDate = CurDateFormat.format(date);
        return strCurDate;
    }

    @Override
    public void onClick(View v) {

    }
    public void initView(){
        btn_write = (Button)findViewById(R.id.btn_write);
    }
    public void initListener(){
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WriteActivity.class);
                startActivity(intent);
            }
        });
    }

    class CustomAdapter extends BaseAdapter{

        ArrayList<Memo> memo = new ArrayList<>();

        Context context;

        public CustomAdapter(ArrayList<Memo> memo, Context context){
            this.memo=memo;
            this.context=context;
        }

        @Override
        public int getCount() {
            return memo.size();
        }

        @Override
        public Object getItem(int position) {
            return memo.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            // 레이아웃 inflator로 xml파일을 View객체로 변환
            // 여기서 null은 뭔가를 눌렀을 때 리스트그룹이 새로 나오는 것이 없어서
            // null이라서 그냥 전체가 펼쳐짐
            Holder holder = null;
            if(view == null) {
                // 아이템 view를 재 사용하기 위하여 null 체크
                // 스크롤해서 view하나가 사라지면 새로운 view는 그것을 재사용
                view = LayoutInflater.from(context).inflate(R.layout.list_item, null);

                // view에 있는 텍스트 내용을 재사용하기 위해 null 체크
                // 아이템이 최초 호출될 경우 Holder에 위젯들을 담고
                // 여기서 holder클래스 생성자를 통해 listener까지 붙여줌
                holder = new Holder(view,memo);

                // 홀더를 view에 붙여놓는다
                view.setTag(holder);
            }
            else{
                // 이미 view가 있을 경우
                // view에 붙어있는 홀더를 가져온다
                holder = (Holder)view.getTag();
            }

            // 뷰안에 있는 텍스트 위젯에 값을 입력한다
            holder.texttitle.setText();

            return view;
        }
    }
    class Holder{
        TextView texttitle;
        TextView textno;
        ArrayList<Memo> memo = new ArrayList<>();
        public Holder(View view, final ArrayList<Memo> memo){
            this.memo=memo;
            texttitle=(TextView)findViewById(R.id.texttitle);
            textno=(TextView)findViewById(R.id.textno);
            texttitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra("memo", String.valueOf(memo.get(Integer.parseInt(textno.getText()+""))));
                    startActivity(intent);
                }
            });
        }
    }
}
