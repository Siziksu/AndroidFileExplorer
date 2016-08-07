package com.siziksu.explorer.presenter;

import android.app.Activity;

import java.io.File;
import java.util.List;

public interface MainView {

    Activity getActivity();

    void onFiles(List<File> items);
}