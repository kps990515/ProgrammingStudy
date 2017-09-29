package org.andriodtown.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
ViewPager 사용하기

 */

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        // 가상 데이터
        List<String>data = new ArrayList<>();
        for(int i=0; i<100; i++){
            data.add("Temp data = "+i);
        }
        // Adapter 생성
        CustomAdapter customAdapter = new CustomAdapter(this,data);
        // Adapter 연결
        viewPager.setAdapter(customAdapter);
    }
}

class CustomAdapter extends PagerAdapter{

    List<String> data;
    Context context;

    public CustomAdapter(Context context, List<String>data){
        this.context = context;
        this.data = data;
    }

    // 전체 개수
    @Override
    public int getCount() {
        return data.size();
    }

    // getView와 같은거
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 여기서 inflate할 Layout 파일이 필요
        // 1. Layout파일을 infalte해서 view로 만든다
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager,null);
        // 2. 데이터에서 value를 가져오고 textViewPager에 삽입
        String value = data.get(position);
        TextView textViewPager = (TextView) view.findViewById(R.id.txt_viewPager);
        textViewPager.setText(value);
        // 3. 뷰 그룹에 만들어진 view를 add한다
        container.addView(view);

        return view;
    }

    // instatiateItem에서 리턴된 Object가 View가 맞는지 체크
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    // 현재 사용하지 않는 View 제거
    // container : 뷰페이저
    // object : 뷰 아이템(뷰페이지 안에 있는)
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 컨테이너(뷰 3개 묶여있는거)중에 필요없는 view destroy
        container.removeView((View)object);
        // 이거를 주석처리하는 이유는 뷰 안에 다른 Bitmap같은 스트림이 열려있을 때 안닫혀서...
        // super.destroyItem(container, position, object);
    }
}