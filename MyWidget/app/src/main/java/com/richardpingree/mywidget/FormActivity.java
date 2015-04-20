package com.richardpingree.mywidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.mywidget.fragments.FormFragment;



/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class FormActivity extends Activity implements FormFragment.FormListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getFragmentManager().beginTransaction().add(R.id.container, new FormFragment()).commit();
    }

    @Override
    public void addContact(Contact newContact) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.ADD_CONTACT_EXTRA_FIRST, newContact.mFirst);
        returnIntent.putExtra(MainActivity.ADD_CONTACT_EXTRA_LAST, newContact.mLast);
        returnIntent.putExtra(MainActivity.ADD_CONTACT_EXTRA_EMAIL, newContact.mEmail);
        setResult(RESULT_OK, returnIntent);
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
