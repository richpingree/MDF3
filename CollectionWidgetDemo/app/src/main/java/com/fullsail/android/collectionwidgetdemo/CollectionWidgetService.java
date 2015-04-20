package com.fullsail.android.collectionwidgetdemo;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class CollectionWidgetService extends RemoteViewsService {

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new CollectionWidgetViewFactory(getApplicationContext());
	}
}