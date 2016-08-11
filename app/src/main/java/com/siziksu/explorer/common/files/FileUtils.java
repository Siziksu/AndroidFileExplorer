package com.siziksu.explorer.common.files;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.siziksu.explorer.common.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    public static boolean tryOpenWithDefaultMimeType(Context context, File file) {
        try {
            tryToOpen(context, file, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean tryOpenAsPlainText(Context context, File file) {
        try {
            tryToOpen(context, file, "text/plain");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static void tryToOpen(Context context, File newFile, String openAsMimeType) throws Exception {
        String mimeType = (openAsMimeType == null) ? MimeTypes.getMimeTypeFromFile(newFile.getName()) : openAsMimeType;
        if (mimeType != null) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(newFile), mimeType);
            context.startActivity(intent);
        }
    }

    public static void printFiles(List<File> files) {
        String string;
        for (File file : files) {
            string = file.isDirectory() ? "[FOL]" : "";
            string += file.isHidden() ? "[HID]" : "";
            string += FileUtils.isSymlink(file) ? "[LIN]" : "";
            try {
                string += " " + file.getCanonicalPath();
            } catch (IOException e) {
                Log.d(Constants.TAG, e.getMessage(), e);
            }
            Log.d(Constants.TAG, string);
        }
    }
}
