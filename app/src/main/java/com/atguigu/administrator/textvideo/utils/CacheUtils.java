package com.atguigu.administrator.textvideo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 贺永亮 on 2017/1/16 0016.
 */

public class CacheUtils {
    public static String getString(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void putString(Context mContext, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences("atguigu",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}
