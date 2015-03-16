package com.richardpingree.widget.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.richardpingree.widget.CustomAdapter;
import com.richardpingree.widget.Person;
import com.richardpingree.widget.R;

import java.util.ArrayList;

/**
 * Created by richardpingree on 3/14/15.
 */
public class MainFragment extends Fragment{

    private final String TAG = "MainFragment";

    private PersonListener mListener;
    private ArrayList<Person> mPeopleList;
    CustomAdapter mAdapter;

    public interface PersonListener{
        public ArrayList<Person> getPeople();
        public void viewPerson(int position);
    }

    public MainFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof PersonListener){
            mListener = (PersonListener)activity;
        }else{
            throw new IllegalArgumentException("Containing activtiy must implement PersonListener interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView peopleListView = (ListView)getView().findViewById(R.id.people_list);
        mAdapter = new CustomAdapter(getActivity(), mListener.getPeople());
        peopleListView.setAdapter(mAdapter);

        peopleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.viewPerson(position);
            }
        });
    }

    public void updateList() {
        ListView peopleListView = (ListView)getView().findViewById(R.id.people_list);
        BaseAdapter adapter = (BaseAdapter)peopleListView.getAdapter();
        adapter.notifyDataSetChanged();

    }
}
