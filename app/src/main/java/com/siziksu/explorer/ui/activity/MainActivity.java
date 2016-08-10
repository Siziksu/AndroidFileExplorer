package com.siziksu.explorer.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import com.siziksu.explorer.App;
import com.siziksu.explorer.R;
import com.siziksu.explorer.common.ActivityCommon;
import com.siziksu.explorer.presenter.IFilesPresenter;
import com.siziksu.explorer.presenter.IFilesView;
import com.siziksu.explorer.ui.adapter.FilesAdapter;

public class MainActivity extends AppCompatActivity implements IFilesView, FilesAdapter.OnAdapterListener {

    private IFilesPresenter presenter;
    private TextView folder;
    private TextView emptyFolder;
    private HorizontalScrollView horizontalScrollView;
    private Bundle savedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar defaultToolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        ActivityCommon.get().applyToolBarStyleWithHome(this, defaultToolbar);
        folder = (TextView) findViewById(R.id.folder);
        emptyFolder = (TextView) findViewById(R.id.emptyFolder);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        presenter = App.filesModule().getWeather();
        presenter.setRecyclerView(this, R.id.recyclerView, this);
        if (savedInstanceState != null) {
            savedState = savedInstanceState;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register(this);
        if (savedState == null) {
            presenter.getFiles();
        } else {
            presenter.getFiles(savedState);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
    }

    @Override
    public void onBackPressed() {
        if (presenter.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setPath(String path) {
        folder.setText(path);
        presenter.fullScroll(horizontalScrollView);
    }

    @Override
    public void folderEmpty(boolean value) {
        emptyFolder.setVisibility(value ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onItemClick(int position) {
        presenter.fileClicked(position);
    }

    @Override
    public void onEndOfListReached() { }
}
