package com.siziksu.explorer.common.model;

import java.io.File;

public class FileAction {

    public static final int COPY = 0;
    public static final int MOVE = 1;

    private final int type;
    private final File file;
    private final File folder;

    public FileAction(int type, File file, File folder) {
        this.type = type;
        this.file = file;
        this.folder = folder;
    }

    public int getAction() {
        return type;
    }

    public File getFile() {
        return file;
    }

    public File getFolder() {
        return folder;
    }
}
