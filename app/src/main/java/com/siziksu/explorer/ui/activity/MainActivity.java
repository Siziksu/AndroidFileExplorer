package com.siziksu.explorer.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.siziksu.explorer.App;
import com.siziksu.explorer.R;
import com.siziksu.explorer.common.ActivityCommon;
import com.siziksu.explorer.common.PermissionManager;
import com.siziksu.explorer.presenter.IFilesPresenter;
import com.siziksu.explorer.presenter.IFilesView;
import com.siziksu.explorer.ui.adapter.FilesAdapter;
import com.siziksu.explorer.ui.adapter.HeaderAdapter;

public class MainActivity extends AppCompatActivity implements IFilesView {

    private IFilesPresenter presenter;
    private TextView emptyFolder;
    private Bundle savedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar defaultToolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        ActivityCommon.get().applyToolBarStyleWithHome(this, defaultToolbar);
        emptyFolder = (TextView) findViewById(R.id.emptyFolder);
        presenter = App.filesModule().getWeather();
        presenter.setHeader(this, R.id.headerRecyclerView, new HeaderAdapterListener());
        presenter.setRecyclerView(this, R.id.recyclerView, new FileAdapterListener());
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
        PermissionManager.get().verifyStoragePermissions(this);
        presenter.register(this);
        if (savedState == null) {
            presenter.getFiles();
        } else {
            presenter.getFiles(savedState);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.get().onRequestPermissions();
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
    public void folderEmpty(boolean value) {
        emptyFolder.setVisibility(value ? View.VISIBLE : View.INVISIBLE);
    }

    private class FileAdapterListener implements FilesAdapter.OnAdapterListener {

        @Override
        public void onItemClick(int position) {
            presenter.fileClicked(position);
        }

        @Override
        public void onEndOfListReached() { }
    }

    private class HeaderAdapterListener implements HeaderAdapter.OnAdapterListener {

        @Override
        public void onItemClick(int position) {
            presenter.folderClicked(position);
        }

        @Override
        public void onEndOfListReached() { }
    }
}
