package com.richardpingree.servicefundamentals;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Richard Pingree MDF3 1503 on 3/10/15.
 */
public class mainFragment extends Fragment {

    public static final String TAG = "mainFragment";

    private OnButtonClickListener mListener;

    Button previous, stop, play, pause, next;
    TextView songTitle;

    public interface OnButtonClickListener{
        //public void songTitle();
        public void clickStop();
        public void clickPause();
        public void clickPlay();
        public void clickPrev();
        public void clickNext();
        public String getSongTitle(String titleString);
    }

    public mainFragment(){

    }

    public void songTitle(){
        //songTitle.setText(myService.songNames[myService.mAudioPosition]);
        //songTitle.setText();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof OnButtonClickListener){
            mListener = (OnButtonClickListener) activity;
        } else{
            throw new IllegalArgumentException("Containing activity must implement OnButtonClickListener interface");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        previous = (Button) rootView.findViewById(R.id.prevBtn);
        stop = (Button) rootView.findViewById(R.id.stopBtn);
        play = (Button) rootView.findViewById(R.id.playBtn);
        pause = (Button) rootView.findViewById(R.id.pauseBtn);
        next = (Button) rootView.findViewById(R.id.nextBtn);
        songTitle = (TextView) rootView.findViewById(R.id.songTxt);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickStop();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickPlay();
                //mListener.songTitle();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickPause();

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickPrev();
                //mListener.songTitle();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickNext();
                //mListener.songTitle();
            }
        });
    }
}
