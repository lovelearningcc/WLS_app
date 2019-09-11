package com.wls.dmr.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.litepal.util.LogUtil;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

//import com.zyl.vincent.log.LogUtil;

/**
 * description ：
 * project name：Dmr
 * author : gcc
 * creation date: 2019\7\19 0019 10:21
 *
 * @version 1.0
 */



public class SharedPreferencesUtil {
    private Context mContext;
    private Editor mEditor;
    private SharedPreferences mPreferences;
    private String mFileName = "";
    private int mMode = 0;
    private static final String TAG = SharedPreferencesUtil.class.getSimpleName();


    public SharedPreferencesUtil(Context context, String fileName){
        this.mContext = context;
        this.mPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        this.mEditor = this.mPreferences.edit();
        mFileName = fileName;
        mMode = Context.MODE_PRIVATE;
//        Toast.makeText(mContext, "create SharedPreferencesUtil; name :" + mFileName + "; mode : " + mMode, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this," create SharedPreferencesUtil; name : " + mFileName + "; mode : " + mMode,Toast.LENGTH_SHORT).show();
    }

    public SharedPreferencesUtil(Context context, String fileName, int mode){
        this.mContext = context;
        this.mPreferences = context.getSharedPreferences(fileName, mode);
        this.mEditor = this.mPreferences.edit();
        mFileName = fileName;
        mMode = mode;
//        LogUtil.D(TAG," create SharedPreferencesUtil; name : " + mFileName + "; mode : " + mMode);
//        Toast.makeText(mContext, "create SharedPreferencesUtil; name :" + mFileName + "; mode : " + mMode, Toast.LENGTH_SHORT).show();
    }

    // 读写配置文件
    public boolean putString(String name, String value) {
        mEditor.putString(name, value);
        boolean result = mEditor.commit();

//        LogUtil.D(TAG, " put key : "+name+", value : "+value+" to file : "+mFileName+" result: "+result);
//        Toast.makeText(mContext, "create SharedPreferencesUtil; name :" + mFileName + "; mode : " + mMode, Toast.LENGTH_SHORT).show();
        return result;
    }

    public boolean putLong(String name, Long value) {
        mEditor.putLong(name, value);
        boolean result = mEditor.commit();

//        LogUtil.D(TAG, " put key : "+name+", value : "+value+" to file : "+mFileName+" result: "+result);
//        Toast.makeText(mContext, "create SharedPreferencesUtil; name :" + mFileName + "; mode : " + mMode, Toast.LENGTH_SHORT).show();
        return result;
    }

    public boolean putInt(String name, int value) {
        mEditor.putInt(name, value);
        boolean result = mEditor.commit();

//        LogUtil.D(TAG, " put key : "+name+", value : "+value+" to file : "+mFileName+" result: "+result);
//        Toast.makeText(mContext, "create SharedPreferencesUtil; name :" + mFileName + "; mode : " + mMode, Toast.LENGTH_SHORT).show();
        return result;
    }

    public boolean putBoolean(String name, Boolean value) {
        mEditor.putBoolean(name, value);
        boolean result = mEditor.commit();

//        LogUtil.D(TAG, " put key : "+name+", value : "+value+" to file : "+mFileName+" result: "+result);
//        Toast.makeText(mContext, "create SharedPreferencesUtil; name :" + mFileName + "; mode : " + mMode, Toast.LENGTH_SHORT).show();
        return result;
    }

    public boolean remove(String name) {
        mEditor.remove(name);
        boolean result = mEditor.commit();

//        LogUtil.D(TAG, " remove key : "+name+" from file : "+mFileName+" result: "+result);
//        Toast.makeText(mContext, "create SharedPreferencesUtil; name :" + mFileName + "; mode : " + mMode, Toast.LENGTH_SHORT).show();
        return result;

    }

    public boolean clear(){
        mEditor.clear();
        boolean result = mEditor.commit();

//        LogUtil.D(TAG, " clear file : "+mFileName+" result: "+result);
//        Toast.makeText(mContext, "create SharedPreferencesUtil; name :" + mFileName + "; mode : " + mMode, Toast.LENGTH_SHORT).show();
        return result;
    }

    public long getLong(String key) {
        return mPreferences.getLong(key, 0);
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public Boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public String getString(String key) {
        return mPreferences.getString(key, "");
    }

    public long getLong(String key, long defValue) {
        return mPreferences.getLong(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }

    public Boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    public String getString(String key, String defValue) {
        return mPreferences.getString(key, defValue);
    }

    public Editor getEditor(){
        return mEditor;
    }
}