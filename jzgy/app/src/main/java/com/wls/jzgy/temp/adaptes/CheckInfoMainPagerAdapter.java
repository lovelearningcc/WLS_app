package com.wls.jzgy.temp.adaptes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wls.jzgy.temp.fragments.CommonFragment;
import com.wls.jzgy.temp.fragments.SensorFragment;
import com.wls.jzgy.temp.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author kklu
 * @Email W258840818@163.com
 * @Data 2019/1/22 10:58
 * @Description fragment适配器
 */
public class CheckInfoMainPagerAdapter extends FragmentPagerAdapter {

    public static final String URL_TAG = "urlTag";
    private List<String> mData = new ArrayList<>();
    private List<String> urlData = new ArrayList<>();
    private ArrayList<CommonFragment> mFragment = new ArrayList<>();

    private SensorFragment sensorFragment = new SensorFragment();

    public CheckInfoMainPagerAdapter(FragmentManager fm) {
        super(fm);
        initData();
    }



    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(URL_TAG, urlData.get(position));
        if (position == 4) {
            sensorFragment.setArguments(bundle);
            return sensorFragment;
        }
        mFragment.get(position).setArguments(bundle);
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 7; i++) {
            mFragment.add(new CommonFragment());
        }
        mData.add("工作模式");
        mData.add("运行状态");
        mData.add("报警状态");
        mData.add("设备操作");
        mData.add("实时数据");
        mData.add("所有数据");
        mData.add("保留数据");
        urlData.add(UrlUtils.URL_DAY);
        urlData.add(UrlUtils.URL_THIS_WEEK);
        urlData.add(UrlUtils.URL_MONTH);
        urlData.add(UrlUtils.URL_LASE_WEEK);
        urlData.add(UrlUtils.URL_DISTANCE);
        urlData.add(UrlUtils.URL_PAY_MENT);
        urlData.add(UrlUtils.URL_CHECK_USER);
    }

}
