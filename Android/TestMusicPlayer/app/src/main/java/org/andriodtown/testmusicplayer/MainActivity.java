package org.andriodtown.testmusicplayer;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import org.andriodtown.testmusicplayer.model.Music;
import org.andriodtown.testmusicplayer.player.PlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MainListFragment.OnListFragmentInteractionListener{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Music music = null;


    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        load();
        initView();
        initTabs();
        initViewPager();
        initListener();
    }

    private void load(){
        music = Music.getMusic();
        music.load(this);

    }

    private void initView(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
    }

    private void initTabs(){
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_title)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_artist)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_album)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_genre)));
    }

    private void initViewPager(){
        List<Fragment> fragList = new ArrayList<>();
        MainListFragment fragTitle =  MainListFragment.newInstance(1);
        MainListFragment fragArtist =  MainListFragment.newInstance(1);
        MainListFragment fragAlbum =  MainListFragment.newInstance(1);
        MainListFragment fragGenre =  MainListFragment.newInstance(1);
        fragList.add(fragTitle); fragList.add(fragArtist); fragList.add(fragAlbum); fragList.add(fragGenre);

        ListPagerAdapter listPagerAdapter = new ListPagerAdapter(getSupportFragmentManager(),fragList);
        viewPager.setAdapter(listPagerAdapter);
    }

    private void initListener(){
        // tabLayout과 viewPager연결
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public List<Music.Item> getList() {
        return music.musiclist;
    }

    @Override
    public void openPlayer(int position) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(Constant.KEY_POSITION,position);
    }
}
