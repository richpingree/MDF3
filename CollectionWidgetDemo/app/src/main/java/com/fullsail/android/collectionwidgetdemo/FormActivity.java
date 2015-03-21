package com.fullsail.android.collectionwidgetdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fragments.FormFragment;

/**
 * Created by Richard Pingree MDF3 1503 Week 3 on 3/20/15.
 */
public class FormActivity extends Activity implements FormFragment.FormListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getFragmentManager().beginTransaction().add(R.id.container, new FormFragment()).commit();
    }

    @Override
    public void addEntry(Person newPerson) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.ADD_PERSON_EXTRA_FIRST, newPerson.mFirst);
        returnIntent.putExtra(MainActivity.ADD_PERSON_EXTRA_LAST, newPerson.mLast);
        returnIntent.putExtra(MainActivity.ADD_PERSON_EXTRA_EMAIL, newPerson.mEmail);
        setResult(RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
