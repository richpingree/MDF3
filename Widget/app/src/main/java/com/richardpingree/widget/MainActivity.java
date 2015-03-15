package com.richardpingree.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.widget.Fragments.MainFragment;

import java.util.ArrayList;


public class MainActivity extends Activity implements MainFragment.PersonListener{

    private final String TAG = "MainActivity";

    private ArrayList<Person> mPeopleDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
        }

        mPeopleDataList = new ArrayList<Person>();
        mPeopleDataList.add(new Person("John", "Smith", "johnsmith@email.com"));
        mPeopleDataList.add(new Person("Rich", "Pingree", "richpingree@email.com"));

    }

    @Override
    public ArrayList<Person> getPeople(){
        return mPeopleDataList;
    }

    @Override
    public void viewPerson(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.PERSONEXTRA, mPeopleDataList.get(position));
        startActivity(detailIntent);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
