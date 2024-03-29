package com.richardpingree.mediaplayer;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Richard Pingree MDF3 1504 Week 1 on 3/31/15.
 */
public class MyService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{

    public static final String TAG = "MyService.TAG";

    public MediaPlayer player;
    boolean mActivityResumed;
    boolean mPrepared;
    boolean shuffle = false;
    int mAudioPosition = 0;
    String[] stringArray = new String[]{"/raw/bury_the_hatchet", "/raw/crescendo", "/raw/east_is_west", "/raw/feel_the_vibe", "/raw/freed", "/raw/i_gotta_be_strong"};
    String[] songNames = new String[]{"Bury the Hatchet", "Crescendo", "East is West", "Feel the Vibe", "Freed", "I Gotta be Strong"};
    int[] imageNames = new int[]{R.drawable.art1,R.drawable.art2,R.drawable.art3,R.drawable.art4,R.drawable.art5,R.drawable.art6};

    private static final int STANDARD_NOTIFICATION = 0x00001;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BoundServiceBinder mServiceBinder = new BoundServiceBinder();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new BoundServiceBinder();
    }

    public class BoundServiceBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    public int getMediaDuration(){
        return player.getDuration();
    }

    public int getMediaCurPos(){
        return player.getCurrentPosition();
    }

    //generates a random number
    public int randomTrack(){
        Random r = new Random();
        mAudioPosition = r.nextInt(stringArray.length - 0) + 0;
        return mAudioPosition;
    }



    public void playerSetup(){

        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(MyService.this);
        player.setOnCompletionListener(MyService.this);

        try{
            player.setDataSource(MyService.this, Uri.parse("android.resource://" + getPackageName() + stringArray[mAudioPosition]));
        }catch(IOException e){
            e.printStackTrace();
        }
        player.prepareAsync();

    }
    public void play(){
        player = new MediaPlayer();
        playerSetup();

    }

    public void onPause(){
        player.pause();
    }

    public void onResume(){
        mActivityResumed = true;

        if(player != null && !mPrepared){
            player.prepareAsync();
        }else if (player != null && mPrepared){
            player.start();
        }
    }

    public void onStop(){
        player.stop();
        mPrepared = false;
        player.release();
        stopForeground(true);
    }

    public void onDestroy(){
        super.onDestroy();
        if(player != null){
            player.release();
            stopForeground(true);
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mPrepared = true;
        player.start();

        String currentSong = songNames[mAudioPosition];
        Bitmap currentimage = BitmapFactory.decodeResource(getResources(), imageNames[mAudioPosition]);
        Intent nIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, nIntent, 0);
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(currentimage);
        builder.setOngoing(true);
        builder.setContentTitle("Current Song");
        builder.setContentText(currentSong);
        Notification notification = builder.build();

        startForeground(STANDARD_NOTIFICATION, notification);
    }



    public void onPrev(){
        if(shuffle == true){
            randomTrack();
            player.reset();
            playerSetup();
        }else if(shuffle == false && mAudioPosition >= 1){
            mAudioPosition--;
            player.reset();
            playerSetup();
        }
        //old code before shuffle was added
//        if(mAudioPosition >= 1){
//            mAudioPosition--;
//            player.reset();
//            playerSetup();
//
//        }
    }

    public void onNext(){
        if(shuffle == true){
            randomTrack();
            player.reset();
            playerSetup();
        }else if(shuffle == false && mAudioPosition < stringArray.length - 1){
            mAudioPosition++;
            player.reset();
            playerSetup();
        }
        //old code before shuffle was added
//        if(mAudioPosition < stringArray.length - 1){
//            mAudioPosition++;
//            player.reset();
//            playerSetup();
//
//        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (shuffle == true){
            randomTrack();
            player.reset();
            playerSetup();
        }else if(shuffle == false && mAudioPosition < stringArray.length - 1){
            mAudioPosition++;
            player.reset();
            playerSetup();
        }
        //old code before shuffle was added
//        if(mAudioPosition < stringArray.length - 1){
//            mAudioPosition++;
//            player.reset();
//            playerSetup();
//        }
    }



}
