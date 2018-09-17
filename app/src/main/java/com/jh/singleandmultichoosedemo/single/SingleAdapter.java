package com.jh.singleandmultichoosedemo.single;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jh.singleandmultichoosedemo.OnItemClickListener;
import com.jh.singleandmultichoosedemo.R;

import java.util.List;

/**
 * Created by jinhui on 2018/9/17.
 * email: 1004260403@qq.com
 */

public class SingleAdapter extends RecyclerView.Adapter {

    private List<String> data;
    private int selected = -1;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public SingleAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SingleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SingleViewHolder) {
            final SingleViewHolder viewHolder = (SingleViewHolder) holder;
            String name = data.get(position);
            viewHolder.mTvName.setText(name);

            if (selected == position) {
                viewHolder.mCheckBox.setChecked(true);
                viewHolder.itemView.setSelected(true);
            } else {
                viewHolder.mCheckBox.setChecked(false);
                viewHolder.itemView.setSelected(false);
            }

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        onItemClickListener.onItemClick(viewHolder.itemView, viewHolder.getAdapterPosition());
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setSelection(int position) {
        this.selected = position;
        notifyDataSetChanged();
    }

    private class SingleViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;
        CheckBox mCheckBox;

        public SingleViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
