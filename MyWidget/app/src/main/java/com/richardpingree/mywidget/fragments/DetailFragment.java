package com.richardpingree.mywidget.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richardpingree.mywidget.Contact;
import com.richardpingree.mywidget.R;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class DetailFragment extends Fragment {

    private static final String TAG ="DetailFragment";

    private DetailListener mListener;

    public interface DetailListener{
        public Contact getContact();
    }

    public DetailFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof DetailListener){
            mListener = (DetailListener)activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement DetailListener interfacae");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView tv = (TextView)getView().findViewById(R.id.firstTxt);
        tv.setText(mListener.getContact().getFirst());

        tv = (TextView)getView().findViewById(R.id.lastTxt);
        tv.setText(mListener.getContact().getLast());

        tv = (TextView)getView().findViewById(R.id.emailTxt);
        tv.setText(mListener.getContact().getEmail());
    }
}
