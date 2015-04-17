package com.richardpingree.mediaplayer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by Richard Pingree MDF3 1504 Week 1 on 3/31/15.
 */
public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment.TAG";

    private OnButtonClickListener mListener;

    Button previous, stop, play, pause, next;
    Switch suffle;

    public interface OnButtonClickListener{
        public void clickStop();
        public void clickPause();
        public void clickPlay();
        public void clickPrev();
        public void clickNext();
        public void setShuffleTrue();
        public void setShuffleFalse();
    }

    public MainFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof OnButtonClickListener){
            mListener = (OnButtonClickListener) activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement OnButtonClickListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        previous = (Button)rootView.findViewById(R.id.prevBtn);
        stop = (Button)rootView.findViewById(R.id.stopBtn);
        play = (Button)rootView.findViewById(R.id.playBtn);
        pause = (Button)rootView.findViewById(R.id.pauseBtn);
        next = (Button)rootView.findViewById(R.id.nextBtn);
        suffle = (Switch)rootView.findViewById(R.id.switch1);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous.setEnabled(false);
                play.setEnabled(true);
                pause.setEnabled(false);
                next.setEnabled(false);
                stop.setEnabled(false);
                mListener.clickStop();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous.setEnabled(true);
                play.setEnabled(false);
                pause.setEnabled(true);
                stop.setEnabled(true);
                next.setEnabled(true);
                mListener.clickPlay();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause.setEnabled(true);
                play.setEnabled(false);
                mListener.clickPause();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickPrev();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clickNext();
            }
        });

        suffle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    Log.i(TAG, "switch is on");
                    mListener.setShuffleTrue();
                }else{
                    mListener.setShuffleFalse();
                    Log.i(TAG, "switch is off");
                }
            }
        });
    }
}
