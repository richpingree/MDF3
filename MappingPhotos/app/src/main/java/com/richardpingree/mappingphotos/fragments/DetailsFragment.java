package com.richardpingree.mappingphotos.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richardpingree.mappingphotos.R;

/**
 * Created by Richard Pingree MDF3 1503 Week 4 on 3/25/15.
 */
public class DetailsFragment extends Fragment {

    private final String TAG = "DetailFragment";

    private DetailListener mListener;

    public interface DetailListener{
        public String getMarkerTitle();
    }

    public DetailsFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof DetailListener){
            mListener = (DetailListener)activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement DetaiListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView tv = (TextView)getView().findViewById(R.id.textView1);
        tv.setText(mListener.getMarkerTitle());

    }
}
