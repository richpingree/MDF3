package com.richardpingree.mymapapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.mymapapp.fragments.MyMapFragment;

/**
 * Created by Richard Pingree MDF3 1504 Week 4 on 4/27/15.
 */
public class MainActivity extends Activity {

    private static final int ADD_BTN_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyMapFragment frag = new MyMapFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
    }

    public void addBtnMethod(){
        Intent addBtnIntent = new Intent(this, FormActivity.class);
        startActivityForResult(addBtnIntent, ADD_BTN_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addBtnMethod();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
