package com.siziksu.explorer.presenter;

import android.util.Log;

import com.siziksu.explorer.common.Constants;
import com.siziksu.explorer.common.comparators.FileComparator;
import com.siziksu.explorer.common.functions.Done;
import com.siziksu.explorer.common.functions.Fail;
import com.siziksu.explorer.common.functions.Success;
import com.siziksu.explorer.domain.GetFilesRequest;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {

    private static final String ROOT_PATH = "/";
    private static final String DEFAULT_PATH = "/";

    private MainView view;

    private File directory;
    private List<File> files;

    private boolean showHidden;
    private boolean showSymLinks;

    public MainPresenterImpl() {
        directory = new File(DEFAULT_PATH);
        showHidden = true;
        showSymLinks = true;
    }

    @Override
    public void getFiles() {
        new GetFilesRequest(directory, showHidden, showSymLinks).getFiles(
                new Success<List<File>>() {
                    @Override
                    public void success(List<File> response) {
                        if (view != null) {
                            Collections.sort(response, new FileComparator());
                            files = response;
                            view.onFiles(response);
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
        Log.d(Constants.TAG, files.get(position).getName());
    }
}
