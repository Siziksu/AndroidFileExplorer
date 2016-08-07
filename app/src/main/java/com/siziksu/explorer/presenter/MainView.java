package com.siziksu.explorer.presenter;

import android.app.Activity;

public interface MainView {

    Activity getActivity();

    void setPath(String path);

    void folderEmpty(boolean value);
}