package com.richardpingree.mymapapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/29/15.
 */
public class DetailActivity extends Activity {

    public static final String TITLE_EXTRA = "Title";
    public static final String NOTE_EXTRA = "Note";
    public static final String LATLONG_EXTRA = "LatLong";

    String detailTitle, detailNote, detailLatLong;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activtiy_details);
    }
}
