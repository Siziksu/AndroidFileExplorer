package com.siziksu.explorer;

import android.app.Application;

import com.siziksu.explorer.common.ActivityCommon;
import com.siziksu.explorer.common.Preferences;

/**
 * Application class.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.init(getApplicationContext());
        ActivityCommon.init();
    }
}