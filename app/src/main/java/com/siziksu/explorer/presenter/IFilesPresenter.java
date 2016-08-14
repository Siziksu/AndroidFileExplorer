package com.siziksu.explorer.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.siziksu.explorer.domain.IGetFilesRequest;

public interface IFilesPresenter {

    void register(IFilesView view);

    void unregister();

    IFilesPresenter setGetFilesRequest(IGetFilesRequest getFilesData);

    void onSaveInstanceState(Bundle outState);

    void getFiles();

    void getFiles(Bundle savedInstanceState);

    void setHeaderView(Activity activity, int id);

    void setFilesView(Activity activity, int id);

    boolean onBackPressed();

    void onCreateOptionsMenu(Menu menu);

    boolean onOptionsItemSelected(MenuItem item);
}
