package cn.pickup.launcher;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public final class PickupWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(
            Context context,
            AppWidgetManager appWidgetManager,
            int[] appWidgetIds
    ) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(
                    context.getPackageName(),
                    R.layout.pickup_widget
            );
            views.setOnClickPendingIntent(
                    R.id.widget_cainiao,
                    pendingIntent(context, Destination.CAINIAO, 101)
            );
            views.setOnClickPendingIntent(
                    R.id.widget_taobao,
                    pendingIntent(context, Destination.TAOBAO, 102)
            );
            views.setOnClickPendingIntent(
                    R.id.widget_pinduoduo,
                    pendingIntent(context, Destination.PINDUODUO, 103)
            );
            views.setOnClickPendingIntent(
                    R.id.widget_jd,
                    pendingIntent(context, Destination.JD, 105)
            );
            views.setOnClickPendingIntent(
                    R.id.widget_xhs,
                    pendingIntent(context, Destination.XHS, 106)
            );
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private PendingIntent pendingIntent(
            Context context,
            Destination destination,
            int requestCode
    ) {
        Intent intent = new Intent(context, MainActivity.class)
                .setAction(MainActivity.ACTION_OPEN)
                .putExtra(MainActivity.EXTRA_DESTINATION, destination.key);
        return PendingIntent.getActivity(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
    }
}
