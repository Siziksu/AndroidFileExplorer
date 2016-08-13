package com.siziksu.explorer.domain;

import com.siziksu.explorer.common.AsyncObject;
import com.siziksu.explorer.common.functions.Done;
import com.siziksu.explorer.common.functions.Fail;
import com.siziksu.explorer.common.functions.Success;
import com.siziksu.explorer.data.IGetFilesData;

import java.io.File;
import java.util.List;

public class GetFilesRequest implements IGetFilesRequest {

    private IGetFilesData getFilesData;

    private File directory;
    private boolean showHidden;
    private boolean showSymLinks;

    public GetFilesRequest(IGetFilesData getFilesData) {
        this.getFilesData = getFilesData;
    }

    @Override
    public GetFilesRequest init(File directory, boolean showHidden, boolean showSymLinks) {
        this.directory = directory;
        this.showHidden = showHidden;
        this.showSymLinks = showSymLinks;
        return this;
    }

    @Override
    public void getFiles(final Success<List<File>> success, final Fail fail, final Done done) {
        new AsyncObject<List<File>>()
                .subscribeOnMainThread()
                .action(() -> getFilesData.getFiles(directory, showHidden, showSymLinks))
                .done(done::done)
                .subscribe(success::success, fail::fail);
    }
}
