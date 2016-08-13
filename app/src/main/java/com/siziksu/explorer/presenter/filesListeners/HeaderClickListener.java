package com.siziksu.explorer.presenter.filesListeners;

import com.siziksu.explorer.presenter.IFilesOwner;
import com.siziksu.explorer.ui.adapter.HeaderAdapter;

public class HeaderClickListener implements HeaderAdapter.OnItemClickListener {

    private IFilesOwner presenter;

    public HeaderClickListener(IFilesOwner presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(int position) {
        presenter.setDirectory(presenter.getFolderList().get(position).getFolder());
        for (int i = presenter.getFolderList().size() - 1; i > position; i--) {
            presenter.getFolderList().remove(i);
            presenter.getHeaderAdapter().notifyItemRemoved(i);
        }
        presenter.findFiles();
    }
}
