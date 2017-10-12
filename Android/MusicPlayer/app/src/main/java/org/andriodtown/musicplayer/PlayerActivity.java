package org.andriodtown.musicplayer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.andriodtown.musicplayer.model.Music;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer player=null;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load();
        initControl();
        initView();
        initListener();
        initViewPager();
        start();
    }
    private void load(){
        music = Music.getInstance();
        Intent intent = getIntent();
        current = intent.getIntExtra(Constant.KEY_POSITION_NAME, -1 );
    }

    private void initControl(){
        // 볼륨 조절버튼으로 미디어 음량만 조절하기 위한 설정
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setPlayer();
    }

    SeekBarThread seekBarThread = null;

    private void setPlayer(){
        // postion에 해당하는 현재 아이템 꺼내기
        Music.Item item = music.data.get(current);
        Uri musicUri = item.musicUri;
        if(seekBarThread!=null){
            seekBarThread.setStop();
        }
        if(player != null){
            player.release(); //player = 1444번지라 할때 release를 쭉 해준다
            player=null; // player = 0번지로 초기화
        }
        // 음악uri 사용해서 플레이어 초기화
        player = MediaPlayer.create(this, musicUri);
        player.setLooping(false);

        // 화면세팅
        String duration = miliToSec(player.getDuration());
        txt_duration.setText(duration);
        txt_currentTime.setText("0:00");

        seekBar.setMax(player.getDuration());

        seekBarThread = new SeekBarThread(handler);
        seekBarThread.start();
    }

    private String miliToSec(int mili){
        int sec = mili / 1000;
        int min = sec / 60;
        sec = sec % 60;

        return String.format("%01d", min) + ":" + String.format("%02d",sec);
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

    private void initListener(){
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_ff).setOnClickListener(this);
        findViewById(R.id.btn_bb).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
    }
    private void initViewPager(){
        PlayerPagerAdapter adapter = new PlayerPagerAdapter(this,music.data);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current = position;
                setPlayer();
                if(playButtonStat==Constant.STAT_PLAY){
                    start();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if(current>-1){
            viewPager.setCurrentItem(current);
        }
    }

    private void start(){
        playButtonStat = Constant.STAT_PLAY;
        player.start();
        btn_play.setImageResource(android.R.drawable.ic_media_pause);
    }

    private void pause(){
        playButtonStat = Constant.STAT_PAUSE;
        player.pause();
        btn_play.setImageResource(android.R.drawable.ic_media_play);
    }

    @Override
    protected void onDestroy() {
        if(seekBarThread!=null){
            seekBarThread.setStop();
        }

        if(player!=null){
            player.release();
        }

        super.onDestroy();
    }

    int playButtonStat = Constant.STAT_PLAY;

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_play:
                if(playButtonStat == Constant.STAT_PLAY){
                    pause();
                }else{start();}
                break;
            case R.id.btn_ff:
                break;
            case R.id.btn_bb:
                break;
            case R.id.btn_next:
                break;
            case R.id.btn_back:
                break;

        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.WHAT_SET:
                    if(player!=null && player.isPlaying()){
                        int cp = player.getCurrentPosition();
                        seekBar.setProgress(cp);
                        txt_currentTime.setText(miliToSec(cp));
                    }
                    break;
            }
        }
    };
}

class SeekBarThread extends Thread{
    private boolean runFlag = true;
    private Handler handler;
    SeekBarThread(Handler handler){
        this.handler = handler;
    }
    public void setStop(){
        runFlag = false;
    }
    public void run(){
        while(runFlag) {
                handler.sendEmptyMessage(Constant.WHAT_SET);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
