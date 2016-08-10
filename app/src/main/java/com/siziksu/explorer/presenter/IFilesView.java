package com.siziksu.explorer.presenter;

import android.app.Activity;

public interface IFilesView {

    Activity getActivity();

    void folderEmpty(boolean value);
}