package com.siziksu.explorer;

import android.app.Application;

import com.siziksu.explorer.common.ActivityCommon;
import com.siziksu.explorer.common.PermissionManager;
import com.siziksu.explorer.common.Preferences;
import com.siziksu.explorer.common.files.MimeTypeMap;
import com.siziksu.explorer.injector.FilesModule;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.init(getApplicationContext());
        ActivityCommon.init();
        MimeTypeMap.init();
        PermissionManager.init();
    }

    public static FilesModule filesModule() {
        return FilesModule.get();
    }
}