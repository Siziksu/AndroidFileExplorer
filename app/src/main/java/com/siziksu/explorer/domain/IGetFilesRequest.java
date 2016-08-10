package com.siziksu.explorer.domain;

import com.siziksu.explorer.common.functions.Done;
import com.siziksu.explorer.common.functions.Fail;
import com.siziksu.explorer.common.functions.Success;

import java.io.File;
import java.util.List;

public interface IGetFilesRequest {

    IGetFilesRequest init(File directory, boolean showHidden, boolean showSymLinks);

    void getFiles(Success<List<File>> success, Fail fail, Done done);
}
