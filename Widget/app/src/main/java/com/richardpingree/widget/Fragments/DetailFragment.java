package com.richardpingree.widget.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.richardpingree.widget.Person;
import com.richardpingree.widget.R;

/**
 * Created by richardpingree on 3/15/15.
 */
public class DetailFragment extends Fragment {

    private final String TAG = "DetailFragment";

    private DetailListener mListener;

    public interface DetailListener{
        public Person getPerson();
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
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView tV = (TextView) getView().findViewById(R.id.detailFirst);
        tV.setText(mListener.getPerson().getFirst());
        tV =(TextView) getView().findViewById(R.id.detailLast);
        tV.setText(mListener.getPerson().getLast());
        tV = (TextView) getView().findViewById(R.id.detailEmail);
        tV.setText(mListener.getPerson().getEmail());
    }
}
