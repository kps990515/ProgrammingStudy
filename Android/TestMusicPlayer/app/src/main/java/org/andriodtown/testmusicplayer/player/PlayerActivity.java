package org.andriodtown.testmusicplayer.player;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.andriodtown.testmusicplayer.BaseActivity;
import org.andriodtown.testmusicplayer.Constant;
import org.andriodtown.testmusicplayer.R;
import org.andriodtown.testmusicplayer.model.Music;

import java.util.List;

public class PlayerActivity extends BaseActivity implements View.OnClickListener{

    Music music;
    int current=-1;
    private ViewPager viewPager;
    private RelativeLayout controller;
    private SeekBar seekBar;
    private TextView txt_currentTime;
    private TextView txt_duration;
    private ImageButton btn_play;
    private ImageButton btn_ff;
    private ImageButton btn_bb;
    private ImageButton btn_next;
    private ImageButton btn_back;

    Intent serviceIntent;

    @Override
    public void init() {
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();
        if(intent!=null) {
            current = intent.getIntExtra(Constant.KEY_POSITION, 0);
        }
        serviceIntent = new Intent(this, playerService.class);
        load();
        initView();
        initViewPager();
    }

    private void load(){
        music = Music.getMusic();
        music.load(this);
    }

    private void initView(){
        setContentView(R.layout.activity_player);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        controller = (RelativeLayout) findViewById(R.id.controller);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        txt_currentTime = (TextView) findViewById(R.id.txt_currentTime);
        txt_duration = (TextView) findViewById(R.id.txt_duration);
        btn_play = (ImageButton) findViewById(R.id.btn_play);
        btn_ff = (ImageButton) findViewById(R.id.btn_ff);
        btn_bb = (ImageButton) findViewById(R.id.btn_bb);
        btn_next = (ImageButton) findViewById(R.id.btn_next);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
    }

    private void initViewPager(){
        PlayerPagerAdapter adapter = new PlayerPagerAdapter(this, music.musiclist);
        viewPager.setAdapter(adapter);
        // 뷰페이저에 리스너를 달기전에 페이지를 변경해서 onPageSelected가 호출되지 않는다
        if(current > 0)
            viewPager.setCurrentItem(current);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                current = position;
                playerSet();
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_play:
                if(Player.getPlayer().getStatus() == Constant.STAT_PLAY){
                    pause();
                }else{start();}
                break;
            case R.id.btn_ff:
                if(Player.getPlayer().getStatus() == Constant.STAT_PLAY){
                    ff();
                    if(Player.getPlayer().getCurrent()+10000>Player.getPlayer().getDuration()){
                        seekBar.setProgress(Player.getPlayer().getDuration());
                        txt_currentTime.setText(miliToSec(player.getDuration()));
                    }
                }
                break;
            case R.id.btn_bb:
                if(Player.getPlayer().getStatus() == Constant.STAT_PLAY){
                    bb();
                }
                break;
            case R.id.btn_next:
                break;
            case R.id.btn_back:
                break;

        }
    }

    private void playerSet(){
        serviceIntent.setAction(Constant.ACTION_SET);
        serviceIntent.putExtra(Constant.KEY_POSITION, current);
        startService(serviceIntent);
    }
    private void start(){
        serviceIntent.setAction(Constant.ACTION_START);
        startService(serviceIntent);
        togglePlayButton(Constant.STAT_PLAY);
    }
    private void pause(){
        serviceIntent.setAction(Constant.ACTION_PAUSE);
        startService(serviceIntent);
        togglePlayButton(Constant.STAT_PAUSE);
    }
    private void stop(){
        startService(serviceIntent);
    }

    private void ff(){

    }

    private void bb(){

    }

    private void togglePlayButton(int status){
        if(status == Constant.STAT_PLAY){
            btn_play.setImageResource(android.R.drawable.ic_media_pause);
        }else if(status == Constant.STAT_PAUSE){
            btn_play.setImageResource(android.R.drawable.ic_media_play);
        }
    }
}
