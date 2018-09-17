package com.jh.singleandmultichoosedemo.single;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jh.singleandmultichoosedemo.OnItemClickListener;
import com.jh.singleandmultichoosedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinhui on 2018/9/17.
 * email: 1004260403@qq.com
 */

public class SingleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        initData();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final SingleAdapter adapter = new SingleAdapter(data);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                adapter.setSelection(position);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });

    }

    private void initData() {
        data = new ArrayList<>();
        for (int i=0; i<20; i++) {
            data.add("测试"+i);
        }
    }
}
