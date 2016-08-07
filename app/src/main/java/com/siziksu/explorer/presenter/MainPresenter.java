package com.siziksu.explorer.presenter;

import com.siziksu.explorer.ui.adapter.FilesAdapter;

public interface MainPresenter {

    void getFiles();

    void register(MainView view);

    void unregister();

    void fileClicked(int position);

    void setRecyclerView(int id, FilesAdapter.OnAdapterListener listener);

    boolean onBackPressed();
}
