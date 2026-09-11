package cn.pickup.launcher;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

final class DeepLinkLauncher {
    private static final String TAG = "PickupDeepLink";

    private DeepLinkLauncher() {
    }

    static void open(Context context, Destination destination) {
        for (String appUri : destination.appUris) {
            if (tryOpen(context, appUri, destination.packageName)) {
                return;
            }
        }

        if (destination.openAppWhenDeepLinkUnavailable
                && tryOpenInstalledApp(context, destination.packageName)) {
            Toast.makeText(
                    context,
                    destination.title + "没有公开直达链接，已打开官方 App，请在 App 内查看取件码",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }

        // Some versions of the official apps can render their own HTTPS pages.
        // Try that route before handing the URL to the user's browser.
        if (tryOpen(context, destination.webUri, destination.packageName)) {
            return;
        }

        if (!tryOpen(context, destination.webUri, null)) {
            Toast.makeText(context, "暂时无法打开" + destination.title, Toast.LENGTH_SHORT).show();
        }
    }

    private static boolean tryOpenInstalledApp(Context context, String packageName) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (launchIntent == null) {
            Log.i(TAG, "No launch activity found for package " + packageName);
            return false;
        }
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (!(context instanceof android.app.Activity)) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            context.startActivity(launchIntent);
            Log.i(TAG, "Opened installed app " + packageName);
            return true;
        } catch (ActivityNotFoundException | SecurityException | IllegalArgumentException exception) {
            Log.w(TAG, "Could not open installed app " + packageName, exception);
            return false;
        }
    }

    private static boolean tryOpen(Context context, String uri, String packageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        if (packageName != null) {
            intent.setPackage(packageName);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (!(context instanceof android.app.Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        try {
            ComponentName resolved = intent.resolveActivity(context.getPackageManager());
            if (resolved == null) {
                Log.i(TAG, "No activity resolved for " + uri + " with package " + packageName);
                return false;
            }
            context.startActivity(intent);
            Log.i(TAG, "Opened " + uri + " with " + resolved.flattenToShortString());
            return true;
        } catch (ActivityNotFoundException | SecurityException | IllegalArgumentException exception) {
            Log.w(TAG, "Could not open " + uri + " with package " + packageName, exception);
            return false;
        }
    }
}
