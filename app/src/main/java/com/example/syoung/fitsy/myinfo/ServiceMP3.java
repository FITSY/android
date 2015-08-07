package com.example.syoung.fitsy.myinfo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.syoung.fitsy.R;

/**
 * Created by 상훈 on 2015-08-06.
 */
public class ServiceMP3 extends Service {
    private MediaPlayer mPlayer = null;
    public IBinder onBind(Intent intent){
        return null;
    }
    public void onCreate(){
        mPlayer = MediaPlayer.create(this, R.raw.updown);
        mPlayer.setLooping(false);
    }
    public int onStartCommand(Intent intent, int flags, int startId){
        mPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }
    public void onDestroy(){
        mPlayer.stop();
    }
}
