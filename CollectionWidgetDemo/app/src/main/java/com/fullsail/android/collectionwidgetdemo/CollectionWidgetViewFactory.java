package com.fullsail.android.collectionwidgetdemo;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import java.util.ArrayList;

public class CollectionWidgetViewFactory implements RemoteViewsFactory {
	
	private static final int ID_CONSTANT = 0x0101010;
    public static final String EXTRA = "com.fullsail.android.CollectionWidgetViewFactory.EXTRA";

    private ArrayList<Person> mPersons;
	private Context mContext;
	
	public CollectionWidgetViewFactory(Context context) {
		mContext = context;
		mPersons = new ArrayList<Person>();
	}

	@Override
	public void onCreate() {
        mPersons.add(new Person("Richard", "Pingree", "richpingree@email.com"));

//		String[] titles = { "New Phones Released!", "Random App Makes $300 Million",
//				"Google Glass Robots", "Arduino Used in Mobile", "Orioles Win World Series" };
//		String[] authors = { "Phone Scoop", "Yahoo Marketing Department", "Cyborg Alliance",
//				"Some Open Source Person", "Roch Kubatko" };
//		String[] dates = { "Everyday", "June 20, 2012", "September 10, 2014", "August 9, 2014", "November 10, 2014" };
//
//		for(int i = 0; i < 5; i++) {
//			mPersons.add(new Person(titles[i], authors[i], dates[i]));
//		}
	}

	@Override
	public int getCount() {
		return mPersons.size();
	}

	@Override
	public long getItemId(int position) {
		return ID_CONSTANT + position;
	}

	@Override
	public RemoteViews getLoadingView() {
		return null;
	}

	@Override
	public RemoteViews getViewAt(int position) {
		
		Person article = mPersons.get(position);
		
		RemoteViews itemView = new RemoteViews(mContext.getPackageName(), R.layout.article_item);
		
		itemView.setTextViewText(R.id.firstTxt, article.getFirst());
		itemView.setTextViewText(R.id.lastTxt, article.getLast());
		itemView.setTextViewText(R.id.emailTxt, article.getEmail());
		
		Intent intent = new Intent();
		intent.putExtra(CollectionWidgetProvider.EXTRA_ITEM, article);
		itemView.setOnClickFillInIntent(R.id.article_item, intent);
		
		return itemView;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public void onDataSetChanged() {
		// Heavy lifting code can go here without blocking the UI.
		// You would update the data in your collection here as well.
	}

	@Override
	public void onDestroy() {
		mPersons.clear();
	}
	
}