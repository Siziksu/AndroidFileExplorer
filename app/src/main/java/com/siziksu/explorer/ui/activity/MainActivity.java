package com.siziksu.explorer.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.ActivityCommon;
import com.siziksu.explorer.presenter.MainPresenter;
import com.siziksu.explorer.presenter.MainPresenterImpl;
import com.siziksu.explorer.presenter.MainView;
import com.siziksu.explorer.ui.adapter.FilesAdapter;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, FilesAdapter.OnAdapterListener {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar defaultToolbar = (Toolbar) findViewById(R.id.defaultToolbar);
        ActivityCommon.get().applyToolBarStyleWithHome(this, defaultToolbar);
        presenter = new MainPresenterImpl();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register(this);
        presenter.getFiles();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unregister();
    }

    @Override
    public void onFiles(List<File> files) {
        FilesAdapter adapter = new FilesAdapter(this, files, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        presenter.fileClicked(position);
    }

    @Override
    public void onEndOfListReached() { }
}
