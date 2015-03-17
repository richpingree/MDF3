package com.richardpingree.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.richardpingree.widget.Fragments.MainFragment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class MainActivity extends Activity implements MainFragment.PersonListener{


    private final String TAG = "MainActivity";

    private static final Object MAP_OF_PEOPLE = "Person";
    private static final int ADDREQUEST =1;
    public static String ADDPERSONEXTRAFIRST = "First Name";
    public static String ADDPERSONEXTRALAST = "Last Name";
    public static String ADDPERSONEXTRAEMAIL = "Email Address";

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == ADDREQUEST){
            Person newPerson = new Person();

            newPerson.mFirst = data.getStringExtra(ADDPERSONEXTRAFIRST);
            newPerson.mLast = data.getStringExtra(ADDPERSONEXTRALAST);
            newPerson.mEmail = data.getStringExtra(ADDPERSONEXTRAEMAIL);

            mPeopleDataList.add(newPerson);
            MainFragment mf = (MainFragment)getFragmentManager().findFragmentById(R.id.container);
            try{
                mf.updateList();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
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

    public void addPerson(){
        Intent addIntent = new Intent(this, FormActivity.class);
        startActivityForResult(addIntent, ADDREQUEST);
    }

    public void createFile() throws IOException {
        FileOutputStream fos = this.openFileOutput("savedfile", Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(MAP_OF_PEOPLE);
        oos.close();
        fos.close();
    }

    public void readFile() throws IOException{
        FileInputStream fis = this.openFileInput("savedfile");
        ObjectInputStream ois = new ObjectInputStream(fis);

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
                addPerson();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
