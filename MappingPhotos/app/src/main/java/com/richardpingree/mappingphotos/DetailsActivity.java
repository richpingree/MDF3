package com.richardpingree.mappingphotos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.richardpingree.mappingphotos.fragments.DetailsFragment;


/**
 * Created by Richard Pingree MDF3 1503 Week 4 on 3/25/15.
 */
public class DetailsActivity extends Activity implements DetailsFragment.DetailListener {


    public static final String EXTRA = "Details Extra";
    String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        test = intent.getStringExtra(EXTRA);

        DetailsFragment frag = new DetailsFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
    }


    @Override
    public String getMarkerTitle() {
        return test;
    }
}
