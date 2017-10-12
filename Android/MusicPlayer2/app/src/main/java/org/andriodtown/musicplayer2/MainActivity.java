package org.andriodtown.musicplayer2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import org.andriodtown.musicplayer2.domain.Music;
import org.andriodtown.musicplayer2.player.Player;
import org.andriodtown.musicplayer2.player.PlayerActivity;

import java.util.ArrayList;
import java.util.List;

/*
    뮤직플레이어 만들기
    1. 권한설정 : Read_External_Storage <- Main에 BaseActivity(권한요청)상속
    2. 화면 만들기 : 메인 -> TabLayout & ViewPager
                         -> TabLayout Fragment <- ListPager Adapter <- 메인에서 만든 MusicFragment담은 List
                         -> ViewPager Fragment <- Recycler View(music_list) <- Recycler Adapter(MymusicAdapter) <- fragment_music.xml
                    Music -> load() : 음악목록 가져오기
                    Player -> ViewPager, Button, SeekBar
                            -> ViewPager Fragment <- Player Pager Adapter <- item_player
 */

public class MainActivity extends BaseActivity implements ListFragment.IActivityInteract{
    private ViewPager viewPager;
    private TabLayout tablayout;

    Music music = null;

    @Override
    public void init() {
        load();
        initView();
        initTab();
        initViewPager();
        conTabWithViewPager();
        checkPlayer();
    }

    void load(){
        music = Music.getInstance();
        music.load(this);
    }

    void initView(){
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
    }
    void initTab(){
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.tab_title)));
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.tab_artist)));
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.tab_album)));
        tablayout.addTab(tablayout.newTab().setText(getString(R.string.tab_genre)));
    }
    void initViewPager(){
        Fragment one = new ListFragment();
        Fragment two = new ListFragment();
        Fragment three = new ListFragment();
        Fragment four = new ListFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(one); fragments.add(two); fragments.add(three); fragments.add(four);
        ListPagerAdapter adapter = new ListPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    void checkPlayer(){
        if(Player.getInstance().isPlay())
            openPlayer(-1);
    }

    void conTabWithViewPager(){
        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
    }

    @Override
    public List<Music.Item> getList() {
        return music.data;
    }

    @Override
    public void openPlayer(int position) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(Const.KEY_POSITION, position);
        startActivity(intent);
    }
}
