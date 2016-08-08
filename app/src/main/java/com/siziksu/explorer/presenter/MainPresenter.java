package com.siziksu.explorer.presenter;

import android.app.Activity;
import android.widget.HorizontalScrollView;

import com.siziksu.explorer.ui.adapter.FilesAdapter;

public interface MainPresenter {

    void getFiles();

    void register(MainView view);

    void unregister();

    void fileClicked(int position);

    void setRecyclerView(Activity activity, int id, FilesAdapter.OnAdapterListener listener);

    boolean onBackPressed();

    void fullScroll(HorizontalScrollView view);
}
