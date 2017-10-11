package org.andriodtown.musicplayer;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import org.andriodtown.musicplayer.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements MusicFragment.OnListFragmentInteractionListener{
    Music music = null;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        load();
        initView();
        initViewPager();
        initTabs();
        initListener();
    }

    private void load(){
        music = Music.getInstance();
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
        List<Fragment> list = new ArrayList<>();
        MusicFragment fragTitle = MusicFragment.newInstance(1);
        MusicFragment fragArtist = MusicFragment.newInstance(1);
        MusicFragment fragAlbum = MusicFragment.newInstance(1);
        MusicFragment fragGenre = MusicFragment.newInstance(1);
        list.add(fragTitle); list.add(fragArtist); list.add(fragAlbum); list.add(fragGenre);

        ListPagerAdapter listPagerAdapter = new ListPagerAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(listPagerAdapter);

    }
    private void initListener(){
        // tabLayout과 viewPager연결
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public List<Music.Item> getList() {
        return music.data;
    }

    @Override
    public void openPlayer(int position) {
        Intent intent = new Intent(this,PlayerActivity.class);
        intent.putExtra(Constant.KEY_POSITION_NAME,position);
        startActivity(intent);
    }
}
