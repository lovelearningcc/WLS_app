package com.wls.jzgy.temp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by WKC on 2017/9/6.
 */

public class SpUtils {
    //用户登录信息
    private static SharedPreferences mSpUseInfo;
    private static final String USER_INFO = "login_data";//fileName
    private static final String USER_INFO_ID = "userid";//用户id

    private static SharedPreferences getSpUserInfo(Context context) {
        if (mSpUseInfo == null) {
            mSpUseInfo = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        }
        return mSpUseInfo;
    }

    //用户id
    public static void setUserInfoId(Context context, String userId) {
        getSpUserInfo(context).edit().putString(USER_INFO_ID, userId).commit();
    }

    public static String getUserInfoId(Context context) {
        return getSpUserInfo(context).getString(USER_INFO_ID, "");
    }

}
