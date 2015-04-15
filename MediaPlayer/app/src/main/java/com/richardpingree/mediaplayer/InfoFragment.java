package com.richardpingree.mediaplayer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Richard Pingree MDF3 1504 Week 1 on 4/3/15.
 */
public class InfoFragment extends Fragment {

    public static final String TAG = "InfoFragment.TAG";

    TextView songTitle, artist;
    SeekBar seekBar;
    ImageView albumImageView;
    private InfoListener mListener;
    int musicToTime, musicCurTime;
    public Handler durationHandler = new Handler();

    public interface InfoListener{
        public String currentSongTitle();
        public int currentAlbumImage();
        public int mediaDuration();
        public int mediaCurPos();
    }

    public InfoFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof InfoListener){
            mListener = (InfoListener)activity;
        }else{
            throw new IllegalArgumentException("containing activity must implement InfoListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        songTitle = (TextView)rootView.findViewById(R.id.songTxt);
        artist = (TextView)rootView.findViewById(R.id.artistTxt);
        seekBar = (SeekBar)rootView.findViewById(R.id.seekBar);
        albumImageView = (ImageView)rootView.findViewById(R.id.imageView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if(songTitle == null){
//            songTitle.setText(mListener.currentSongTitle());
//            artist.setText("Matthew Corbett & Mike Wilkie");
//        }
        albumImageView.setImageResource(mListener.currentAlbumImage());
        songTitle.setText(mListener.currentSongTitle());
        artist.setText("Matthew Corbett & Mike Wilkie");

        Runnable updateSeek = new Runnable() {
            @Override
            public void run() {

                musicToTime = mListener.mediaDuration();
                seekBar.setMax(musicToTime);
                musicCurTime = mListener.mediaCurPos();
                seekBar.setProgress(musicCurTime);
                durationHandler.postDelayed(this, 1000);
            }

        };
        durationHandler.postDelayed(updateSeek, 1000);

    }

//    Runnable updateSeek = new Runnable() {
//        @Override
//        public void run() {
//
//            musicToTime = mListener.mediaDuration();
//            seekBar.setMax(musicToTime);
//            musicCurTime = mListener.mediaCurPos();
//            seekBar.setProgress(musicCurTime);
//        }
//        //durationHandler.postDelayed(this, 1000);
//    };
}
