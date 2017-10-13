package org.andriodtown.player;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.andriodtown.player.domain.Music;
import org.andriodtown.player.player.Player;

public class PlayerService extends Service {
    Player player = null;
    Thread thread = null;
    Music music = null;
    int current = -1;
    public PlayerService() {}

    @Override
    public void onCreate() {
        super.onCreate();
        player = Player.getInstance();
        thread = Player.getInstance().playerThread;
        thread.start();
        music = Music.getInstance();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            String action = intent.getAction();
            switch(action){
                case Const.ACTION_SET:
                    current = intent.getIntExtra(Const.KEY_POSITION,-1);
                    playerSet();
                    break;
                case Const.ACTION_START:
                    start();
                    break;
                case Const.ACTION_PAUSE:
                    pause();
                    break;
                case Const.ACTION_STOP:
                    stop();
                    break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    void playerSet(){
        if(current > -1)
            player.set(getBaseContext(), music.data.get(current).musicUri);
    }
    void start(){
        player.start();
    }
    void pause(){
        player.pause();
    }
    void stop(){
        player.stop();
    }

    @Override
    public void onDestroy() {
        if(player != null)
            player = null;
        if(thread != null) {
            Player.getInstance().setStop();
            thread = null;
        }
        super.onDestroy();
    }

}
