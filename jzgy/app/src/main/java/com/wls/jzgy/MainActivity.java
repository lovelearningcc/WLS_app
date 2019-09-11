package com.wls.jzgy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wls.jzgy.activity.BaseFragmentActivity;

import com.wls.jzgy.fragment.FunctionFragment;
import com.wls.jzgy.fragment.HomeFragment;
import com.wls.jzgy.fragment.MeFragment;
import com.wls.jzgy.utils.SharePreUtil;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    private ViewPager viewPager_content;
    private TextView menu_bottom_function;     //功能列表
    private TextView menu_bottom_home;         //首页
    private TextView menu_bottom_me;           //个人中心

    private final int TAB_FUNC = 0;        //功能列表
    private final int TAB_HOME = 1;          //首页
    private final int TAB_ME = 2;
    private int IsTab;

    private FunctionFragment functionFragment; //功能列表
    private HomeFragment homeFragment;  //首页
    private MeFragment meFragment; //个人中心

    private FragmentAdapter adapter;


    private String userid;//用户id
    private Boolean isLoad;//是否登录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initID();//初始化绑定组件id
        initView();//初始化视图
    }

    /**
     * 初始化控件加载
     */
    public void initID() {

        viewPager_content = findViewById(R.id.viewPager_content);
        menu_bottom_home = findViewById(R.id.tv_menu_bottom_home);
        menu_bottom_function = findViewById(R.id.tv_menu_bottom_function);
        menu_bottom_me = findViewById(R.id.tv_menu_bottom_me);
        menu_bottom_home.setOnClickListener(this);
        menu_bottom_function.setOnClickListener(this);
        menu_bottom_me.setOnClickListener(this);

        //ViewPager滑动监听,切换界面
        viewPager_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("main_viewpager", "position--" + position);
                switch (position) {
                    case TAB_FUNC://点击功能模块执行
                        jumpFuncFragment();
                        IsTab = 1;
                        break;

                    case TAB_HOME://点击首页模块执行
                        IsTab = 2;
                        jumpHomeFragment();
                        break;
                    case TAB_ME://点击个人中心模块执行
                        IsTab = 3;
                        jumpMeFragment();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    /**
     * 初始化视图,默认显示首界面
     */
    public void initView() {
        isLoad = false;

        functionFragment = new FunctionFragment();
        homeFragment = new HomeFragment();
        meFragment = new MeFragment();
        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager_content.setAdapter(adapter);
        setSelected(menu_bottom_home);
        viewPager_content.setCurrentItem(TAB_HOME, false);
        setTitleName("首页");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_menu_bottom_function://点击功能模块执行
                IsTab = 1;
                setSelected(menu_bottom_function);
                viewPager_content.setCurrentItem(TAB_FUNC, false);
                setTitleName("功能列表");
                break;

            case R.id.tv_menu_bottom_home://点击首页模块执行
                IsTab = 2;
                setSelected(menu_bottom_home);
                viewPager_content.setCurrentItem(TAB_HOME, false);
                setTitleName("首页");
                break;
            case R.id.tv_menu_bottom_me://点击个人中心模块执行
                IsTab = 3;
                setSelected(menu_bottom_me);
                viewPager_content.setCurrentItem(TAB_ME, false);
                setTitleName("个人中心");
                break;

            default:
                break;
        }
    }

    //当选中的时候变色,改变底部文字颜色
    public void setSelected(TextView textView) {
        menu_bottom_home.setSelected(false);
        menu_bottom_function.setSelected(false);
        menu_bottom_me.setSelected(false);
        textView.setSelected(true);
    }

    /*
     * 模块Fragment适配器
     */
    public class FragmentAdapter extends FragmentPagerAdapter {
        private final int TAB_COUNT = 3;

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int id) {
            switch (id) {

                case TAB_FUNC:
                    return functionFragment;
                case TAB_HOME:
                    return homeFragment;
                case TAB_ME:
                    return meFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharePreUtil.SetShareString(mContext, "userid", "");//Activity死亡清空id保存
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听返回键，如果当前界面不是首界面，或没切换过界面，切到首界面
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (IsTab != 1) {
                IsTab = 1;
                jumpHomeFragment();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 显示主界面Fragemnt
     */
    private void jumpHomeFragment() {
        setSelected(menu_bottom_home);
        viewPager_content.setCurrentItem(TAB_HOME, false);
        setTitleName("首页");
    }

    /**
     * 切换巡店Fragment,提供给HomeFragment的培训查看调用
     */
    public void jumpFuncFragment() {
        setSelected(menu_bottom_function);
        viewPager_content.setCurrentItem(TAB_FUNC, false);
        setTitleName("功能列表");
    }
    /**
     * 显示拜访VisitFragment,提供给新建拜访完成后调用
     */
    public void jumpMeFragment() {
        setSelected(menu_bottom_me);
        viewPager_content.setCurrentItem(TAB_ME, false);
        setTitleName("个人中心");
    }
}