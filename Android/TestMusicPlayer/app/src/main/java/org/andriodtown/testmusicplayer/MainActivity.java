package org.andriodtown.testmusicplayer;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.andriodtown.testmusicplayer.model.Music;

public class MainActivity extends BaseActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Music music = null;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        load();
    }

    private void load(){
        music = Music.getMusic();
        music.load(this);
    }
}
