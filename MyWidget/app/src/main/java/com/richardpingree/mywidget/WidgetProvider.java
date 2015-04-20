package com.richardpingree.mywidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by Richard Pingree MDF3 1504 Week 3 on 4/20/15.
 */
public class WidgetProvider extends AppWidgetProvider {

    public static final String ACTION_VIEW_DETAILS = "com.richardpingree.android.ACTION_VIEW_DETAILS";
    public static final String EXTRA_ITEM = "com.richardpingree.android.WidgetProvider.EXTRA_ITEM";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for(int i = 0; i < appWidgetIds.length; i++){

            int widgetId = appWidgetIds[i];

            Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

            RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            widgetView.setRemoteAdapter(R.id.contact_list, intent);
            widgetView.setEmptyView(R.id.contact_list, R.id.empty);

            Intent detailIntent = new Intent(ACTION_VIEW_DETAILS);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            widgetView.setPendingIntentTemplate(R.id.contact_list, pIntent);

            appWidgetManager.updateAppWidget(widgetId, widgetView);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(ACTION_VIEW_DETAILS)){
            Contact contact = (Contact)intent.getSerializableExtra(EXTRA_ITEM);
            if(contact != null){
                Intent details = new Intent(context, DetailActivity.class);
                details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                details.putExtra(DetailActivity.EXTRA_ITEM, contact);
                context.startActivity(details);
            }
        }
        super.onReceive(context, intent);
    }
}
