package com.atguigu.administrator.textvideo.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.administrator.textvideo.base.BaseFragment;

public class NetAudioFragment extends BaseFragment {

    private TextView tv;
    @Override
    public View initview() {
        tv = new TextView(mContext);
        tv.setTextSize(30);
        tv.setTextColor(Color.RED);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    @Override
    public void initdata() {
        super.initdata();
        tv.setText("NetAudioFragment");
    }
}
