package com.richardpingree.mywidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.mywidget.fragments.MainFragment;

import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class MainActivity extends Activity implements MainFragment.ContactListener{

    private static final String TAG = "MainActivity";

    private static final int ADD_REQUEST = 1;
    public static final String ADD_CONTACT_EXTRA_FIRST = "First Name";
    public static final String ADD_CONTACT_EXTRA_LAST = "Last Name";
    public static final String ADD_CONTACT_EXTRA_EMAIL = "Email Address";

    public Contact newContact;
    public ArrayList<Contact> mContactDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();

        mContactDataList = new ArrayList<Contact>();

//        mContactDataList.add(new Contact("Richard", "Pingree", "richardpingree@email.com"));
//        mContactDataList.add(new Contact("John", "Doe", "johndoe@email.com"));
//        mContactDataList.add(new Contact("Steve", "Jobs", "stevejobs@email.com"));

        ContactUtility.loadFile(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == ADD_REQUEST){
            newContact = new Contact();

            newContact.mFirst = data.getStringExtra(ADD_CONTACT_EXTRA_FIRST);
            newContact.mLast = data.getStringExtra(ADD_CONTACT_EXTRA_LAST);
            newContact.mEmail = data.getStringExtra(ADD_CONTACT_EXTRA_EMAIL);

            //ContactUtility.saveFile(this, newContact);

            mContactDataList.add(newContact);

            Log.i(TAG, newContact.toString());



            MainFragment mf = (MainFragment)getFragmentManager().findFragmentById(R.id.container);
            try{
                mf.updateList();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void addContact(){
        Intent addIntent = new Intent(this, FormActivity.class);
        startActivityForResult(addIntent, ADD_REQUEST);
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
            addContact();
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
