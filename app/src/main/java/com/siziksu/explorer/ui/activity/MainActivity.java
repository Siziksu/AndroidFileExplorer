package com.siziksu.explorer.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.siziksu.explorer.App;
import com.siziksu.explorer.R;
import com.siziksu.explorer.common.ActivityCommon;
import com.siziksu.explorer.common.PermissionManager;
import com.siziksu.explorer.presenter.IFilesPresenter;
import com.siziksu.explorer.presenter.IFilesView;

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
        presenter.setHeaderView(this, R.id.headerView);
        presenter.setFilesView(this, R.id.filesView);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem action_paste = menu.add(Menu.NONE, R.id.action_paste, Menu.NONE, getString(R.string.action_paste));
        action_paste.setIcon(R.drawable.ic_menu_paste);
        action_paste.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        action_paste.setVisible(false);
        presenter.menuReady(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.onOptionsItemSelected(item);
    }
}
