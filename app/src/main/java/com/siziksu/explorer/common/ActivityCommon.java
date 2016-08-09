package com.siziksu.explorer.common;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public final class ActivityCommon {

    private static ActivityCommon instance;

    private ActivityCommon() {
        // Private constructor
    }

    public static void init() {
        if (instance == null) {
            instance = new ActivityCommon();
        }
    }

    public static ActivityCommon get() {
        if (instance == null) {
            throw new RuntimeException("This class must be initialized");
        }
        return instance;
    }

    public void setToolbar(AppCompatActivity activity, Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
    }

    public void applyToolBarStyleWithHome(AppCompatActivity activity, Toolbar toolbar) {
        setToolbar(activity, toolbar);
        android.support.v7.app.ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
