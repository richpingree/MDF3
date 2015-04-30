package com.richardpingree.mymapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.richardpingree.mymapapp.fragments.FormFragment;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/29/15.
 */
public class FormActivity extends Activity implements FormFragment.FormListener{

    public static final String GETLAT = "latitude";
    public static final String GETLONG = "longitude";

    Double mLat, mLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getFragmentManager().beginTransaction().replace(R.id.container, new FormFragment()).commit();

        Intent locIntent = getIntent();
        if(locIntent != null){
            mLat = locIntent.getDoubleExtra(GETLAT, 0.00);
            mLong = locIntent.getDoubleExtra(GETLONG, 0.00);
            Log.i("FormActivity", String.valueOf(mLat) + String.valueOf(mLong));
        }


    }


    @Override
    public Double getLat() {
        //mLat = getIntent().getExtras().getString("latitude");
        return mLat;
    }

    @Override
    public Double getLong() {
        //mLong = getIntent().getStringExtra("longitude");
        return mLong;

    }

    @Override
    public void addObject(CustomObject newObject) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.ADD_OBJECT_EXTRA_TITLE, newObject.mTitle);
        returnIntent.putExtra(MainActivity.ADD_OBJECT_EXTRA_NOTE, newObject.mNote);
        returnIntent.putExtra(MainActivity.ADD_OBJECT_EXTRA_LAT, newObject.mLatitude);
        returnIntent.putExtra(MainActivity.ADD_OBJECT_EXTRA_LONG, newObject.mLongitude);
        setResult(RESULT_OK, returnIntent);
        finish();

    }


}
