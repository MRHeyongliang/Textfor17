package com.atguigu.administrator.textvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.atguigu.administrator.textvideo.base.BaseFragment;
import com.atguigu.administrator.textvideo.fragment.NetAudioFragment;
import com.atguigu.administrator.textvideo.fragment.Recyclerviewfragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        initfragments();
        initlistener();
    }

    private void initlistener() {
    }

    private void initfragments() {
        fragments = new ArrayList<>();
        fragments.add(new NetAudioFragment());
        fragments.add(new Recyclerviewfragment());
    }
}
