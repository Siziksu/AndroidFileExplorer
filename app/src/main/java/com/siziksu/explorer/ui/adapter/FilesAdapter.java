package com.siziksu.explorer.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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

    private final Context context;
    private final OnItemClickListener listener;
    private final OnItemLongClickListener longListener;
    private final LayoutInflater inflater;
    private List<File> files;

    public FilesAdapter(Context context, List<File> files, OnItemClickListener listener, OnItemLongClickListener longListener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.files = files;
        this.listener = listener;
        this.longListener = longListener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.file_row, parent, false);
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
        if (symlink) {
            if (file.isDirectory()) {
                image = R.drawable.system_folder_symlink;
            } else {
                image = R.drawable.system_file_symlink;
            }
        } else if (file.isDirectory()) {
            image = R.drawable.system_folder;
        } else {
            image = MimeTypes.fileIcon(file.getName());
        }
        name = file.getName();
        permissions = FileUtils.filePermissions(file);
        date = FileUtils.fileDate(file);
        ((MainViewHolder) holder).fileImage.setImageResource(image);
        ((MainViewHolder) holder).fileName.setText(name);
        ((MainViewHolder) holder).fileName.setTextColor(ContextCompat.getColor(context, file.isHidden() ? R.color.hidden : R.color.dark));
        ((MainViewHolder) holder).filePermissions.setText(permissions);
        ((MainViewHolder) holder).fileDate.setText(date);
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

    class MainViewHolder extends BaseViewHolder implements View.OnClickListener, View.OnLongClickListener {

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
            fileRow.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            longListener.onItemLongClick(v, getAdapterPosition());
            return true;
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {

        void onItemLongClick(View v, int position);
    }
}
