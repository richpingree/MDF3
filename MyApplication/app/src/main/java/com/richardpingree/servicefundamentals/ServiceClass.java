package com.richardpingree.servicefundamentals;

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
import android.support.v4.app.NotificationCompat;

import java.io.IOException;

/**
 * Created by Richard Pingree MDF3 1503 on 3/7/15.
 */
public class ServiceClass extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{

    MediaPlayer player;
    boolean mActivityResumed;
    boolean mPrepared;
    int mAudioPosition;
    String[] stringArray = new String[]{"/raw/bury_the_hatchet", "/raw/crescendo", "/raw/east_is_west", "/raw/crescendo_sting", "/raw/feel_the_vibe", "/raw/freed", "/raw/i_gotta_be_strong"};
    String[] songNames = new String[]{"Bury the Hatchet", "Crescendo", "East is West", "Crescendo Sting", "Feel the Vibe", "Freed", "I gotta be Strong"};

    private static final int STANDARD_NOTIFICATION = 0x01001;

    @Override
    public void onCreate() {
        super.onCreate();
        mAudioPosition = 0;
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnCompletionListener(this);
        player.setOnPreparedListener(this);
        player.reset();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

//    protected void onHandelIntent(Intent intent){
//        ResultReceiver receiver = intent.getParcelableExtra(MainActivity.EXTRA_RECEIVER);
//        Bundle result = new Bundle();
//        result.putString(MainActivity.DATA_RETURNED, "newSong");
//        //receiver.send(MainActivity.RESULT_DATA_RETURNED, result);
//    }

    public class BoundServiceBinder extends Binder{
        public ServiceClass getService(){
            return ServiceClass.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new BoundServiceBinder();
    }

    protected void play(){
        player.reset();
        mAudioPosition = 0;
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(ServiceClass.this);
        player.setOnCompletionListener(ServiceClass.this);

        try{
            player.setDataSource(ServiceClass.this, Uri.parse("android.resource://" + getPackageName() + stringArray[mAudioPosition]));
            player.prepareAsync();
            //Intent intent = new Intent("newSong");
            //onHandelIntent(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPause(){
        player.pause();
    }

    public void onResume(){
        mActivityResumed = true;
        if(player != null && mPrepared){
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

    @Override
    public void onDestroy() {
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

        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.art1);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.art2);
        Bitmap image3 = BitmapFactory.decodeResource(getResources(), R.drawable.art3);
        Bitmap image4 = BitmapFactory.decodeResource(getResources(), R.drawable.art4);
        Bitmap image5 = BitmapFactory.decodeResource(getResources(), R.drawable.art5);
        Bitmap image6 = BitmapFactory.decodeResource(getResources(), R.drawable.art6);
        Bitmap image7 = BitmapFactory.decodeResource(getResources(), R.drawable.art7);
        Bitmap[] bitImages = {image1, image2, image3, image4, image5, image6, image7};

        String currentSong = songNames[mAudioPosition];
        Bitmap currentImage = bitImages[mAudioPosition];
        Intent notifIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(currentImage);
        builder.setOngoing(true);
        builder.setContentTitle("Current Song");
        builder.setContentText(currentSong);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(currentImage));


        Notification notification = builder.build();

        startForeground(STANDARD_NOTIFICATION, notification);

    }

    protected void onPrev(){
        if(mAudioPosition >= 1){
            mAudioPosition--;
            player.reset();
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(ServiceClass.this);
            player.setOnCompletionListener(ServiceClass.this);

            try{
                player.setDataSource(ServiceClass.this, Uri.parse("android.resource://" + getPackageName() + stringArray[mAudioPosition]));

            } catch (IOException e) {
                e.printStackTrace();
            }
            player.prepareAsync();
        }else{
            player.reset();
            mAudioPosition = 0;
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(ServiceClass.this);
            player.setOnCompletionListener(ServiceClass.this);

            try{
                player.setDataSource(ServiceClass.this, Uri.parse("android.resource://" + getPackageName() + stringArray[mAudioPosition]));

            } catch (IOException e) {
                e.printStackTrace();
            }
            player.prepareAsync();
        }
    }

    protected void onNext(){
        if(mAudioPosition < stringArray.length - 1){
            mAudioPosition++;
            player.reset();
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(ServiceClass.this);
            player.setOnCompletionListener(ServiceClass.this);

            try{
                player.setDataSource(ServiceClass.this, Uri.parse("android.resource://" + getPackageName() + stringArray[mAudioPosition]));

            } catch (IOException e) {
                e.printStackTrace();
            }
            player.prepareAsync();
        }else{
            player.reset();
            mAudioPosition = 0;
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(ServiceClass.this);
            player.setOnCompletionListener(ServiceClass.this);

            try{
                player.setDataSource(ServiceClass.this, Uri.parse("android.resource://" + getPackageName() + stringArray[mAudioPosition]));

            } catch (IOException e) {
                e.printStackTrace();
            }
            player.prepareAsync();
        }
    }
    @Override
    public void onCompletion(MediaPlayer mp) {
        if(mAudioPosition < stringArray.length -1){
            mAudioPosition++;
            player.reset();
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(ServiceClass.this);
            player.setOnCompletionListener(ServiceClass.this);
            try{
                player.setDataSource(ServiceClass.this, Uri.parse("android.resource://" + getPackageName() + stringArray[mAudioPosition]));
                //Intent intent = new Intent("newSong");
                //onHandelIntent(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
            player.prepareAsync();
        }else{
            mAudioPosition = 0;
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(ServiceClass.this);
            player.setOnCompletionListener(ServiceClass.this);

            try{
                player.setDataSource(ServiceClass.this, Uri.parse("android.resource://" + getPackageName() + stringArray[mAudioPosition]));
                Intent intent = new Intent("newSong");
                //onHandelIntent(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
            player.prepareAsync();
        }

    }










}
