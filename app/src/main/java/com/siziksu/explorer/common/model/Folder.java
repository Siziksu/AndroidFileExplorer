package com.siziksu.explorer.common.model;

import java.io.File;

public class Folder {

    public String name;
    public File folder;

    public Folder(String name, File folder) {
        this.name = name;
        this.folder = folder;
    }

    public String getName() {
        return name;
    }

    public File getFolder() {
        return folder;
    }
}
