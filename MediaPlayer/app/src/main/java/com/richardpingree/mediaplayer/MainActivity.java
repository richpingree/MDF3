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
import android.widget.TextView;

/**
 * Created by Richard Pingree MDF3 1504 Week 1 on 3/31/15.
 */
public class MainActivity extends Activity implements ServiceConnection, MainFragment.OnButtonClickListener{

    public static final String TAG = "MainActivity.TAG";

    TextView songTitle, artist;
    String songTitleString;
    boolean mBound;
    MyService mySevice;
    MyService.BoundServiceBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();

        songTitle = (TextView) findViewById(R.id.songTxt);
        artist = (TextView) findViewById(R.id.artistTxt);
    }

    public void songTitle(){
        songTitle.setText(mySevice.songNames[mySevice.mAudioPosition]);
        artist.setText("Matthew Corbett & Mike Wilkie");
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
        songTitle();
    }

    @Override
    public void clickPrev() {
        mySevice.onPrev();
        songTitle();
    }

    @Override
    public void clickNext() {
        mySevice.onNext();
        songTitle();
    }
}
