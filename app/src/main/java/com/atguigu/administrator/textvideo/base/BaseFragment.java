package com.atguigu.administrator.textvideo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 贺永亮 on 2017/1/16 0016.
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    public Context mContext;

    /**
     * Basefragment创建时调用的方法
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    /**
     * Basefragment建立view时调用的方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initview();
    }

    public abstract View initview();

    /**
     * 视图建立的时候用来初始化数据，绑定数据，联网请求用的
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdata();
    }

    public void initdata() {
    }

    //刷新Fragment
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onRefrshData();
        }
    }

    public void onRefrshData() {
    }


}
