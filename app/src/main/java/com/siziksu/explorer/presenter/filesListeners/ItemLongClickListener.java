package com.siziksu.explorer.presenter.filesListeners;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.dialogs.AlertDialogs;
import com.siziksu.explorer.common.dialogs.Popups;
import com.siziksu.explorer.common.files.FileActions;
import com.siziksu.explorer.common.model.FileAction;
import com.siziksu.explorer.presenter.IFilesOwner;
import com.siziksu.explorer.presenter.IFilesView;
import com.siziksu.explorer.ui.adapter.FilesAdapter;

import java.io.File;

public class ItemLongClickListener implements FilesAdapter.OnItemLongClickListener {

    private IFilesView view;
    private FileAction action;

    private IFilesOwner presenter;

    public ItemLongClickListener(IFilesOwner presenter) {
        this.presenter = presenter;
    }

    public void register(IFilesView view) {
        this.view = view;
    }

    public void unregister() {
        view = null;
    }

    @Override
    public void onItemLongClick(View v, final int position) {
        if (view == null || view.getActivity().isFinishing()) {
            return;
        }
        Popups.get(view.getActivity())
              .anchor(v)
              .menu(R.menu.menu_action)
              .gravity(Gravity.TOP)
              .onClickListener(item -> {
                  switch (item.getItemId()) {
                      case R.id.rename:
                          break;
                      case R.id.delete:
                          deleteAction(position);
                          break;
                      case R.id.copy:
                          break;
                      case R.id.move:
                          action = new FileAction(FileAction.MOVE, presenter.getFileList().get(position), presenter.getDirectory());
                          presenter.enablePaste();
                          break;
                      default:
                          break;
                  }
                  return true;
              })
              .show();
    }

    private void deleteAction(int position) {
        new AlertDialogs((AppCompatActivity) view.getActivity()).confirm(
                R.string.action_delete,
                R.string.file_action_confirmation,
                () -> deleteFile(position)
        );
    }

    private void deleteFile(int position) {
        FileActions.delete(
                presenter.getFileList().get(position),
                (value, message) -> {
                    if (!value) {
                        new AlertDialogs((AppCompatActivity) view.getActivity()).info(message);
                    } else {
                        File deleted = presenter.getFileList().get(position);
                        presenter.getFileList().remove(position);
                        presenter.getFilesAdapter().notifyItemRemoved(position);
                        if (action != null && action.getFile().equals(deleted)) {
                            action = null;
                        }
                    }
                }
        );
    }

    public void pasteAction() {
        if (action == null) {
            presenter.disablePaste();
            return;
        }
        new AlertDialogs((AppCompatActivity) view.getActivity()).confirm(
                R.string.action_paste,
                R.string.file_action_confirmation,
                () -> {
                    switch (action.getAction()) {
                        case FileAction.COPY:
                            break;
                        case FileAction.MOVE:
                            pasteFile();
                            break;
                        default:
                            break;
                    }
                }
        );
    }

    private void pasteFile() {
        FileActions.move(
                action.getFile(),
                presenter.getDirectory(),
                (value, message) -> {
                    if (!value) {
                        new AlertDialogs((AppCompatActivity) view.getActivity()).info(message);
                    } else {
                        action = null;
                        presenter.disablePaste();
                        presenter.findFiles();
                    }
                }
        );
    }

    public void checkActions() {
        if (action == null) {
            presenter.disablePaste();
        } else {
            presenter.enablePaste();
        }
    }
}
