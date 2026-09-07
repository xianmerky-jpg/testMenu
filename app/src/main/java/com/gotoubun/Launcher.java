package com.gotoubun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

public class Launcher {

    static {
        System.loadLibrary("nino");
    }

    public static void Init(Object object) {
        Context m_Context = (Context) object;
        Activity m_Activity = (Activity) object;

        // Request the overlay permission first. Starting Floating before this
        // permission is granted can make WindowManager.addView() throw and
        // terminate the app.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(m_Context)) {
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + m_Context.getPackageName())
            );
            m_Activity.startActivity(intent);
            return;
        }

        startFloatingService(m_Context);
    }

    static void startFloatingService(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(context)) {
            return;
        }

        Intent intent = new Intent(context.getApplicationContext(), Floating.class);
        try {
            context.startService(intent);
        } catch (SecurityException e) {
            // Do not let a denied/revoked overlay permission crash the app.
            e.printStackTrace();
        }
    }

    private static native void Init(Context mContext);
}
