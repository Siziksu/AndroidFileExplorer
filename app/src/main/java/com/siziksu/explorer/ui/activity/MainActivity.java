package com.siziksu.explorer.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.ActivityCommon;
import com.siziksu.explorer.presenter.MainPresenter;
import com.siziksu.explorer.presenter.MainPresenterImpl;
import com.siziksu.explorer.presenter.MainView;
import com.siziksu.explorer.ui.adapter.FilesAdapter;

public class MainActivity extends AppCompatActivity implements MainView, FilesAdapter.OnAdapterListener {

    private MainPresenter presenter;
    private TextView folder;
    private TextView emptyFolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar defaultToolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        ActivityCommon.get().applyToolBarStyleWithHome(this, defaultToolbar);
        folder = (TextView) findViewById(R.id.folder);
        emptyFolder = (TextView) findViewById(R.id.emptyFolder);
        presenter = new MainPresenterImpl();
        presenter.setRecyclerView(this, R.id.recyclerView, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register(this);
        presenter.getFiles();
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
