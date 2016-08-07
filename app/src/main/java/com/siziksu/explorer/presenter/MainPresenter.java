package com.siziksu.explorer.presenter;

public interface MainPresenter {

    void getFiles();

    void register(MainView view);

    void unregister();

    void fileClicked(int position);
}
