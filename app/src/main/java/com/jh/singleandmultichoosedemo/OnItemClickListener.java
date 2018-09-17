package com.jh.singleandmultichoosedemo;

import android.view.View;

/**
 * Created by jinhui on 2018/9/17.
 * email: 1004260403@qq.com
 *
 *  item监听回调方法
 */

public interface OnItemClickListener {

    void onItemClick(View view, int position);

    void onLongItemClick(View view, int position);

}
