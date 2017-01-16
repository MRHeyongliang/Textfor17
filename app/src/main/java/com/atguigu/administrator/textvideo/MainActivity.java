package com.atguigu.administrator.textvideo;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.atguigu.administrator.textvideo.base.BaseFragment;
import com.atguigu.administrator.textvideo.fragment.NetAudioFragment;
import com.atguigu.administrator.textvideo.fragment.Recyclerviewfragment;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;
    /**
     * 缓存的Fragment
     */

    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        initfragments();
        initlistener();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
    }

    private int position;

    private void initlistener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.net_audio:
                        position = 0;
                        break;
                    case R.id.Recyclerview:
                        position = 1;
                        break;
                }
                BaseFragment currfragment = fragments.get(position);
                switchfragment(currfragment);
            }

        });
        rg_main.check(R.id.net_audio);
    }

    //要替换的fragment
    private Fragment tempfragment;

    private void switchfragment(BaseFragment currfragment) {
        if (tempfragment != currfragment) {
            //开启事务
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (currfragment != null) {
                if (!currfragment.isAdded()) {
                    if (tempfragment != null) {
                        ft.hide(tempfragment);
                    }
                    //没有增加就增加
                    ft.add(R.id.fl_main, currfragment);
                } else {
                    if (tempfragment != null) {
                        ft.hide(tempfragment);
                    }
                    ft.show(currfragment);
                }
                ft.commit();
            }
            tempfragment = currfragment;
        }

    }


    private void initfragments() {
        fragments = new ArrayList<>();
        fragments.add(new NetAudioFragment());
        fragments.add(new Recyclerviewfragment());
        //默认显示的本地视频
        defultFragemtn(fragments.get(position));
    }

    private void defultFragemtn(Fragment to) {
        tempfragment = to;
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, to).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
