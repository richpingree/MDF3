package com.richardpingree.mywidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.richardpingree.mywidget.fragments.DetailFragment;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class DetailActivity extends Activity implements DetailFragment.DetailListener {

    public static final String EXTRA_ITEM = "com.richardpingree.android.DetailActivity.EXTRA_ITEM";

    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mContact = (Contact)intent.getSerializableExtra(EXTRA_ITEM);
        if (mContact == null){
            finish();
            return;
        }

        getFragmentManager().beginTransaction().add(R.id.container, new DetailFragment()).commit();
    }

    @Override
    public Contact getContact() {
        return mContact;
    }
}
