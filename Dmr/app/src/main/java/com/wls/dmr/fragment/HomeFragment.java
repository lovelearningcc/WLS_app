package com.wls.dmr.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wls.dmr.R;
import com.wls.dmr.entity.Contants;
import com.wls.dmr.entity.UserData;
import com.wls.dmr.entity.ZhiShui;
import com.wls.dmr.http.OkHttpUtils3;
import com.wls.dmr.http.ReqCallBack;
import com.wls.dmr.utils.WebUrls;
import com.wls.dmr.widget.MyListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author：chenjr .
 * UpdateTime：2018-06-19
 * Version：v1.0 查询数据
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private List<View> listViews;
    private ImageView cursorIv;
    private TextView tab01, tab02;
    private int IsTab;
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
    private int lineWidth = 180;

    /**
     * 当前选项卡的位置
     */
    private int current_index = 0;
    private static final int TAB_COUNT = 2;

    private static final int TAB_0 = 0;

    private static final int TAB_1 = 1;

    private int one;
    private List<UserData> lists = new ArrayList<>();
    private LinearLayout[] ll_no_data;
    private QuaryDataAdapter adapter;

    private String myuid;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private static final String TAG = "HomeFragment";

    private TextView title_bar_title;


    private String hospital;
    private String province;
    private String city;
    private String num;
    private JSONObject jsonObject;
    private int num1 = 0;
    private View hview;//根布局

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = this.getActivity().getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();
        myuid = sp.getString("uid", "");
        Log.e(TAG, "66666666666666666666666" + myuid);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        hview = inflater.inflate(R.layout.quary_info2, container, false);

        initUI();
        initImageView();
        initVPager();

        getUserData7(0); //获取在线离线设备列表
        return hview;
    }

    public void saveAccount111() {

        editor.putString("province", province); //省
        editor.putString("city", city);  //市
        editor.putString("hospital", hospital); //医院
        editor.putString("num", num); //设备总数
        editor.commit();
    }

    /**
     * 初始化布局和监听
     */
    private void initUI() {
        viewPager = (ViewPager) hview.findViewById(R.id.vPager);
        cursorIv = (ImageView) hview.findViewById(R.id.iv_tab_bottom_img);
        tab01 = (TextView) hview.findViewById(R.id.tv_ondev);
        tab02 = (TextView) hview.findViewById(R.id.tv_offdev);

        tab01.setOnClickListener(this);
        tab02.setOnClickListener(this);

        title_bar_title = hview.findViewById(R.id.tv_title_bar_title1);
        title_bar_title.setText("设备列表");
    }

    /**
     * 初始化底部下划线
     */
    private void initImageView() {
        // Android提供的DisplayMetrics可以很方便的获取屏幕分辨率
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels; // 获取分辨率宽度
        offset = (screenW / TAB_COUNT - lineWidth) / 2;  // 计算偏移值
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cursorIv.getLayoutParams();
        //获取当前控件的布局对象
        params.width = screenW / 2;//设置当前控件布局的高度
        cursorIv.setLayoutParams(params);
        // 设置下划线初始位置
        cursorIv.setImageMatrix(matrix);
    }

    /**
     * 初始化ViewPager并添加监听事件
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initVPager() {
        listViews = new ArrayList<>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
//        listViews.add(mInflater.inflate(R.layout.quary_info_list, null));
        ll_no_data = new LinearLayout[]{(LinearLayout) listViews.get(0).findViewById(R.id.ll_no_data), (LinearLayout) listViews.get(1).findViewById(R.id.ll_no_data)};
        listData = new MyListView[]{(MyListView) listViews.get(0).findViewById(R.id.list_data), (MyListView) listViews.get(1).findViewById(R.id.list_data)};
        viewPager.setAdapter(new MyPagerAdapter(listViews));
        viewPager.setCurrentItem(0);
        titles = new TextView[]{tab01, tab02};
        viewPager.setOffscreenPageLimit(titles.length);
        one = offset * 2 + lineWidth;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                animation(position);
                getUserData7(position);
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

        public List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
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
        float fromX = one * current_index;
        // 下划线移动完毕后的位置
        float toX = one * position;
        Animation animation = new TranslateAnimation(fromX, toX, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(500);
        // 给图片添加动画
        cursorIv.startAnimation(animation);
        // 当前Tab的字体变成深白色
        titles[position].setTextColor(getResources().getColor(R.color.black));
        titles[current_index].setTextColor(getResources().getColor(R.color.black));
        current_index = position;
    }

    //当选中的时候变色,改变底部文字颜色
    public void setSelected(TextView textView) {

        tab02.setSelected(false);
        textView.setSelected(true);
    }
    public void setSelected1(TextView textView) {
        tab01.setSelected(false);

        textView.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ondev:
                // 避免重复加载
                if (viewPager.getCurrentItem() != TAB_0) {
                    viewPager.setCurrentItem(TAB_0);
                    animation(0);
                }
                break;
            case R.id.tv_offdev:
                if (viewPager.getCurrentItem() != TAB_1) {
                    viewPager.setCurrentItem(TAB_1);
//                    lists.clear();
                    animation(1);
                }
                break;
        }
    }


    public void getUserData7(int flag) {  //获取在线、离线设备

        Log.d(TAG, "进入了getUserData7方法------------>");
        String url = "";


        ZhiShui zhiShui = new ZhiShui();

        zhiShui.setDate(String.valueOf(new Date().getTime()));
        zhiShui.setUid(myuid);
        zhiShui.setEtypecode(Contants.ETYPECODE_SJ);
//        String json = JSON.toJSONString(zhiShui);
        Gson gson = new Gson();
        String obj2 = gson.toJson(zhiShui);

        if (0 == flag) {
            url = WebUrls.getdeviceonlineurl;
        } else if (1 == flag) {
            url = WebUrls.getdeviceofflineurl;
        }

        Log.e(TAG, "在线离线设备请求参数----------》" + String.valueOf(new Date().getTime()));
        Log.e(TAG, "在线离线设备请求参数----------》" + myuid);
        Log.e(TAG, "在线离线设备请求参数----------》" + Contants.ETYPECODE_SJ);
//        Log.e(TAG, "在线离线设备请求参数----------》" + json);
        Log.e(TAG, "在线离线设备请求参数----------》" + obj2);

        OkHttpUtils3 okHttpUtils3 = new OkHttpUtils3(getActivity());
        okHttpUtils3.requestAsyn(url, OkHttpUtils3.TYPE_POST_JSON, obj2, new ReqCallBack<String>() {

            @Override
            public void onReqSuccess(String result) {
                Log.e(TAG, "在线离线设备请求参数请求成功----------》" + result);

                //上传日志回调，若成功则清空表

//                Log.d("WLS", "onResponse回调结果------------>" + result);
                if (result.endsWith("null") || result.endsWith("[]")) {
                    ll_no_data[flag].setVisibility(View.VISIBLE);
                    return;
                }
                ll_no_data[flag].setVisibility(View.INVISIBLE);

                try {
                    //gcc_add
                    JSONObject re = new JSONObject(result);  //解析JSON字符串
                    String code = re.getString("code");
                    switch (code) {
                        case "0":
                            Log.d("WLS：", "onResponse回调结果------------>" + code);
//                            msg = re.getString("msg");

                            //gcc_add_2019.4.19
                            JSONObject dataobject = re.getJSONObject("data");

                            String onlineNum = dataobject.getString("num");
                            num = dataobject.getString("num");

                            Log.e(TAG, "9999999999999999999999     " + onlineNum);
                            Log.e(TAG, "9999999999999999999999     " + num);

                            if (num.equals("0")) {
                                Toast.makeText(getActivity(), "暂无在线设备", Toast.LENGTH_LONG).show();

                            } else {

                                JSONArray jsonArray = dataobject.getJSONArray("equipmentList");

                                int jsonArraySize = jsonArray.length();//获取数组长度
                                lists.clear();
                                num1 = 0;
                                for (int i = 0; i < jsonArraySize; i++) {

                                    num1++;
                                    jsonObject = (JSONObject) jsonArray.getJSONObject(i);
                                    UserData userData = new UserData();

                                    province = jsonObject.getString("province");
                                    city = jsonObject.getString("city");
                                    hospital = jsonObject.getString("hospital");
//                                    eid = jsonObject.getString("eid");


                                    userData.setEid(jsonObject.getString("eid"));
                                    userData.setType(jsonObject.getString("type"));
                                    userData.setProvince(jsonObject.getString("province"));
                                    userData.setCity(jsonObject.getString("city"));
                                    userData.setNum(String.valueOf(num1));

                                    lists.add(userData);
                                }

                                saveAccount111();
                                adapter = new QuaryDataAdapter(getActivity(), lists, flag);
                                listData[flag].setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {
                Toast.makeText(getActivity(), "在线、离线设备数据请求失败，请检查网络！" + errorMsg, Toast.LENGTH_SHORT).show();
                ll_no_data[flag].setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("疾风剑豪2：", "onDestroy被调用了------------>");

    }
}























