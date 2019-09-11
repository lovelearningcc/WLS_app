package com.wls.dmr.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Shared工具类
 */
public class SharePreUtil {

    /**
	 * @param context 上下文
	 * @param keyname 存储数据的key
	 * @param value 存储数据的value
	 * @description shared保存String类型数据
	 */
	public static void SetShareString(Context context,String keyname,String value){
		SharedPreferences sp = PreferenceManager
					.getDefaultSharedPreferences(context);
		sp.edit().putString(keyname, value).commit();
	}

}
