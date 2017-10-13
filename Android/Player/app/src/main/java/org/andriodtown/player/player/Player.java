package org.andriodtown.player.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import org.andriodtown.player.Const;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by pc on 10/12/2017.
 */

public class Player{
    //Singleton
    private static Player instance = new Player();
    private Player(){ }
    public static Player getInstance(){
        if(instance == null)
            instance = new Player();
        return instance;
    }
    // 멤버변수
    private MediaPlayer player = null;
    private boolean loop = false;
    private int status = Const.STAT_STOP;


    // 음원 세팅
    public void set(Context context, Uri musicUri){
        if(player != null) {
            player.release();
            player = null;
        }
        player = MediaPlayer.create(context, musicUri);
        player.setLooping(loop);
    }

    public void start(){
        if(player != null) {
            player.start();
            status = Const.STAT_PLAY;
        }
    }

    public void pause(){
        if(player != null) {
            player.pause();
            status = Const.STAT_PAUSE;
        }
    }

    public void stop(){
        if(player != null) {
            player.stop();
            player.release();
            player = null;
            status = Const.STAT_STOP;
        }
    }

    public int getStatus(){
        return status;
    }

    public boolean isPlay(){
        if(player != null)
            return player.isPlaying();
        return false;
    }

    public int getCurrent(){
        if(player != null)
            return player.getCurrentPosition();
        return 0;
    }

    public int getDuration(){
        if(player != null)
            return player.getDuration();
        return 0;
    }

    public Thread playerThread = new Thread(){
        public void run(){
            while(runFlag){
                for(IObserver o :observers)
                    o.setProgress();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private boolean runFlag = true;
    public void setStop(){
        runFlag = false;
    }
    // CopyOnWriteArrayList <= 동기화를 지원하는 컬렉션
    // run() 함수의 향상된 for문에서 observers를 읽고 있으면
    //       대기하고 있다가 읽기가 끝나면 add(), remove()를 실행해서
    //       충돌을 방지해준다.
    private List<IObserver> observers = new CopyOnWriteArrayList<>();

    public void add(IObserver observer){
        observers.add(observer);
    }
    public void remove(IObserver observer){
        observers.remove(observer);
    }
    // Observer
    public interface IObserver{
        public void setProgress();
    }
}
