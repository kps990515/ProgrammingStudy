package org.andriodtown.tablayoutview;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */

public class Four extends FrameLayout {


    public Four(Context context) {
        super(context);
        initView();
        // Required empty public constructor
    }

    public Four(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
        initView();
    }

    private void initView(){
        // 1. 레이아웃 파일로 뷰를 만들고
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_four, null);

        // 로직작성
        process();
        addView(view);
    }
    private void process(){
    }

}
