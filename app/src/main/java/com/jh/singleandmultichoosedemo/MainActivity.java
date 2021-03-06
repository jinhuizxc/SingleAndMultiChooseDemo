package com.jh.singleandmultichoosedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jh.singleandmultichoosedemo.multi.MultiActivity;
import com.jh.singleandmultichoosedemo.single.SingleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 单选
     * @param view
     */
    public void single(View view){
        startActivity(new Intent(this, SingleActivity.class));
    }

    /**
     * 多选
     * @param view
     */
    public void multi(View view){
        startActivity(new Intent(this, MultiActivity.class));
    }
}
