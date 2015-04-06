package com.richardpingree.mediaplayer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Richard Pingree MDF3 1504 Week 1 on 4/3/15.
 */
public class InfoFragment extends Fragment {

    public static final String TAG = "InfoFragment.TAG";

    TextView songTitle, artist;
    private InfoListener mListener;

    public interface InfoListener{
        public String currentSongTitle();
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

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        songTitle.setText(mListener.currentSongTitle());
        artist.setText("Matthew Corbett & Mike Wilkie");
    }
}
