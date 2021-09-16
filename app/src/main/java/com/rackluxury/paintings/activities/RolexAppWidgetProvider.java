package com.rackluxury.paintings.activities;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.rackluxury.paintings.R;

public class RolexAppWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, LoginOrRegisterActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rolex_widget);
            views.setOnClickPendingIntent(R.id.example_widget_button, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}