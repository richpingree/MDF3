package com.richardpingree.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Richard Pingree MDF3 1503 Week3 on 3/17/15.
 */
public class CollectionWidgetService extends RemoteViewsService{

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new CollectionWidgetViewFactory(getApplicationContext());
    }
}
