package org.andriodtown.tablayoutview;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTabLayout();
        setViewPager();
        // TabLayout을 ViewPager에 연결
        setListener();

    }

    private void setListener(){
        // tabLayout을 누르면 viewPager 자동변경
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        // viewPager를 바꾸면 tabLayout 자동변경
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setTabLayout(){
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("One"));
        tabLayout.addTab(tabLayout.newTab().setText("Two"));
        tabLayout.addTab(tabLayout.newTab().setText("Three"));
        tabLayout.addTab(tabLayout.newTab().setText("Four"));
    }

    private void setViewPager(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        /*
        List<Fragment> data = new ArrayList<>();
        data.add(new OneFragment());
        data.add(new TwoFragment());
        data.add(new ThreeFragment());
        data.add(new FourFragment());
        */
        CustomAdapter customAdapter = new CustomAdapter(this);
        viewPager.setAdapter(customAdapter);
    }
}
