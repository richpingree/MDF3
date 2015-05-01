package com.richardpingree.mymapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.richardpingree.mymapapp.fragments.DetailFragment;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/29/15.
 */
public class DetailActivity extends Activity implements DetailFragment.DetailListener {

    public static final String TITLE_EXTRA = "Title";
    public static final String NOTE_EXTRA = "Note";
    public static final String LATLONG_EXTRA = "LatLong";
    //public static final String EXTRA_OBJECT = "com.richardpingree.android.DetailActivity.EXTRA_ITEM";

    String detailTitle, detailNote, detailLatLong;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_details);

        Intent detailIntent = getIntent();
        detailTitle = detailIntent.getStringExtra(TITLE_EXTRA);
        detailNote = detailIntent.getStringExtra(NOTE_EXTRA);
        detailLatLong = detailIntent.getStringExtra(LATLONG_EXTRA);

        Log.i("Details", detailTitle + " " + detailNote + " " + detailLatLong);


        DetailFragment frag = new DetailFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
    }

    @Override
    public String getMarkerTitle() {
        return detailTitle;
    }

    @Override
    public String getMarkerNote() {
        return detailNote;
    }

    @Override
    public String getMarkerLatLong() {
        return detailLatLong;
    }
}
