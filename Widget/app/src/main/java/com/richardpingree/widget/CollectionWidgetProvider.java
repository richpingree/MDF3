package com.richardpingree.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by Richard Pingree MDF3 1503 Week3 on 3/17/15.
 */
public class CollectionWidgetProvider extends AppWidgetProvider{

    public static final String ACTION_VIEW_DETAILS = "com.richardpingree.widget.ACTION_VIEW_DETAILS";
    public static final String PERSONEXTRA = "Person";
    private static final String ACTION_CREATE = "com.richardpingree.widget.ACTION_CREATE";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for(int i=0; i< appWidgetIds.length;i++){
            int widgetId = appWidgetIds[i];

            Intent intent = new Intent(context, CollectionWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

            RemoteViews widgetView = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
            widgetView.setRemoteAdapter(R.id.article_list, intent);

            Intent detailIntent = new Intent(ACTION_VIEW_DETAILS);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, detailIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            widgetView.setPendingIntentTemplate(R.id.article_list, pIntent);

            Intent addIntent = new Intent(context, FormActivity.class);
            PendingIntent cpIntent = PendingIntent.getActivity(context, 0, addIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            widgetView.setOnClickPendingIntent(R.id.AddBtn, cpIntent);

            appWidgetManager.updateAppWidget(widgetId,widgetView);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION_VIEW_DETAILS)){
            Person person = (Person)intent.getSerializableExtra(PERSONEXTRA);
            if(person != null){
                Intent details = new Intent(context, DetailActivity.class);
                details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                details.putExtra(DetailActivity.PERSONEXTRA, person);
                context.startActivity(details);
            }
        }
        super.onReceive(context, intent);
    }
}
