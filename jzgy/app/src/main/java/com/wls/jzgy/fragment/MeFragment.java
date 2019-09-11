package com.wls.jzgy.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wls.jzgy.R;
import com.wls.jzgy.activity.LoginActivity;

import com.wls.jzgy.persions.AlertDialogConfirm;
import com.wls.jzgy.persions.HelpManualActivity;
import com.wls.jzgy.persions.PhoneActivity;
import com.wls.jzgy.persions.UserInfoActivity;

import java.util.Calendar;
import java.util.Date;

/**
 * 个人中心-主界面
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private View mView;   //布局
    private LinearLayout mMeItem;
//    private LinearLayout mChangeItem;

    private LinearLayout mHelpItem;
    private LinearLayout mShouQuan;
    private LinearLayout mContactItem;
    private LinearLayout mVersionItem;
    private LinearLayout mExitItem;

    private static final String TAG = "MeFragment";

    private TextView title_bar_title;

    private int num;

    private AlertDialogConfirm dialogConfirm;

    private boolean sd = false;
    private Date date2;
    private String date3;
    private String dtm;
    private String dtm1;
    private int js1, js2;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = this.getActivity().getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.remove("sd");
        editor.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局
        mView = inflater.inflate(R.layout.fragment_me, container, false);

        Log.e(TAG, "程序进入了---MeFragment-------》");
        initView();
        return mView;
    }

    /**
     * 初始化控件信息
     */
    private void initView() {

        title_bar_title = mView.findViewById(R.id.tv_title_bar_title1);
        title_bar_title.setText("个人中心");
//        back = mView.findViewById(R.id.iv_back);

        mMeItem = mView.findViewById(R.id.ll_me);            //个人资料
        mHelpItem = mView.findViewById(R.id.ll_help);      //帮助手册
//        mChangeItem = mView.findViewById(R.id.ll_change);    //修改密码
        mShouQuan = mView.findViewById(R.id.ll_shouquan);    //远程控制授权
        mContactItem = mView.findViewById(R.id.ll_contact);  //联系客服
        mVersionItem = mView.findViewById(R.id.ll_version);  //版本号
        mExitItem = mView.findViewById(R.id.ll_exit);        //退出应用
//        mTestItem = mView.findViewById(R.id.ll_test);      //系统测试

//        text = mView.findViewById(R.id.edit_pwd);

        mShouQuan.setVisibility(View.GONE);

        mMeItem.setOnClickListener(this);
//        mChangeItem.setOnClickListener(this);
        mHelpItem.setOnClickListener(this);
        mShouQuan.setOnClickListener(this);
        mContactItem.setOnClickListener(this);
        mVersionItem.setOnClickListener(this);
        mExitItem.setOnClickListener(this);
//        mTestItem.setOnClickListener(this);
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

                Calendar calendar = Calendar.getInstance();
                //获取系统的日期
                //年
                int year = calendar.get(Calendar.YEAR);
                //月
                int month = calendar.get(Calendar.MONTH)+1;
                //日
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                js2 = (year + month + day + day * 1000 - 1000) * (day + 1818) + day;

                dtm = String.valueOf(js2);
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
//                    Toast.makeText(getActivity(), "点击了系统测试", Toast.LENGTH_SHORT).show();
                    mShouQuan.setVisibility(View.VISIBLE);
                    num = 0;
                }
//                Log.e(TAG, "当前系统版本号为——————————》》》" + getVersion());
                Toast.makeText(getActivity(), "当前系统版本号为:" + getVersion(), Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), SystimeActivity.class));
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

    /**

     * 获取版本号

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
        editor.commit();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
