package com.gotoubun;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

public class MainActivity extends Activity {

    private static final String GAME_ACTIVITY = "com.tencent.tmgp.cod.CODMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Launcher.Init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(this)) {
            Launcher.startFloatingService(this);
            startGame();
        }
    }

    private void startGame() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(getPackageName(), GAME_ACTIVITY));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
