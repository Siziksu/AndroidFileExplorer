package com.siziksu.explorer.common;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * ActivityCommon class.
 */
public final class ActivityCommon {

    private static ActivityCommon instance;

    private ActivityCommon() {
        // Private constructor
    }

    /**
     * This class must be initialized before asking for an instance.
     */
    public static void init() {
        if (instance == null) {
            instance = new ActivityCommon();
        }
    }

    /**
     * This method provides an instance of this class. First needs to be initialized.
     *
     * @return {@link ActivityCommon} object
     */
    public static ActivityCommon get() {
        if (instance == null) {
            throw new RuntimeException("This class must be initialized");
        }
        return instance;
    }

    /**
     * Sets toolbar.
     *
     * @param activity the activity
     * @param toolbar  the toolbar
     */
    public void setToolbar(AppCompatActivity activity, Toolbar toolbar) {
        activity.setSupportActionBar(toolbar);
    }

    /**
     * Apply tool bar style with home.
     *
     * @param activity the activity
     * @param toolbar  the toolbar
     */
    public void applyToolBarStyleWithHome(AppCompatActivity activity, Toolbar toolbar) {
        setToolbar(activity, toolbar);
        android.support.v7.app.ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
