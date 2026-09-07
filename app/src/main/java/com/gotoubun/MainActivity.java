package com.gotoubun;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

public class MainActivity extends Activity {

    private static final String TAG = "LABMOD_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "=== MainActivity.onCreate() ===");
        Launcher.Init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "=== MainActivity.onResume() ===");
        Log.d(TAG, "onResume: SDK=" + android.os.Build.VERSION.SDK_INT);

        if (android.os.Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(this)) {
            Log.d(TAG, "onResume: overlay permission OK, starting service");
            Launcher.startFloatingService(this);
        } else {
            Log.e(TAG, "onResume: overlay permission NOT granted");
        }
    }
}
