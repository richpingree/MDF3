package com.richardpingree.mediaplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Richard Pingree MDF3 1504 Week 1 on 3/31/15.
 */
public class MainActivity extends Activity implements ServiceConnection, MainFragment.OnButtonClickListener, InfoFragment.InfoListener{

    public static final String TAG = "MainActivity.TAG";


    boolean mBound;
    MyService mySevice;
    MyService.BoundServiceBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();




    }

    @Override
    public String currentSongTitle() {
        String sTitle = mySevice.songNames[mySevice.mAudioPosition];
        return sTitle;
    }

    public void setSongInfo(){
        getFragmentManager().beginTransaction().replace(R.id.container1, new InfoFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent playIntent = new Intent(this, MyService.class);
        bindService(playIntent, this, Context.BIND_AUTO_CREATE);
        startService(playIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder =(MyService.BoundServiceBinder)service;
        mySevice = binder.getService();
        mBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        unbindService(MainActivity.this);
        mBound = false;
    }

    @Override
    public void clickStop() {
        mySevice.onStop();
    }

    @Override
    public void clickPause() {
        if(mySevice.player.isPlaying()){
            mySevice.onPause();
        }else{
            mySevice.onResume();
        }
    }

    @Override
    public void clickPlay() {
        mySevice.play();
        setSongInfo();

    }

    @Override
    public void clickPrev() {
        mySevice.onPrev();
        setSongInfo();
    }

    @Override
    public void clickNext() {
        mySevice.onNext();
        setSongInfo();
    }


}
