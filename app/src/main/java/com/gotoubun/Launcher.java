package com.gotoubun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

public class Launcher {

    private static final String TAG = "LABMOD_DEBUG";

    static {
        System.loadLibrary("nino");
    }

    public static void Init(Object object) {
        Log.d(TAG, "=== Launcher.Init() ===");
        Context m_Context = (Context) object;
        Activity m_Activity = (Activity) object;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(m_Context)) {
            Log.d(TAG, "Init: overlay permission NOT granted, requesting...");
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + m_Context.getPackageName())
            );
            m_Activity.startActivity(intent);
            Log.d(TAG, "Init: returned from permission request (service NOT started yet)");
            return;
        }

        Log.d(TAG, "Init: overlay permission granted, starting service");
        startFloatingService(m_Context);
    }

    static void startFloatingService(Context context) {
        Log.d(TAG, "=== startFloatingService() ===");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !Settings.canDrawOverlays(context)) {
            Log.e(TAG, "startFloatingService: overlay permission NOT granted, ABORT");
            return;
        }

        Intent intent = new Intent(context.getApplicationContext(), Floating.class);
        try {
            Log.d(TAG, "startFloatingService: calling startService(Floating.class)");
            context.startService(intent);
            Log.d(TAG, "startFloatingService: startService SUCCESS");
        } catch (SecurityException e) {
            Log.e(TAG, "startFloatingService: SecurityException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static native void Init(Context mContext);
}
