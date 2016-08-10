package com.siziksu.explorer.presenter;

import android.app.Activity;
import android.os.Bundle;

import com.siziksu.explorer.domain.IGetFilesRequest;
import com.siziksu.explorer.ui.adapter.FilesAdapter;
import com.siziksu.explorer.ui.adapter.HeaderAdapter;

public interface IFilesPresenter {

    void register(IFilesView view);

    void unregister();

    IFilesPresenter setGetFilesRequest(IGetFilesRequest getFilesData);

    void onSaveInstanceState(Bundle outState);

    void fileClicked(int position);

    void folderClicked(int position);

    void getFiles();

    void getFiles(Bundle savedInstanceState);

    void setHeader(Activity activity, int id, HeaderAdapter.OnAdapterListener listener);

    void setRecyclerView(Activity activity, int id, FilesAdapter.OnAdapterListener listener);

    boolean onBackPressed();
}
