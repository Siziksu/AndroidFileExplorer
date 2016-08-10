package com.siziksu.explorer.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import android.widget.TextView;

import com.siziksu.explorer.R;
import com.siziksu.explorer.common.model.Folder;

import java.util.List;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.BaseViewHolder> {

    private final OnAdapterListener listener;
    private final LayoutInflater inflater;
    private List<Folder> folders;

    public HeaderAdapter(Context context, List<Folder> folders, OnAdapterListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.folders = folders;
        this.listener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.header_element, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeaderAdapter.BaseViewHolder holder, int position) {
        final Folder folder = folders.get(position);
        ((MainViewHolder) holder).pathSeparator.setVisibility((position == 0) ? View.GONE : View.VISIBLE);
        ((MainViewHolder) holder).folder.setText(folder.getName());
        if (listener != null && position == folders.size() - 1) {
            listener.onEndOfListReached();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MainViewHolder extends BaseViewHolder implements View.OnClickListener {

        private final Space pathSeparator;
        private final TextView folder;

        public MainViewHolder(View itemView) {
            super(itemView);
            pathSeparator = (Space) itemView.findViewById(R.id.pathSeparator);
            folder = (TextView) itemView.findViewById(R.id.folder);
            folder.setOnClickListener(this);
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
