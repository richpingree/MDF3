package com.richardpingree.widget;

import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Created by richardpingree on 3/17/15.
 */
public class CollectionWidgetViewFactory implements RemoteViewsService.RemoteViewsFactory {
    @Override
    public void onCreate() {
        
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
