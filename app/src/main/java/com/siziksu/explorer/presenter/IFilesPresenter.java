package com.siziksu.explorer.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.HorizontalScrollView;

import com.siziksu.explorer.domain.IGetFilesRequest;
import com.siziksu.explorer.ui.adapter.FilesAdapter;

public interface IFilesPresenter {

    void register(IFilesView view);

    void unregister();

    IFilesPresenter setGetFilesRequest(IGetFilesRequest getFilesData);

    void onSaveInstanceState(Bundle outState);

    void fileClicked(int position);

    void getFiles();

    void getFiles(Bundle savedInstanceState);

    void setRecyclerView(Activity activity, int id, FilesAdapter.OnAdapterListener listener);

    void fullScroll(HorizontalScrollView view);

    boolean onBackPressed();
}
