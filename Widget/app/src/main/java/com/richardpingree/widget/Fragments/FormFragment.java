package com.richardpingree.widget.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.richardpingree.widget.Person;
import com.richardpingree.widget.R;

/**
 * Created by richardpingree on 3/15/15.
 */
public class FormFragment extends Fragment {

    private final String TAG = "FormFragment";

    public EditText first, last, email;

    public Person newPerson;

    private FormListener mListener;

    public interface FormListener{
        public void addEntry(Person newPerson);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        first = (EditText)getView().findViewById(R.id.firstText);
        last = (EditText)getView().findViewById(R.id.lastText);
        email = (EditText)getView().findViewById(R.id.emailText);
    }


}
