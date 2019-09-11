package com.wls.jzgy.temp.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wls.jzgy.R;
import com.wls.jzgy.temp.adaptes.CheckInfoMainPagerAdapter;
import com.wls.jzgy.temp.domain.UpdateTimeData;
import com.wls.jzgy.widget.DateChooseWheelViewDialog;

import org.greenrobot.eventbus.EventBus;

public class CheckInfoMainActivity extends FragmentActivity {
    private final static String TAG = "CheckInfoMainActivity1";
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private CheckInfoMainPagerAdapter mAdapter;
    private ImageView imageView;
    private LinearLayout lin_back;

    public final static String UPDATE_TIME_DATA = "updateTimeData";
    private String mTime = String.valueOf(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_info_main);
        initView();
    }

    private void initView()     {
        imageView = findViewById(R.id.iv_choosedate);
        mViewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        lin_back = findViewById(R.id.lin_back2);

        tabLayout.setupWithViewPager(mViewPager);//关联tab和viewPager
        mViewPager.setOffscreenPageLimit(7);//初始化viewPager 页数
        mAdapter = new CheckInfoMainPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        imageView.setOnClickListener(v -> {
            DateChooseWheelViewDialog startDateChooseDialog = new DateChooseWheelViewDialog(this,
                    (time, longTimeChecked) -> mTime = time, handler);
            startDateChooseDialog.setTimePickerGone(true);
            startDateChooseDialog.setDateDialogTitle("选择查询日期");
            startDateChooseDialog.showDateChooseDialog();
        });

        lin_back.setOnClickListener(view -> {
            this.finish();
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                EventBus.getDefault().post(new UpdateTimeData(UPDATE_TIME_DATA, mTime));
            }
        }
    };
}
