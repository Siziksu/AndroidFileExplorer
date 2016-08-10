package com.siziksu.explorer.injector;

import com.siziksu.explorer.data.GetFilesData;
import com.siziksu.explorer.data.IGetFilesData;
import com.siziksu.explorer.domain.GetFilesRequest;
import com.siziksu.explorer.domain.IGetFilesRequest;
import com.siziksu.explorer.presenter.IFilesPresenter;
import com.siziksu.explorer.presenter.FilesPresenter;

public class FilesModule {

    private final IFilesPresenter mainPresenter;
    private final IGetFilesData getFilesData;

    private static FilesModule instance;

    private FilesModule() {
        mainPresenter = new FilesPresenter();
        getFilesData = new GetFilesData();
    }

    public static FilesModule get() {
        if (instance == null) {
            instance = new FilesModule();
        }
        return instance;
    }

    public IFilesPresenter getWeather() {
        return mainPresenter.setGetFilesRequest(getFilesRequest());
    }

    private IGetFilesRequest getFilesRequest() {
        return new GetFilesRequest(getFilesData());
    }

    private IGetFilesData getFilesData() {
        return getFilesData;
    }
}
