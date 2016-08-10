package com.siziksu.explorer.data;

import com.siziksu.explorer.common.files.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFilesData implements IGetFilesData {

    @Override
    public List<File> getFiles(File directory, boolean showHidden, boolean showSymLinks) {
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
}
