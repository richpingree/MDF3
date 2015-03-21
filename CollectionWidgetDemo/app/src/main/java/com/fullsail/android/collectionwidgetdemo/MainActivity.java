package com.fullsail.android.collectionwidgetdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import fragments.MainFragment;

/**
 * Created by Richard Pingree MDF3 1503 Week 3 on 3/21/15.
 */
public class MainActivity extends Activity implements MainFragment.PersonListener {

    private final static String TAG = "MainActivity";

    private static final int ADD_REQUEST = 1;
    public static String ADD_PERSON_EXTRA_FIRST = "First Name";
    public static String ADD_PERSON_EXTRA_LAST = "Last Name";
    public static String ADD_PERSON_EXTRA_EMAIL = "Email Address";

    public Person newPerson;
    public ArrayList<Person> mPersonDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();

        mPersonDataList = new ArrayList<Person>();

        mPersonDataList.add(new Person("John", "Smith", "johnsmith@email.com"));
        mPersonDataList.add(new Person("Rich", "Pingree", "richpingree@email.com"));

        Intent toWidget = new Intent(this, CollectionWidgetViewFactory.class);
        toWidget.putExtra("personList", getPersons());
        sendBroadcast(toWidget);

        PersonUtility.saveFile(this, newPerson);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == ADD_REQUEST){
            newPerson = new Person();

            newPerson.mFirst = data.getStringExtra(ADD_PERSON_EXTRA_FIRST);
            newPerson.mLast = data.getStringExtra(ADD_PERSON_EXTRA_LAST);
            newPerson.mEmail = data.getStringExtra(ADD_PERSON_EXTRA_EMAIL);

            mPersonDataList.add(newPerson);

            MainFragment mf = (MainFragment)getFragmentManager().findFragmentById(R.id.container);
            try{
                mf.updateList();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Person> getPersons() {
        return mPersonDataList;
    }


    @Override
    public void viewPerson(int position) {
        Intent detailIntent = new Intent(this, DetailsActivity.class);
        detailIntent.putExtra(DetailsActivity.EXTRA_ITEM, mPersonDataList.get(position));
        startActivity(detailIntent);
    }

    public void addPerson(){
        Intent addIntent = new Intent(this, FormActivity.class);
        startActivityForResult(addIntent, ADD_REQUEST);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                addPerson();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
