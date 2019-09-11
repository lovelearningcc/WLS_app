package com.wls.dmr.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wls.dmr.R;
import com.wls.dmr.activity.LoginActivity;
import com.wls.dmr.persions.AlertDialogConfirm;
import com.wls.dmr.persions.HelpManualActivity;
import com.wls.dmr.persions.PhoneActivity;
import com.wls.dmr.persions.UserInfoActivity;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wls.dmr.entity.Contants.webTime;

/**
 * 个人中心-主界面
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private View mView;   //布局
    private LinearLayout mMeItem;
    private LinearLayout mHelpItem;
    private LinearLayout mShouQuanItem;
    private LinearLayout mContactItem;
    private LinearLayout mVersionItem;
    private LinearLayout mExitItem;

    private static final String TAG = "MeFragment";

    private TextView title_bar_title;

    private int num;

    private AlertDialogConfirm dialogConfirm;

    private boolean sd = false;
    private boolean sd1 = false;
    private Date date2;
    private String date3;
    private String dtm;
    private String dtm1;
    private int js1, js2;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String strtime, syear, smonth, sday;

    private String d;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = this.getActivity().getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();
//        editor.remove("sd");
        editor.commit();

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局
        mView = inflater.inflate(R.layout.fragment_me, container, false);

        initView();

        return mView;
    }

    /**
     * 初始化控件信息
     */
    private void initView() {

        title_bar_title = mView.findViewById(R.id.tv_title_bar_title1);
        title_bar_title.setText("个人中心");
        mMeItem = mView.findViewById(R.id.ll_me);            //个人资料
        mHelpItem = mView.findViewById(R.id.ll_help);      //帮助手册
        mShouQuanItem = mView.findViewById(R.id.ll_shouquan);    //远程控制授权
        mContactItem = mView.findViewById(R.id.ll_contact);  //联系客服
        mVersionItem = mView.findViewById(R.id.ll_version);  //版本号
        mExitItem = mView.findViewById(R.id.ll_exit);        //退出应用
        mShouQuanItem.setVisibility(View.GONE);

        mMeItem.setOnClickListener(this);
        mHelpItem.setOnClickListener(this);
        mShouQuanItem.setOnClickListener(this);
        mContactItem.setOnClickListener(this);
        mVersionItem.setOnClickListener(this);
        mExitItem.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_me:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.ll_help:
                startActivity(new Intent(getActivity(), HelpManualActivity.class));
                break;
            case R.id.ll_shouquan:
                d = getNetTime();
                Log.e(TAG, "d获取的网络时间为——————————》》》" + d);
                String str1 = d.substring(0, 4);
                String str2 = d.substring(5, 7);
                String str3 = d.substring(8);


                int year = Integer.parseInt(str1);
                int month = Integer.parseInt(str2);
                int day = Integer.parseInt(str3);

                Log.e(TAG, "时间的值为——————————》》》" + year + "年" + month + "月" + day + "日");

                js2 = (year + month + day + day * 1000 - 1000) * (day + 1818) + day;


                dtm = String.valueOf(js2);
                Log.e(TAG, "dtm的值为——————————》》》" + dtm);
                if (!TextUtils.isEmpty(dtm) && dtm.length() >= 6) {

                    dtm1 = dtm.substring(dtm.length() - 6, dtm.length());

                    Log.e(TAG, "98k的值为——————————》》》" + dtm1);
                }

                dialogConfirm = new AlertDialogConfirm(getActivity(), new AlertDialogConfirm.onClickAlertDialog() {
                    @Override
                    public void onClickConfirm(String value) {
                        if (value.equals(dtm1)) {
                            sd = true;
                            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();
                            sd1 = true;
//                            startActivity(new Intent(getActivity(), RTDActivity.class));
                        } else {
                            sd = false;
                            Toast.makeText(getActivity(), "授权失败,授权码错误", Toast.LENGTH_SHORT).show();
                        }

                        saveAccount14();
                    }

                    @Override
                    public void onClickCancel() {
                    }
                });
                dialogConfirm.builder().show();

                break;
            case R.id.ll_contact:
                startActivity(new Intent(getActivity(), PhoneActivity.class));
                break;
            case R.id.ll_version:
                num++;
                if (num >= 3) {
                    mShouQuanItem.setVisibility(View.VISIBLE);
                    num = 0;
                }
                Toast.makeText(getActivity(), "当前系统版本号为:" + getVersion(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_exit:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    public static String getNetTime() {
        try {
            URL url = new URL(webTime);
            URLConnection uc = url.openConnection();
            uc.setReadTimeout(5000);
            uc.setConnectTimeout(5000);
            uc.connect();
            long correctTime = uc.getDate();
            Date date = new Date(correctTime);
            SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
            String strtime = sdr.format(date);
            return strtime;
        } catch (Exception e) {
            return "0";
        }
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            String version = info.versionName;
            return "版本：" + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "找不到版本号";
        }
    }

    public void saveAccount14() {
        //设备系统时间
        editor.putBoolean("sd", sd); //远程控制开关标志位
        editor.putBoolean("sd1", sd1); //远程控制开关标志位
        editor.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
