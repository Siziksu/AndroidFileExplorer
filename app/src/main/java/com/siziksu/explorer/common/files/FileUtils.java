package com.siziksu.explorer.common.files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtils {

    private static final String ROOT_PATH = "/";

    public static boolean isSymlink(File file) {
        try {
            File canon;
            if (file.getParent() == null) {
                canon = file;
            } else {
                File canonDir = file.getParentFile().getCanonicalFile();
                canon = new File(canonDir, file.getName());
            }
            return !canon.getCanonicalFile().equals(canon.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isRoot(File file) {
        return file.toString().equalsIgnoreCase(ROOT_PATH);
    }

    public static String filePermissions(File file) {
        return (file.isDirectory() ? (isSymlink(file) ? "l" : "d") : "-") + (file.canRead() ? "r" : "-") + (file.canWrite() ? "w" : "-") + (file.canExecute() ? "x" : "-");
    }

    public static String fileDate(File file) {
        Date date = new Date(file.lastModified());
        SimpleDateFormat format = new SimpleDateFormat("MMM dd hh:mm", Locale.getDefault());
        return format.format(date);
    }
}
