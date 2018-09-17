package com.jh.singleandmultichoosedemo.multi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jh.singleandmultichoosedemo.OnItemClickListener;
import com.jh.singleandmultichoosedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinhui on 2018/9/17.
 * email: 1004260403@qq.com
 *
 * 属性setHasFixedSize(https://blog.csdn.net/sd19871122/article/details/51649965)：
 *
 * RecyclerView的尺寸在每次改变时，比如你加任何些东西。
 * setHasFixedSize 的作用就是确保尺寸是通过用户输入从而确保RecyclerView的尺寸是一个常数。
 * RecyclerView 的Item宽或者高不会变。每一个Item添加或者删除都不会变。如果你没有设置setHasFixedSized没有设置的代价将会是非常昂贵的。因为RecyclerView会需要而外计算每个item的size，
 *
 */

public class MultiActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TextView mTvCount;
    private List<String> data;
    // 全选
    private List<String> selectDatas = new ArrayList<>();
    private MultiAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        initData();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mTvCount = (TextView) findViewById(R.id.tv_count);
        // 确保尺寸是通过用户输入从而确保RecyclerView的尺寸是一个常数
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MultiAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (!MultiAdapter.isSelected.get(position)){
                    MultiAdapter.isSelected.put(position, true); // 修改map的值保存状态
                    mAdapter.notifyItemChanged(position);  // 局部刷新
                    selectDatas.add(data.get(position));
                }else {
                    MultiAdapter.isSelected.put(position, false); // 修改map的值保存状态
                    mAdapter.notifyItemChanged(position);
                    selectDatas.remove(data.get(position));
                }
                mTvCount.setText("已选中"+selectDatas.size()+"项");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(MultiActivity.this, "长按事件", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("测试" + i);
        }
    }

    /**
     * 全选
     * @param view
     */
    public void all(View view){
        selectDatas.clear();
        for (int i = 0; i < data.size(); i++) {
            MultiAdapter.isSelected.put(i, true);
            selectDatas.add(data.get(i));
        }
        mAdapter.notifyDataSetChanged();
        mTvCount.setText("已选中" + selectDatas.size() + "项");
    }

    /**
     * 反选
     * @param view
     */
    public void reverse(View view){
        for (int i = 0; i < data.size(); i++) {
            if (MultiAdapter.isSelected.get(i)){
                MultiAdapter.isSelected.put(i, false);
                selectDatas.remove(data.get(i));
            }else {
                MultiAdapter.isSelected.put(i, true);
                selectDatas.add(data.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
        mTvCount.setText("已选中" + selectDatas.size() + "项");
    }

    /**
     * 取消
     * @param view
     */
    public void cancel(View view){
        for (int i = 0; i < data.size(); i++) {
            if (MultiAdapter.isSelected.get(i)){
                MultiAdapter.isSelected.put(i, false);
                selectDatas.remove(data.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
        mTvCount.setText("已选中"+selectDatas.size()+"项");
    }
}
