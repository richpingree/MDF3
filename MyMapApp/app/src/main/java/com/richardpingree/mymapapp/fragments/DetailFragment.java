package com.richardpingree.mymapapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richardpingree.mymapapp.R;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/29/15.
 */
public class DetailFragment extends Fragment {

    TextView titleView, noteView, latLongView;
    private DetailListener mListener;

    public interface DetailListener{
        public String getMarkerTitle();
        public String getMarkerNote();
        public String getMarkerLatLong();

    }

    public DetailFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof DetailListener){
            mListener = (DetailListener)activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement DetailListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        titleView = (TextView)rootView.findViewById(R.id.titleTxt);
        noteView = (TextView)rootView.findViewById(R.id.noteTxt);
        latLongView = (TextView)rootView.findViewById(R.id.latLongTxt);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        titleView.setText(mListener.getMarkerTitle());
        noteView.setText(mListener.getMarkerNote());
        latLongView.setText(mListener.getMarkerLatLong());

    }
}
