package com.siziksu.explorer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.files.FileUtils;
import com.siziksu.explorer.common.files.MimeTypes;

import java.io.File;
import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.BaseViewHolder> {

    private final OnAdapterListener listener;
    private final LayoutInflater inflater;
    private final List<File> files;

    public FilesAdapter(Context context, List<File> files, OnAdapterListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.files = files;
        this.listener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_detail, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilesAdapter.BaseViewHolder holder, int position) {
        final File file = files.get(position);
        boolean symlink = FileUtils.isSymlink(file);
        int image;
        String name;
        String permissions;
        String date;
        if (position == 0 && !FileUtils.isRoot(file.getParentFile())) {
            image = R.drawable.system_folder;
            name = "..";
            permissions = "Parent folder";
            date = "";
        } else {
            if (symlink) {
                image = R.drawable.system_symlink;
            } else if (file.isDirectory()) {
                image = R.drawable.system_folder;
            } else {
                image = MimeTypes.fileIcon(file.getName());
            }
            name = file.getName();
            permissions = FileUtils.filePermissions(file);
            date = FileUtils.fileDate(file);
        }
        ((MainViewHolder) holder).fileImage.setImageResource(image);
        ((MainViewHolder) holder).fileName.setText(name);
        ((MainViewHolder) holder).filePermissions.setText(permissions);
        ((MainViewHolder) holder).fileDate.setText(date);
        if (listener != null && position == files.size() - 1) {
            listener.onEndOfListReached();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MainViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final View fileRow;
        private final ImageView fileImage;
        private final TextView fileName;
        private final TextView filePermissions;
        private final TextView fileDate;

        public MainViewHolder(View itemView) {
            super(itemView);
            fileRow = itemView.findViewById(R.id.fileRow);
            fileImage = (ImageView) itemView.findViewById(R.id.fileImage);
            fileName = (TextView) itemView.findViewById(R.id.fileName);
            filePermissions = (TextView) itemView.findViewById(R.id.filePermissions);
            fileDate = (TextView) itemView.findViewById(R.id.fileDate);
            fileRow.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnAdapterListener {

        void onItemClick(int position);

        void onEndOfListReached();
    }
}
