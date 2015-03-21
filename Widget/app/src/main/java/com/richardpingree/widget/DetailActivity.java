package com.richardpingree.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.richardpingree.widget.Fragments.DetailFragment;

/**
 * Created by Richard Pingree MDF3 1503 Week3 on 3/15/15.
 */
public class DetailActivity extends Activity implements DetailFragment.DetailListener {


    private final String TAG = "DetailActivity";

    private Person mPerson;

    public static final String PERSONEXTRA = "Person";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, new DetailFragment()).commit();
        }

        Intent detailIntent = getIntent();
        if(detailIntent != null){
            mPerson = (Person)detailIntent.getSerializableExtra(PERSONEXTRA);
        }
    }

    @Override
    public Person getPerson() {
        return mPerson;
    }
}
