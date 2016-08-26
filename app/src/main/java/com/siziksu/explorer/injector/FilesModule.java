package com.siziksu.explorer.injector;

import com.siziksu.explorer.data.GetFilesData;
import com.siziksu.explorer.data.IGetFilesData;
import com.siziksu.explorer.domain.GetFilesRequest;
import com.siziksu.explorer.domain.IGetFilesRequest;
import com.siziksu.explorer.presenter.FilesPresenter;
import com.siziksu.explorer.presenter.IFilesPresenter;

public class FilesModule {

    private static FilesModule instance;

    private FilesModule() {}

    public static FilesModule get() {
        if (instance == null) {
            instance = new FilesModule();
        }
        return instance;
    }

    public IFilesPresenter getWeather() {
        return new FilesPresenter(getFilesRequest());
    }

    private IGetFilesRequest getFilesRequest() {
        return new GetFilesRequest(getFilesData());
    }

    private IGetFilesData getFilesData() {
        return new GetFilesData();
    }
}
