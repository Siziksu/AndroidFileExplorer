package com.siziksu.explorer.data;

import java.io.File;
import java.util.List;

public interface IGetFilesData {

    List<File> getFiles(File directory, boolean showHidden, boolean showSymLinks);
}
