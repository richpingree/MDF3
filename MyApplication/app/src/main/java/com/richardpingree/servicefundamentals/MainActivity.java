package com.richardpingree.servicefundamentals;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ResultReceiver;



/**
 * Created by Richard Pingree MDF3 1503 on 3/7/15.
 */
public class MainActivity extends Activity implements ServiceConnection, mainFragment.OnButtonClickListener{

    public static final String TAG = "MainActivity.TAG";

    //Button previous, stop, play, pause, next;
    String songTitleString;
    ServiceClass myService;
    ServiceClass.BoundServiceBinder binder;
    public static final String EXTRA_RECEIVER = "EXTRA_RECEIVER";
    public static final String DATA_RETURNED = "DATA_RETURNED";
    public static final int RESULT_DATA_RETURNED = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new mainFragment()).commit();
        }

//        previous = (Button) findViewById(R.id.prevBtn);
//        stop = (Button) findViewById(R.id.stopBtn);
//        play = (Button) findViewById(R.id.playBtn);
//        pause = (Button) findViewById(R.id.pauseBtn);
//        next = (Button) findViewById(R.id.nextBtn);
//        songTitle = (TextView) findViewById(R.id.songTxt);

        Intent intent = new Intent(this, ServiceClass.class);
        intent.putExtra(EXTRA_RECEIVER, new DataReceiver());

    }

    private final Handler handler = new Handler();

    public class DataReceiver extends ResultReceiver{
        public DataReceiver(){
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultData != null && resultData.containsKey(DATA_RETURNED)){
                //songTitle.setText(resultData.getString(DATA_RETURNED, "newSong"));
            }
        }
    }

    public void songTitle(){
        //songTitle.setText(myService.songNames[myService.mAudioPosition]);
    }

    public String getSongTitle(String titleString){
        songTitleString = myService.songNames[myService.mAudioPosition];
        titleString = songTitleString;
        return titleString;
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent playIntent = new Intent(this, ServiceClass.class);
        bindService(playIntent, this, Context.BIND_AUTO_CREATE);
        startService(playIntent);


//        stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myService.onStop();
//            }
//        });
//
//        play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myService.play();
//                songTitle();
//            }
//        });
//
//        pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(myService.player.isPlaying()){
//                    myService.onPause();
//                }else{
//                    myService.onResume();
//                }
//
//            }
//        });
//
//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myService.onPrev();
//                songTitle();
//            }
//        });
//
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myService.onNext();
//                songTitle();
//            }
//        });
    }
    public void clickStop(){
        myService.onStop();
    }

    public void clickPause(){
        if(myService.player.isPlaying()){
            myService.onPause();
        }else{
            myService.onResume();
        }
    }

    public void clickPlay(){
        myService.play();
        //songTitle();
    }

    public void clickPrev(){
        myService.onPrev();
        //songTitle();
    }

    public void clickNext(){
        myService.onNext();
        //songTitle();
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (ServiceClass.BoundServiceBinder)service;
        myService = binder.getService();

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        unbindService(MainActivity.this);

    }
}
