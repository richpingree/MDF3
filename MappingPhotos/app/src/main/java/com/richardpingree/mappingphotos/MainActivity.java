package com.richardpingree.mappingphotos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.mappingphotos.fragments.MyMapFragment;

/**
 * Created by Richard Pingree MDF3 1503 Week 4 on 3/24/15.
 */
public class MainActivity extends Activity {

    private static final int ADDREQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyMapFragment frag = new MyMapFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
    }

    public void addMethod(){
        Intent addIntent = new Intent(this, FormActivity.class);
        startActivityForResult(addIntent, ADDREQUEST);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch(item.getItemId()){
           case R.id.action_add:
               addMethod();
               break;
           default:
               break;
       }

        return super.onOptionsItemSelected(item);
    }
}
