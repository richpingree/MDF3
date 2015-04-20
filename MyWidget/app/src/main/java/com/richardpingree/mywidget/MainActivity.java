package com.richardpingree.mywidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.mywidget.fragments.MainFragment;

import java.util.ArrayList;


public class MainActivity extends Activity implements MainFragment.ContactListener{

    public Contact newContact;
    public ArrayList<Contact> mContactDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();

        mContactDataList = new ArrayList<Contact>();

        mContactDataList.add(new Contact("Richard", "Pingree", "richardpingree@email.com"));
        mContactDataList.add(new Contact("John", "Doe", "johndoe@email.com"));
        mContactDataList.add(new Contact("Steve", "Jobs", "stevejobs@email.com"));
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ArrayList<Contact> getContacts() {
        return mContactDataList;
    }

    @Override
    public void viewContact(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(DetailActivity.EXTRA_ITEM, mContactDataList.get(position));
        startActivity(detailIntent);
    }
}
