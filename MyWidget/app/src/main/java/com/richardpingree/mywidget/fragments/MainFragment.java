package com.richardpingree.mywidget.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.richardpingree.mywidget.Contact;
import com.richardpingree.mywidget.R;

import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private ContactListener mListener;
    private ArrayList<Contact> mContactList;
    ArrayAdapter mAdapter;

    public interface ContactListener{
        public ArrayList<Contact> getContacts();
        public void viewContact(int position);
    }

    public MainFragment(){

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof ContactListener){
            mListener = (ContactListener)activity;
        }else{
            throw new IllegalArgumentException("Containing activity must implement ContactListener interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView contactListView = (ListView)getView().findViewById(R.id.contact_list);
        mAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mListener.getContacts());
        contactListView.setAdapter(mAdapter);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.viewContact(position);
            }
        });
    }

    public void updateList(){
        ListView contactListView = (ListView)getView().findViewById(R.id.contact_list);
        BaseAdapter adapter = (BaseAdapter)contactListView.getAdapter();
        adapter.notifyDataSetChanged();
    }
}
