package com.fullsail.android.collectionwidgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class CollectionWidgetProvider extends AppWidgetProvider {
	
	public static final String ACTION_VIEW_DETAILS = "com.fullsail.android.ACTION_VIEW_DETAILS";
	public static final String EXTRA_ITEM = "com.fullsail.android.CollectionWidgetProvider.EXTRA_ITEM";
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		for(int i = 0; i < appWidgetIds.length; i++) {
			
			int widgetId = appWidgetIds[i];
			
			Intent intent = new Intent(context, CollectionWidgetService.class);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
			
			RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			widgetView.setRemoteAdapter(R.id.article_list, intent);
			widgetView.setEmptyView(R.id.article_list, R.id.empty);
			
			Intent detailIntent = new Intent(ACTION_VIEW_DETAILS);
			PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			widgetView.setPendingIntentTemplate(R.id.article_list, pIntent);
			
			appWidgetManager.updateAppWidget(widgetId, widgetView);
		}
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent.getAction().equals(ACTION_VIEW_DETAILS)) {
			NewsArticle article = (NewsArticle)intent.getSerializableExtra(EXTRA_ITEM);
			if(article != null) {
				Intent details = new Intent(context, DetailsActivity.class);
				details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				details.putExtra(DetailsActivity.EXTRA_ITEM, article);
				context.startActivity(details);
			}
		}
		
		super.onReceive(context, intent);
	}
}