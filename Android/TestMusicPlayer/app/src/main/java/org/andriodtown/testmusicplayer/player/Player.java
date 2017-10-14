package org.andriodtown.testmusicplayer.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import org.andriodtown.testmusicplayer.Constant;
import org.andriodtown.testmusicplayer.model.Music;

import java.util.List;

/**
 * Created by user on 2017-10-16.
 */


public class Player {
    Music music;
    private static Player player = null;
    List<Music.Item> pMusicList = music.musiclist;
    private Player(){}

    public static Player getPlayer(){
        if(player==null){
            player = new Player();
        }
        return player;
    }

    private MediaPlayer mediaPlayer = null;
    private boolean loop = true;
    private int status = Constant.STAT_STOP;

    public void setMediaPlayer(Context context, Uri musicUri){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = MediaPlayer.create(context, musicUri);
        mediaPlayer.setLooping(loop);
    }

    public void start(){
        if(mediaPlayer != null) {
            mediaPlayer.start();
            status = Constant.STAT_PLAY;
        }
    }

    public void pause(){
        if(mediaPlayer != null) {
            mediaPlayer.pause();
            status = Constant.STAT_PAUSE;
        }
    }

    public void stop(){
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            status = Constant.STAT_STOP;
        }
    }

    private void ff(){
        if(mediaPlayer != null) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+10000);
            status = Constant.STAT_PLAY;
        }
        /*
        if(mediaPlayer.getCurrentPosition()+10000>player.getDuration()){
            seekBar.setProgress(player.getDuration());
            txt_currentTime.setText(miliToSec(player.getDuration()));
        }
        */
    }

    private void bb(){
        if(mediaPlayer != null) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 10000);
            status = Constant.STAT_PLAY;
        }
        /*
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-10000);
        if(mediaPlayer.getCurrentPosition()-10000<0){
            seekBar.setProgress(0);
            txt_currentTime.setText("0:00");
        }
        */
    }

    public int getStatus(){
        return status;
    }

    public boolean isPlay(){
        if(mediaPlayer != null)
            return mediaPlayer.isPlaying();
        return false;
    }

    public int getCurrent(){
        if(mediaPlayer != null)
            return mediaPlayer.getCurrentPosition();
        return 0;
    }

    public int getDuration(){
        if(mediaPlayer != null)
            return mediaPlayer.getDuration();
        return 0;
    }
}
