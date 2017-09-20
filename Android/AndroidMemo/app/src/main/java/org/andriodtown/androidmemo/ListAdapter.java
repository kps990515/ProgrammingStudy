package org.andriodtown.androidmemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.andriodtown.androidmemo.domain.Memo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.andriodtown.androidmemo.R.id.textno;
import static org.andriodtown.androidmemo.R.id.texttitle;

/**
 * Created by user on 2017-09-20.
 */

public class ListAdapter extends BaseAdapter{
    ArrayList<Memo>data;
    Context context;

    public ListAdapter(Context context, ArrayList<Memo> data){
        this.data=data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Holder holder = null;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        }
        else{
            holder = (Holder)view.getTag();
        }
        //값을 세팅
        // 1. 컬렉션 구조의 저장소에 객체 단위로 꺼내두는게 편하다
        Memo memo = data.get(position);
        // 홀더의 위젯에 데이터를 입력
        holder.setTextno(memo.getNo());
        holder.setTexttitle(memo.getTitle());
        holder.setTextDate(memo.getDatetime());
        holder.setPosition(position);

        return view;
    }
}
class Holder{
    private int position;
    private String author;
    private String content;
    private TextView textNo;
    private TextView textTitle;
    private TextView textDate;

    public void setAuthor(String author){
        this.author = author;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setTextno(int no) {
        textNo.setText(no+"");
    }

    public void setTexttitle(String title) {
       textTitle.setText(title);
    }

    public void setTextDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:MM");
        String result = sdf.format(date);
        textDate.setText(result);
    }

    public void setPosition(int position){
        this.position = position;
    }

    public Holder(View view){
        textNo = (TextView) view.findViewById(textno);
        textTitle = (TextView) view.findViewById(texttitle);
        textDate = (TextView) view.findViewById(R.id.textdatetime);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("title", textTitle.getText());
                intent.putExtra("author", author);
                intent.putExtra("content", content);
                intent.putExtra("datetime", textDate.getText());
                v.getContext().startActivity(intent);
            }
        });
    }
}

