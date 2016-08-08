package com.siziksu.explorer.presenter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.comparators.FileComparator;
import com.siziksu.explorer.common.files.FileUtils;
import com.siziksu.explorer.common.functions.Done;
import com.siziksu.explorer.common.functions.Fail;
import com.siziksu.explorer.common.functions.Success;
import com.siziksu.explorer.domain.GetFilesRequest;
import com.siziksu.explorer.ui.adapter.FilesAdapter;
import com.siziksu.explorer.ui.view.DividerDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {

    private static final String ROOT_PATH = "/";

    private MainView view;

    private File directory;
    private List<File> files;

    private FilesAdapter adapter;

    private boolean showHidden;
    private boolean showSymLinks;

    public MainPresenterImpl() {
        directory = new File(ROOT_PATH);
        showHidden = true;
        showSymLinks = true;
        files = new ArrayList<>();
    }

    @Override
    public void getFiles() {
        new GetFilesRequest(directory, showHidden, showSymLinks).getFiles(
                new Success<List<File>>() {

                    @Override
                    public void success(List<File> response) {
                        if (view != null) {
                            files.clear();
                            if (response != null) {
                                if (!response.isEmpty()) {
                                    files.addAll(response);
                                    Collections.sort(files, new FileComparator());
                                    view.folderEmpty(false);
                                } else {
                                    view.folderEmpty(true);
                                }
                            }
                            if (!FileUtils.isRoot(directory)) {
                                view.setPath(directory.getAbsolutePath() + "/");
                            } else {
                                view.setPath(directory.getAbsolutePath());
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Fail() {
                    @Override
                    public void fail(Throwable throwable) {

                    }
                }, new Done() {
                    @Override
                    public void done() {

                    }
                }
        );
    }

    @Override
    public void register(MainView view) {
        this.view = view;
    }

    @Override
    public void unregister() {
        view = null;
    }

    @Override
    public void fileClicked(int position) {
        File newFile = files.get(position);
        if (newFile != null) {
            if (newFile.isDirectory()) {
                directory = newFile;
                getFiles();
            }
        }
    }

    @Override
    public void setRecyclerView(Activity activity, int id, FilesAdapter.OnAdapterListener listener) {
        RecyclerView recyclerView = (RecyclerView) activity.findViewById(id);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        adapter = new FilesAdapter(activity, files, listener);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerDecoration(ContextCompat.getDrawable(activity, R.drawable.recycler_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public boolean onBackPressed() {
        if (FileUtils.isRoot(directory)) {
            return true;
        } else {
            if (directory.getParentFile() != null) {
                directory = directory.getParentFile();
                getFiles();
            }
            return false;
        }
    }

    @Override
    public void fullScroll(final HorizontalScrollView view) {
        view.post(new Runnable() {

            @Override
            public void run() {
                view.fullScroll(View.FOCUS_RIGHT);
            }
        });
    }

}
