package com.richardpingree.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.richardpingree.widget.Fragments.FormFragment;

/**
 * Created by richardpingree on 3/15/15.
 */
public class FormActivity extends Activity implements FormFragment.FormListener {

    private final String TAG = "FormActivity";

    Person newPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, new FormFragment()).commit();
        }
    }

    @Override
    public void addEntry(Person newPerson) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.ADDPERSONEXTRAFIRST, newPerson.mFirst);
        returnIntent.putExtra(MainActivity.ADDPERSONEXTRALAST, newPerson.mLast);
        returnIntent.putExtra(MainActivity.ADDPERSONEXTRAEMAIL, newPerson.mEmail);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
