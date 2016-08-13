package com.siziksu.explorer.presenter;

import com.siziksu.explorer.common.model.Folder;
import com.siziksu.explorer.ui.adapter.FilesAdapter;
import com.siziksu.explorer.ui.adapter.HeaderAdapter;

import java.io.File;
import java.util.List;

public interface IFilesOwner {

    void findFiles();

    List<File> getFileList();

    List<Folder> getFolderList();

    File getDirectory();

    void setDirectory(File file);

    HeaderAdapter getHeaderAdapter();

    void scrollHeaderToEnd();

    FilesAdapter getFilesAdapter();

    void enablePaste();

    void disablePaste();
}
