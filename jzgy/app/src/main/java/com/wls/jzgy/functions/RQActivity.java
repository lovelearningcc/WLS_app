package com.wls.jzgy.functions;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wls.jzgy.R;
import com.wls.jzgy.adapter.QuaryDataAdapter1;
import com.wls.jzgy.entity.UserData;
import com.wls.jzgy.widget.CustomDatePicker;
import com.wls.jzgy.widget.MyListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RQActivity extends Activity implements View.OnClickListener {

    //gcc_add
    private List<View> listViews;
    private ImageView cursorIv;
    private TextView tab01, tab02, tab03;
    //zy_and
    private TextView tab04, tab05, tab06;
    private SharedPreferences.Editor editor;
    private static final int REFRESH_LRARN = 0X111;
    int z;
    int y1 = 2;
    int x;
    int y = 2;

    //上拉刷新数据


    private float fromX;
    // 下划线移动完毕后的位置
    private float toX;
    //end
    private TextView[] titles;
    private ViewPager viewPager;
    private MyListView[] listData;
    /**
     * 偏移量（手机屏幕宽度 / 选项卡总数 - 选项卡长度） / 2
     */
    private int offset = 0;

    /**
     * 下划线图片宽度
     */
    private int lineWidth = 30;

    /**
     * 当前选项卡的位置
     */
    private int current_index = 0;
    private static int TAB_COUNT = 6;
    //private static final int TAB_COUNT = 8;

    private static final int TAB_0 = 0;

    private static final int TAB_1 = 1;

    private static final int TAB_2 = 2;
    //zy_and
    private static final int TAB_3 = 3;
    private static final int TAB_4 = 4;
    private static final int TAB_5 = 5;


    //end
    private int one, pos = 0;
    private List <UserData> lists = new ArrayList<>();
    private String userid, startTime;
    private ProgressBar pb_load;
    private LinearLayout[] ll_no_data;
    private QuaryDataAdapter1 adapter;
    private SharedPreferences sp;

    //gcc_end

//    private static final String[] name = {"工作模式", "运行状态", "报警状态", "设备操作", "实时数据"};

    private TextView title_bar_title1;
    private ImageView back1;
//    private ArrayAdapter <CharSequence> adapter;
//    private Spinner spinner;
    //gcc_add
    private LinearLayout selectDate1, selectDate2;
    private TextView startdata, enddate;
    private CustomDatePicker customDatePicker1, customDatePicker2;
    private static int START_YEAR = 1990, END_YEAR = 2100;
    //gcc_end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.records_query1);
        setContentView(R.layout.quary_info1);

        initUI();
        initDatePicker();
        initImageView();
        initVPager();

//        back1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    private void initUI() {
        //add
        viewPager = (ViewPager) findViewById(R.id.vPager);
        cursorIv = (ImageView) findViewById(R.id.iv_tab_bottom_img);
        tab01 = (TextView) findViewById(R.id.tv_date);
        tab01.setTextColor(Color.parseColor("#2a96c5"));
        tab02 = (TextView) findViewById(R.id.tv_week);
        tab03 = (TextView) findViewById(R.id.tv_month);
        //zy_and
        tab04 = (TextView) findViewById(R.id.tv_lastweek);
        tab05 = (TextView) findViewById(R.id.tv_intervenetime);
        tab06 = (TextView) findViewById(R.id.tv_rundetail);

        pb_load = (ProgressBar) findViewById(R.id.pb_load);
        //end
        title_bar_title1 = findViewById(R.id.tv_title_bar_title1);
        title_bar_title1.setText("记录查询");
//        back1 = findViewById(R.id.iv_back1);
        selectDate1 = (LinearLayout) findViewById(R.id.selectDate1);
        selectDate2 = (LinearLayout) findViewById(R.id.selectDate2);
        selectDate1.setOnClickListener(this);
        selectDate2.setOnClickListener(this);
        startdata = (TextView) findViewById(R.id.startDate);
        enddate = findViewById(R.id.endDate);
        }

        //gcc_add

    /**
     * 初始化底部下划线
     */
    private void initImageView() {
        // Android提供的DisplayMetrics可以很方便的获取屏幕分辨率
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels; // 获取分辨率宽度
        offset = (screenW / TAB_COUNT - lineWidth) / 2;  // 计算偏移值
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cursorIv.getLayoutParams();
        //获取当前控件的布局对象
        params.width = screenW / 6;//设置当前控件布局的高度

        cursorIv.setLayoutParams(params);
        // 设置下划线初始位置
        cursorIv.setImageMatrix(matrix);
    }

    /**
     * 初始化ViewPager并添加监听事件
     */
    private void initVPager() {
        listViews = new ArrayList <>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        //zy_and
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        //end
        ll_no_data = new LinearLayout[]{(LinearLayout) listViews.get(0).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(1).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(2).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(3).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(4).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(5).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(6).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(7).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(8).findViewById(R.id.ll_no_data)};
        listData = new MyListView[]{(MyListView) listViews.get(0).findViewById(R.id.list_data), (MyListView) listViews.get(1).findViewById(R.id.list_data), (MyListView) listViews.get(2).findViewById(R.id.list_data), (MyListView) listViews.get(3).findViewById(R.id.list_data), (MyListView) listViews.get(4).findViewById(R.id.list_data), (MyListView) listViews.get(5).findViewById(R.id.list_data), (MyListView) listViews.get(6).findViewById(R.id.list_data), (MyListView) listViews.get(7).findViewById(R.id.list_data), (MyListView) listViews.get(8).findViewById(R.id.list_data)};
        viewPager.setAdapter(new MyPagerAdapter(listViews));
        viewPager.setCurrentItem(0);
        titles = new TextView[]{tab01, tab02, tab03, tab04, tab05, tab06};
        viewPager.setOffscreenPageLimit(titles.length);
        one = offset * 2 + lineWidth;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                animation(position);
                pos = position;
                pb_load.setVisibility(View.VISIBLE);
                lists.clear();
                //                getUserData(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    /**
     * ViewPager适配器
     */
    public class MyPagerAdapter extends PagerAdapter {

        public List <View> mListViews;

        public MyPagerAdapter(List <View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private void animation(int position) {
        // 下划线开始移动前的位置
        fromX = one * current_index;
        // 下划线移动完毕后的位置
        toX = one * position;
        Animation animation = new TranslateAnimation(fromX, toX, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(500);
        // 给图片添加动画
        cursorIv.startAnimation(animation);
        // 当前Tab的字体变成蓝色
        titles[position].setTextColor(getResources().getColor(R.color.colorAccent));
        titles[current_index].setTextColor(getResources().getColor(R.color.text_color_6));
        current_index = position;
    }

    //gcc_end

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectDate1:
                // 日期格式为yyyy-MM-dd
                customDatePicker1.show(startdata.getText().toString());
                break;

            case R.id.selectDate2:
                // 日期格式为yyyy-MM-dd HH:mm
                customDatePicker2.show(enddate.getText().toString());
                break;

                //gcc_add
            case R.id.tv_date:
                // 避免重复加载
                if (viewPager.getCurrentItem() != TAB_0) {
                    viewPager.setCurrentItem(TAB_0);
                    animation(0);
                    tab01.setTextColor(Color.parseColor("#2a96c5"));
                }
                break;
            case R.id.tv_week:
                if (viewPager.getCurrentItem() != TAB_1) {
                    viewPager.setCurrentItem(TAB_1);
                    animation(1);

                    tab02.setTextColor(Color.parseColor("#2a96c5"));
                }
                break;
            case R.id.tv_month:
                if (viewPager.getCurrentItem() != TAB_2) {
                    viewPager.setCurrentItem(TAB_2);
                    animation(2);

                    tab03.setTextColor(Color.parseColor("#2a96c5"));
                }
                break;
            //zy_and
            case R.id.tv_lastweek:
                if (viewPager.getCurrentItem() != TAB_3) {
                    viewPager.setCurrentItem(TAB_3);
                    animation(3);

                    tab04.setTextColor(Color.parseColor("#2a96c5"));
                }
                break;
            case R.id.tv_intervenetime:
                if (viewPager.getCurrentItem() != TAB_4) {
                    viewPager.setCurrentItem(TAB_4);
                    animation(4);

                    tab05.setTextColor(Color.parseColor("#2a96c5"));
                }
                break;
            case R.id.tv_rundetail:
                if (viewPager.getCurrentItem() != TAB_5) {
                    viewPager.setCurrentItem(TAB_5);
                    animation(5);

                    tab06.setTextColor(Color.parseColor("#2a96c5"));
                }
                break;

            case R.id.lin_back:
            case R.id.iv_back:
                this.finish();
                break;
            //gcc_end
        }
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        startdata.setText(now.split(" ")[0]);
        enddate.setText(now.split(" ")[0]);
        //        currentTime.setText(now);
        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                startdata.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动


        customDatePicker2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                enddate.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(true); // 显示时和分
        customDatePicker2.setIsLoop(true); // 允许循环滚动
    }
}
