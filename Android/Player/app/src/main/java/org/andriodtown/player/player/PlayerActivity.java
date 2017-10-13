package org.andriodtown.player.player;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.andriodtown.player.BaseActivity;
import org.andriodtown.player.Const;
import org.andriodtown.player.PlayerPagerAdapter;
import org.andriodtown.player.PlayerService;
import org.andriodtown.player.R;
import org.andriodtown.player.domain.Music;

public class PlayerActivity extends BaseActivity
        implements View.OnClickListener, Player.IObserver{
    Music music;
    int current = -1;
    private ViewPager viewPager;
    private RelativeLayout controller;
    private SeekBar seekBar;
    private TextView textCurrentTime;
    private TextView textDuration;
    private ImageButton btnPlay;
    private ImageButton btnFf;
    private ImageButton btnRew;
    private ImageButton btnNext;
    private ImageButton btnPrev;

    Intent serviceIntent;

    @Override
    public void init() {
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();
        if(intent != null)
            current = intent.getIntExtra(Const.KEY_POSITION, 0);
        serviceIntent = new Intent(this, PlayerService.class);

        load();
        initView();
        initViewPager();
    }
    void load(){
        music = Music.getInstance();
        music.load(this);
        playerSet();
    }
    void initViewPager(){
        PlayerPagerAdapter adapter = new PlayerPagerAdapter(this,music.data);
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

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        controller = (RelativeLayout) findViewById(R.id.controller);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textCurrentTime = (TextView) findViewById(R.id.textCurrentTime);
        textDuration = (TextView) findViewById(R.id.textDuration);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnFf = (ImageButton) findViewById(R.id.btnFf);
        btnRew = (ImageButton) findViewById(R.id.btnRew);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);

        btnPlay.setOnClickListener(this);
        btnFf.setOnClickListener(this);
        btnRew.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPlay:
                if(Player.getInstance().getStatus() == Const.STAT_PLAY){
                    pause();
                }else {
                    start();
                }
                break;
            case R.id.btnFf:
                break;
            case R.id.btnRew:
                break;
            case R.id.btnNext:
                break;
            case R.id.btnPrev:
                break;
        }
    }
    void playerSet(){
        serviceIntent.setAction(Const.ACTION_SET);
        serviceIntent.putExtra(Const.KEY_POSITION, current);
        startService(serviceIntent);
    }
    void start(){
        serviceIntent.setAction(Const.ACTION_START);
        startService(serviceIntent);
        togglePlayButton(Const.STAT_PLAY);
    }
    void pause(){
        serviceIntent.setAction(Const.ACTION_PAUSE);
        startService(serviceIntent);
        togglePlayButton(Const.STAT_PAUSE);
    }
    void stop(){
        startService(serviceIntent);
    }
    void togglePlayButton(int status){
        if(status == Const.STAT_PLAY){
            btnPlay.setImageResource(android.R.drawable.ic_media_pause);
        }else if(status == Const.STAT_PAUSE){
            btnPlay.setImageResource(android.R.drawable.ic_media_play);
        }
    }

    Thread thread = null;
    @Override
    protected void onResume() {
        super.onResume();
        checkPlayer();
        seekBar.setMax(Player.getInstance().getDuration());
        thread = Player.getInstance().playerThread;
        Player.getInstance().add(this);
    }

    @Override
    protected void onPause() {
        Player.getInstance().remove(this);
        super.onPause();
    }

    void checkPlayer(){
        if(Player.getInstance().isPlay()) {
            // 플레이중에 앱을 실행하면 버튼과 viewPager를 상태에 맞게 갱신
            togglePlayButton(Const.STAT_PLAY);
        }
    }

    /**
     * 이 함수는 서브 thread에서 호출되기 때문에 Activity에 코드가 있지만
     * 실행은 서브에서 된다.
     */
    @Override
    public void setProgress() {
        // runOnUiThread : 코드를 main Thread에서 실행한다.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(Player.getInstance().getCurrent());
            }
        });
    }
}
