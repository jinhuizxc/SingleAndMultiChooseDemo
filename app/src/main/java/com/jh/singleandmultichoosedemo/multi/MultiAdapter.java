package com.jh.singleandmultichoosedemo.multi;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jh.singleandmultichoosedemo.OnItemClickListener;
import com.jh.singleandmultichoosedemo.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jinhui on 2018/9/17.
 * email: 1004260403@qq.com
 */

public class MultiAdapter extends RecyclerView.Adapter {

    private List<String> data;
    public static HashMap<Integer, Boolean> isSelected;

    // item监听回调方法
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MultiAdapter(List<String> data) {
        this.data = data;
        init();
    }

    /**
     * 初始化 设置所有item都为未选择
     */
    private void init() {
        isSelected = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            isSelected.put(i, false);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MultiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MultiViewHolder){
            final MultiViewHolder viewHolder = (MultiViewHolder) holder;
            String name = data.get(position);
            viewHolder.mTvName.setText(name);
            viewHolder.mCheckBox.setChecked(isSelected.get(position));
            viewHolder.itemView.setSelected(isSelected.get(position));

            // 设置监听
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

    private class MultiViewHolder extends RecyclerView.ViewHolder {

        TextView mTvName;
        CheckBox mCheckBox;

        public MultiViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

}
