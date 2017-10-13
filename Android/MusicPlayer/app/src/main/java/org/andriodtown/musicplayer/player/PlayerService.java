package org.andriodtown.musicplayer.player;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import org.andriodtown.musicplayer.model.Music;

public class PlayerService extends Service {
    PlayerActivity player = null;
    SeekBarThread thread = null;
    Music music = null;
    int current = -1;

    public PlayerService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
