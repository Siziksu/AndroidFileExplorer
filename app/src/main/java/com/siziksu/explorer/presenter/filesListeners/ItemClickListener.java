package com.siziksu.explorer.presenter.filesListeners;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.Constants;
import com.siziksu.explorer.common.dialogs.AlertDialogs;
import com.siziksu.explorer.common.files.FileUtils;
import com.siziksu.explorer.common.model.Folder;
import com.siziksu.explorer.presenter.IFilesOwner;
import com.siziksu.explorer.presenter.IFilesView;
import com.siziksu.explorer.ui.adapter.FilesAdapter;

import java.io.File;

public class ItemClickListener implements FilesAdapter.OnItemClickListener {

    private IFilesView view;
    private IFilesOwner presenter;

    public ItemClickListener(IFilesOwner presenter) {
        this.presenter = presenter;
    }

    public void register(IFilesView view) {
        this.view = view;
    }

    public void unregister() {
        view = null;
    }

    @Override
    public void onItemClick(View v, int position) {
        File file = presenter.getFileList().get(position);
        if (file == null) {
            return;
        }
        if (file.canRead()) {
            if (file.isDirectory()) {
                presenter.setDirectory(file);
                presenter.findFiles();
                presenter.getFolderList().add(new Folder(presenter.getDirectory().getName(), presenter.getDirectory()));
                presenter.getHeaderAdapter().notifyDataSetChanged();
                presenter.scrollHeaderToEnd();
            } else {
                tryOpenFile(file);
            }
        } else {
            if (view == null || view.getActivity().isFinishing()) {
                return;
            }
            new AlertDialogs((AppCompatActivity) view.getActivity()).info(R.string.no_read_permission_message);
        }
    }

    private void tryOpenFile(File file) {
        if (FileUtils.tryOpenWithDefaultMimeType(view.getActivity(), file)) {
            return;
        }
        if (FileUtils.tryOpenAsPlainText(view.getActivity(), file)) {
            return;
        }
        Log.e(Constants.TAG, "No Activity found to handle the Intent");
    }
}
