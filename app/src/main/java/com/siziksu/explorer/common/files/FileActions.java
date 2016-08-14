package com.siziksu.explorer.common.files;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.functions.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileActions {

    public static void delete(File file, Message message) {
        if (!file.exists()) {
            message.message(false, R.string.file_do_not_exists);
        } else if (file.isDirectory()) {
            message.message(false, R.string.folders_can_not_be_deleted);
        } else if (!file.canWrite()) {
            message.message(false, R.string.file_does_not_have_write_permission);
        } else if (!file.delete()) {
            message.message(false, R.string.file_could_not_be_deleted);
        } else {
            message.message(true, R.string.file_successfully_deleted);
        }
    }

    public static void move(File file, File destination, Message message) {
        File folder = !destination.isDirectory() ? destination.getParentFile() : destination;
        File newFile = new File(folder, file.getName());
        if (!file.exists()) {
            message.message(false, R.string.origin_file_do_not_exists);
        } else if (file.isDirectory()) {
            message.message(false, R.string.origin_file_can_not_be_a_folder);
        } else if (!folder.exists()) {
            message.message(false, R.string.destination_folder_do_not_exists);
        } else if (!folder.isDirectory()) {
            message.message(false, R.string.destination_is_not_a_directory);
        } else if (!folder.canWrite()) {
            message.message(false, R.string.destination_folder_does_not_have_write_permission);
        } else if (newFile.exists()) {
            message.message(false, R.string.file_already_exists);
        } else if (!file.renameTo(newFile)) {
            message.message(false, R.string.file_could_not_be_moved);
        } else {
            message.message(true, R.string.file_successfully_moved);
        }
    }

    public static void copy(File source, File destination, Message message) {
        File folder = !destination.isDirectory() ? destination.getParentFile() : destination;
        File copy = new File(folder, source.getName());
        if (!source.exists()) {
            message.message(false, R.string.origin_file_do_not_exists);
        } else if (source.isDirectory()) {
            message.message(false, R.string.origin_file_can_not_be_a_folder);
        } else if (!folder.exists()) {
            message.message(false, R.string.destination_folder_do_not_exists);
        } else if (!folder.isDirectory()) {
            message.message(false, R.string.destination_is_not_a_directory);
        } else if (!folder.canWrite()) {
            message.message(false, R.string.destination_folder_does_not_have_write_permission);
        } else if (copy.exists()) {
            message.message(false, R.string.file_already_exists);
        } else {
            try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(copy)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            } catch (IOException e) {
                message.message(false, R.string.file_could_not_be_copied);
                return;
            }
            message.message(true, R.string.file_successfully_copied);
        }
    }
}
