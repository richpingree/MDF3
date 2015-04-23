package com.richardpingree.mywidget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class WidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final int ID_CONSTANT = 0x0101010;

    private ArrayList<Contact> mContact;
    private Context mContext;

    public WidgetViewFactory(Context context){
        mContext = context;
        mContact = new ArrayList<Contact>();
    }

    @Override
    public void onCreate() {
//        mContact.add(new Contact("Richard", "Pingree", "richpingree@email.com"));
//        mContact.add(new Contact("John", "Doe", "johndoe@email.com"));
//        mContact.add(new Contact("Steve", "Jobs", "stevejobs@email.com"));
    }

    @Override
    public void onDataSetChanged() {


    }

    @Override
    public void onDestroy() {
        mContact.clear();
    }

    @Override
    public int getCount() {
        return mContact.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        Contact contact = mContact.get(position);

        RemoteViews itemView = new RemoteViews(mContext.getPackageName(), R.layout.contact_item);

        itemView.setTextViewText(R.id.firstTxt, contact.getFirst());
        itemView.setTextViewText(R.id.lastTxt, contact.getLast());
        itemView.setTextViewText(R.id.emailTxt, contact.getEmail());

        Intent intent = new Intent();
        intent.putExtra(WidgetProvider.EXTRA_ITEM, contact);
        itemView.setOnClickFillInIntent(R.id.contact_item, intent);

        return itemView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return ID_CONSTANT + position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
