package com.richardpingree.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by richardpingree on 3/14/15.
 */
public class CustomAdapter extends BaseAdapter {

    private static final long ID_CONSTANT = 0x000000;

    Context mContext;
    ArrayList<Person> mPeople;

    public CustomAdapter(Context context, ArrayList<Person> people){
        mContext = context;
        mPeople = people;
    }

    @Override
    public int getCount() {
        return mPeople.size();
    }

    @Override
    public Object getItem(int position) {
        return mPeople.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }
        Person person = (Person) getItem(position);
        TextView personView = (TextView)convertView.findViewById(R.id.person);
        personView.setText(person.toString());
        return convertView;
    }
}
