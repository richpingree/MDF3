package com.richardpingree.mywidget.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.richardpingree.mywidget.Contact;
import com.richardpingree.mywidget.R;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class FormFragment extends Fragment {

    private static final String TAG = "FormFragment";

    public EditText first, last, email;

    public Contact newContact;

    private FormListener mListener;

    public interface FormListener{
        public void addContact(Contact newContact);
    }

    public FormFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof FormListener){
            mListener = (FormListener)activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement FormListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        first = (EditText)getView().findViewById(R.id.inputFirst);
        last = (EditText)getView().findViewById(R.id.inputLast);
        email = (EditText)getView().findViewById(R.id.inputEmail);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                newContact = new Contact();

                newContact.mFirst = first.getText().toString();
                newContact.mLast = last.getText().toString();
                newContact.mEmail = email.getText().toString();

                mListener.addContact(newContact);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
