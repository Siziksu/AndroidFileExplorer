package com.siziksu.explorer.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.comparators.FileComparator;
import com.siziksu.explorer.common.files.FileUtils;
import com.siziksu.explorer.common.model.Folder;
import com.siziksu.explorer.common.model.State;
import com.siziksu.explorer.domain.IGetFilesRequest;
import com.siziksu.explorer.presenter.filesListeners.HeaderClickListener;
import com.siziksu.explorer.presenter.filesListeners.ItemClickListener;
import com.siziksu.explorer.presenter.filesListeners.ItemLongClickListener;
import com.siziksu.explorer.ui.adapter.FilesAdapter;
import com.siziksu.explorer.ui.adapter.HeaderAdapter;
import com.siziksu.explorer.ui.view.DividerDecoration;
import com.siziksu.explorer.ui.view.SmoothLayoutManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilesPresenter implements IFilesPresenter, IFilesOwner {

    private static final String ROOT_PATH = "/";
    private static final String EXTRAS_STATE = "state";

    private IFilesView view;

    private File directory;
    private List<Folder> folders;
    private List<File> files;

    private HeaderAdapter headerAdapter;
    private FilesAdapter filesAdapter;

    private boolean showHidden;
    private boolean showSymLinks;
    private IGetFilesRequest getFilesData;

    private RecyclerView headerView;
    private ItemClickListener clickListener;
    private ItemLongClickListener longClickListener;

    private MenuItem action_paste;

    public FilesPresenter() {
        directory = new File(ROOT_PATH);
        showHidden = true;
        showSymLinks = true;
        files = new ArrayList<>();
        folders = new ArrayList<>();
        folders.add(new Folder(ROOT_PATH, directory));
        clickListener = new ItemClickListener(this);
        longClickListener = new ItemLongClickListener(this);
    }

    @Override
    public void register(IFilesView view) {
        this.view = view;
        clickListener.register(view);
        longClickListener.register(view);
    }

    @Override
    public void unregister() {
        view = null;
        clickListener.unregister();
        longClickListener.unregister();
    }

    @Override
    public FilesPresenter setGetFilesRequest(IGetFilesRequest getFilesData) {
        this.getFilesData = getFilesData;
        return this;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        State state = new State();
        state.setDirectory(directory);
        state.setFiles(files);
        outState.putParcelable(EXTRAS_STATE, state);
    }

    @Override
    public void getFiles() {
        getFilesData.init(directory, showHidden, showSymLinks)
                    .getFiles(response -> {
                                  if (view != null) {
                                      files.clear();
                                      if (response != null) {
                                          if (!response.isEmpty()) {
                                              files.addAll(response);
                                              Collections.sort(files, new FileComparator());
                                              view.showFolderIsEmpty(false);
                                          } else {
                                              view.showFolderIsEmpty(true);
                                          }
                                      }
                                      filesAdapter.notifyDataSetChanged();
                                  }
                              },
                              throwable -> {},
                              () -> {}
                    );
    }

    @Override
    public void getFiles(Bundle savedInstanceState) {
        if (view != null) {
            if (files.isEmpty()) {
                State state = getState(savedInstanceState);
                if (state != null) {
                    directory = state.getDirectory();
                    if (!state.getFiles().isEmpty()) {
                        files.addAll(state.getFiles());
                        filesAdapter.notifyDataSetChanged();
                        view.showFolderIsEmpty(false);
                    } else {
                        view.showFolderIsEmpty(true);
                    }
                    scrollHeaderToEnd();
                }
            } else {
                view.showFolderIsEmpty(false);
                scrollHeaderToEnd();
            }
        }
    }

    @Override
    public void setHeaderView(Activity activity, int id) {
        headerView = (RecyclerView) activity.findViewById(id);
        headerView.setLayoutManager(new SmoothLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        headerAdapter = new HeaderAdapter(activity, folders, new HeaderClickListener(this));
        headerView.setAdapter(headerAdapter);
    }

    @Override
    public void setFilesView(Activity activity, int id) {
        RecyclerView filesView = (RecyclerView) activity.findViewById(id);
        filesView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        filesAdapter = new FilesAdapter(activity, files, clickListener, longClickListener);
        filesView.setAdapter(filesAdapter);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerDecoration(ContextCompat.getDrawable(activity, R.drawable.recycler_divider));
        filesView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onBackPressed() {
        if (FileUtils.isRoot(directory)) {
            return true;
        } else {
            if (directory.getParentFile() != null) {
                directory = directory.getParentFile();
                getFiles();
                int position = folders.size() - 1;
                folders.remove(position);
                headerAdapter.notifyItemRemoved(position);
                scrollHeaderToEnd();
            }
            return false;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu) {
        action_paste = menu.findItem(R.id.action_paste);
        if (longClickListener != null) {
            longClickListener.checkActions();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_paste:
                longClickListener.pasteAction();
                break;
        }
        return true;
    }

    @Override
    public void findFiles() {
        getFiles();
    }

    @Override
    public List<File> getFileList() {
        return files;
    }

    @Override
    public List<Folder> getFolderList() {
        return folders;
    }

    @Override
    public File getDirectory() {
        return directory;
    }

    @Override
    public void setDirectory(File file) {
        directory = file;
    }

    @Override
    public HeaderAdapter getHeaderAdapter() {
        return headerAdapter;
    }

    @Override
    public FilesAdapter getFilesAdapter() {
        return filesAdapter;
    }

    @Override
    public void enablePaste() {
        action_paste.setVisible(true);
    }

    @Override
    public void disablePaste() {
        action_paste.setVisible(false);
    }

    @Override
    public void scrollHeaderToEnd() {
        headerView.smoothScrollToPosition(folders.size() - 1);
    }

    private State getState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(EXTRAS_STATE)) {
            return savedInstanceState.getParcelable(EXTRAS_STATE);
        }
        return null;
    }
}
