package com.siziksu.explorer.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.HorizontalScrollView;

import com.siziksu.explorer.ui.adapter.FilesAdapter;

public interface MainPresenter {

    void register(MainView view);

    void unregister();

    void fileClicked(int position);

    void getFiles();

    void onSaveInstanceState(Bundle outState);

    void getFiles(Bundle savedInstanceState);

    void setRecyclerView(Activity activity, int id, FilesAdapter.OnAdapterListener listener);

    void fullScroll(HorizontalScrollView view);

    boolean onBackPressed();
}
