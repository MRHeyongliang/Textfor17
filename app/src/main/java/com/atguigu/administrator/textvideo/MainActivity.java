package com.atguigu.administrator.textvideo;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;

import com.atguigu.administrator.textvideo.base.BaseFragment;
import com.atguigu.administrator.textvideo.fragment.NetAudioFragment;
import com.atguigu.administrator.textvideo.fragment.Recyclerviewfragment;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RadioGroup rg_main;
    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;

    /**
     * 装多个Fragment
     */
    private ArrayList<BaseFragment> fragments;
    /**
     * 下标位置
     */
    private int position;

    /**
     * 缓存的Fragment
     */
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        initFragemnt();
        //设置RadioGroup状态的监听
        initListener();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();


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


    private void initListener() {

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
                //当前要显示的Fragemnt
                Fragment currentFragemnt = getFragment(position);
                //切换
//                swichFragment(tempFragment, currentFragemnt);
                switchFragment(currentFragemnt);


            }
        });

        //默认选中本地视频
        rg_main.check(R.id.net_audio);
    }

    private void switchFragment(Fragment currentFragemnt) {
        if (tempFragment != currentFragemnt) {

            FragmentTransaction tf = getSupportFragmentManager().beginTransaction();
            //没有添加
            if (!currentFragemnt.isAdded()) {
                //把上一个隐藏
                if (tempFragment != null) {
                    tf.hide(tempFragment);
                }
                //添加
                tf.add(R.id.fl_main, currentFragemnt).commit();
                //事务提交
//                tf.commit();
            } else {
                //把上一个隐藏
                if (tempFragment != null) {
                    tf.hide(tempFragment);
                }
                //显示
                tf.show(currentFragemnt).commit();
                //事务提交
            }
            //在下面
            tempFragment = currentFragemnt;

        }
    }

    /**
     * 根据位置获取对应位置的Fragment
     *
     * @param position
     * @return
     */
    private Fragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    /**
     * 初始化Fragment
     */
    private void initFragemnt() {
        fragments = new ArrayList<>();
        fragments.add(new NetAudioFragment());//网络音频
        fragments.add(new Recyclerviewfragment());//RecyclerView

        //默认显示本地视频
//        swichFragment(position);
        defultFragemtn(fragments.get(position));

    }

    //设置默认的Fragemnt
    private void defultFragemtn(Fragment to) {
        tempFragment = to;
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, to).commit();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}

