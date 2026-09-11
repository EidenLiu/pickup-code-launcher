package cn.pickup.launcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class LaunchReceiver extends BroadcastReceiver {
    static final String ACTION_OPEN = "cn.pickup.launcher.widget.OPEN";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ACTION_OPEN.equals(intent.getAction())) {
            return;
        }
        Destination destination = Destination.fromKey(
                intent.getStringExtra(MainActivity.EXTRA_DESTINATION)
        );
        if (destination != null) {
            DeepLinkLauncher.open(context, destination);
        }
    }
}

