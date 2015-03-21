package com.richardpingree.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

/**
 * Created by Richard Pingree MDF3 1503 Week3 on 3/17/15.
 */
public class CollectionWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final int ID_CONSTANT = 0x0101010;

    private ArrayList<Person> mPeople;
    private Context mContext;

    public CollectionWidgetViewFactory(Context context){
        mContext = context;
        mPeople = new ArrayList<Person>();
    }

    @Override
    public void onCreate() {
        mPeople.add(new Person("Rich", "Pingree", "richpingree@email.com"));
        mPeople.add(new Person("John", "Smith", "johnsmith@email.com"));



    }



    @Override
    public void onDataSetChanged() {
        //Heavy lifting code can go here without blocking the UI.
        //You would update the data in your collection here as well.
    }

    @Override
    public void onDestroy() {
        mPeople.clear();
    }

    @Override
    public int getCount() {
        return mPeople.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Person person = mPeople.get(position);
        RemoteViews itemView = new RemoteViews(mContext.getPackageName(),R.layout.list_item);

        itemView.setTextViewText(R.id.person, person.toString());

        Intent intent = new Intent();
        intent.putExtra(CollectionWidgetProvider.PERSONEXTRA, person);
        itemView.setOnClickFillInIntent(R.id.list_item, intent);
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
