package com.richardpingree.mymapapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.richardpingree.mymapapp.R;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/29/15.
 */
public class FormFragment extends Fragment {

    private static final int REQUEST_TAKE_PICTURE = 0;
    public EditText title, notes;
    public ImageView image;
    public TextView latitude, longitude;
    public Uri imageUri;

    private FormListener mListener;

    public interface FormListener{
        public Double getLat();
        public Double getLong();

    }

    public FormFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof FormListener){
            mListener = (FormListener) activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement FormListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        title = (EditText)getView().findViewById(R.id.title);
        notes = (EditText)getView().findViewById(R.id.notes);
        image = (ImageView)getView().findViewById(R.id.image);
        latitude = (TextView)getView().findViewById(R.id.latitude);
        longitude = (TextView)getView().findViewById(R.id.longitude);


        latitude.setText("Latitude: " + String.valueOf(mListener.getLat()));
        longitude.setText("Longitude: " + String.valueOf(mListener.getLong()));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                Log.i("Form", "Save Button");
                break;
            case R.id.action_camera:
                Log.i("Form", "Camera Button");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
