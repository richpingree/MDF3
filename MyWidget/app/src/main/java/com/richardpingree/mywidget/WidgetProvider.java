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
    public static final String ACTION_FORM = "com.richardpingree.android.ACTION_FORM";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for(int i = 0; i < appWidgetIds.length; i++){

            int widgetId = appWidgetIds[i];
            appWidgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.contact_list);

            Intent intent = new Intent(context, WidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

            RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            widgetView.setRemoteAdapter(R.id.contact_list, intent);
            widgetView.setEmptyView(R.id.contact_list, R.id.empty);

            Intent detailIntent = new Intent(ACTION_VIEW_DETAILS);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            widgetView.setPendingIntentTemplate(R.id.contact_list, pIntent);

            Intent formIntent = new Intent(ACTION_FORM);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, formIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            widgetView.setOnClickPendingIntent(R.id.button, pendingIntent);

//            Intent formIntent = new Intent(context, FormActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, formIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//            widgetView.setOnClickPendingIntent(R.id.button, pendingIntent);


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
            else if(intent.getAction().equals(ACTION_FORM)){
                Intent form = new Intent(context, FormActivity.class);
                form.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(form);
            }
        }
        super.onReceive(context, intent);
    }
}
