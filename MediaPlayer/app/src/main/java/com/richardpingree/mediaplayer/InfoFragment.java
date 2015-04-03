package com.richardpingree.mediaplayer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Richard Pingree MDF3 1504 Week 1 on 4/3/15.
 */
public class InfoFragment extends Fragment {

    public static final String TAG = "InfoFragment.TAG";

    private InfoListener mListener;

    public interface InfoListener{

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
    }
}
