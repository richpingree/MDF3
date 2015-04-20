package com.richardpingree.mywidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetViewFactory(getApplicationContext());
    }
}
