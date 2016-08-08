package com.siziksu.explorer.domain;

import com.siziksu.explorer.common.AsyncObject;
import com.siziksu.explorer.common.files.FileUtils;
import com.siziksu.explorer.common.functions.Action;
import com.siziksu.explorer.common.functions.Done;
import com.siziksu.explorer.common.functions.Fail;
import com.siziksu.explorer.common.functions.Success;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFilesRequest {

    private final File directory;

    private boolean showHidden;
    private boolean showSymLinks;

    public GetFilesRequest(File directory, boolean showHidden, boolean showSymLinks) {
        this.directory = directory;
        this.showHidden = showHidden;
        this.showSymLinks = showSymLinks;
    }

    public void getFiles(final Success<List<File>> success, final Fail fail, final Done done) {
        new AsyncObject<List<File>>()
                .subscribeOnMainThread()
                .action(new Action<List<File>>() {

                    @Override
                    public List<File> action() throws Exception {
                        File[] files = directory.listFiles();
                        List<File> list = new ArrayList<>();
                        if (files != null) {
                            for (File file : files) {
                                if (!file.exists()) {
                                    continue;
                                }
                                if (!showHidden && file.isHidden()) {
                                    continue;
                                }
                                if (!showSymLinks && FileUtils.isSymlink(file)) {
                                    continue;
                                }
                                list.add(file);
                            }
                        }
                        return list;
                    }
                })
                .done(new Done() {

                    @Override
                    public void done() {
                        done.done();
                    }
                })
                .subscribe(
                        new Success<List<File>>() {

                            @Override
                            public void success(List<File> response) {
                                success.success(response);
                            }
                        },
                        new Fail() {

                            @Override
                            public void fail(Throwable throwable) {
                                fail.fail(throwable);
                            }
                        }
                );
    }
}
