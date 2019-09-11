package com.wls.dmr.temp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wls.dmr.R;
import com.wls.dmr.http.NewRequestManager;
import com.wls.dmr.http.ReqCallBack;
import com.wls.dmr.temp.adaptes.CheckInfoMainPagerAdapter;
import com.wls.dmr.temp.adaptes.SensorFragmentAdapter;
import com.wls.dmr.temp.domain.SensorFragData;
import com.wls.dmr.temp.domain.UpdateTimeData;
import com.wls.dmr.temp.utils.SpUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import static com.wls.dmr.temp.activitys.CheckInfoMainActivity.UPDATE_TIME_DATA;


/**
 * Created by WKC on 2017/7/30.
 */

public class SensorFragment extends Fragment {
    private final String TAG = "SensorFragment1";
    private View mainView = null;
    private Context mContext;
    private String urlTag = "";
    private int pageCount = 1;//当前页
    private LinearLayout llNoData;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private SensorFragmentAdapter mRecyclerViewAdapter = new SensorFragmentAdapter();
    private ArrayList<SensorFragData> dataArrayList = new ArrayList<>();
    private int total = 0;
    private Timer timer = new Timer();
    private String mTime = String.valueOf(System.currentTimeMillis());


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.frag_display_data_sensor, container, false);
            initLocalData();
            initView();
//            initNetData();
        }
        return mainView;
    }


    /**
     * 需要初始化的一些本地数据
     */
    private void initLocalData() {
        mContext = getActivity();
        EventBus.getDefault().register(this);
        if (getArguments().getString(CheckInfoMainPagerAdapter.URL_TAG) != null) {
            urlTag = getArguments().getString(CheckInfoMainPagerAdapter.URL_TAG);
        }
    }
    /**
     * 初始化view
     */
    private void initView() {
        llNoData = mainView.findViewById(R.id.ll_no_data);
        mPullLoadMoreRecyclerView = mainView.findViewById(R.id.pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);

        /**
         * 下拉刷新和上拉加载
         */
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                pageCount = 1;
                dataArrayList.clear();
//                initNetData();
            }

            @Override
            public void onLoadMore() {
                if (dataArrayList.size() < total) {
                    pageCount++;
//                    initNetData();
                } else {
                    noMore();
                }
            }
        });
        //上拉加载数据提水
        mPullLoadMoreRecyclerView.setFooterViewText("加载更多");
    }


    private void noMore() {
        timer.schedule(new TimerTask() {
            public void run() {
                getActivity().runOnUiThread(() -> {
                            Toast.makeText(mContext, "没有数据了", Toast.LENGTH_SHORT).show();
                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                        }
                );
            }
        }, 500);
    }



    /**
     * 网络数据
     */
    private void initNetData() {
        if (urlTag.length() < 2) {
            Toast.makeText(mContext, "数据出错，请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = "distance/getDistance.shtml.shtml";
//                    String url = "";
        String userId = SpUtils.getUserInfoId(mContext);
        HashMap<String, String> mapDay = new HashMap<>();
        mapDay.put("userid", userId);
        mapDay.put("startTime", mTime);
        //zy:add
        mapDay.put("accessm", "app");
        mapDay.put("searchuid", "searchuid");

        mapDay.put("endTime", mTime);
        mapDay.put("showNumber", String.valueOf(100));
        mapDay.put("pageNumber", pageCount + "");

        NewRequestManager myNewRequestManager = new NewRequestManager(getActivity());
        myNewRequestManager.requestAsyn(url, NewRequestManager.TYPE_POST_JSON, mapDay, new ReqCallBack<String>() {
            @Override
            public void onReqSuccess(String result) {

                getActivity().runOnUiThread(() -> {
                            //刷新结束
                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                        }
                );
                try {
                    if (!TextUtils.isEmpty(result)) {
                        //zy_and
                        JSONObject object = new JSONObject(result);
                        String requestStatus = object.getString("requeststatus");
                        switch (requestStatus) {
                            case "0":
                                String distanceData = object.getString("sendDistanceData");
                                if (distanceData.equals("[]")) {
                                    getActivity().runOnUiThread(() -> {
                                                //刷新结束
                                                llNoData.setVisibility(View.VISIBLE);
                                            }
                                    );
                                    return;
                                } else {
                                    JSONArray array = new JSONArray(distanceData);
//                                    Log.i(TAG, "array:" + new Gson().toJson(array));
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject jb = new JSONObject(array.get(i).toString());
                                        SensorFragData data = new SensorFragData();
                                        total = jb.getInt("total");
                                        data.setTotal(total);
                                        data.setdTime(jb.getLong("dtime"));
                                        data.setDistance(jb.getString("distance"));
                                        data.setUsername(jb.getString("username"));
                                        data.setName(jb.getString("name"));
                                        data.setUserId(jb.getInt("userid"));
                                        dataArrayList.add(data);
                                    }
                                    getActivity().runOnUiThread(() -> {
                                                llNoData.setVisibility(View.GONE);
                                                mRecyclerViewAdapter.setData(dataArrayList);
                                            }
                                    );

                                    break;
                                }
                        }
                        //end
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onReqFailed(String errorMsg) {
//                Toast.makeText(mContext, "请求数据失败66666！" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Subscribe()
    public void Events(UpdateTimeData event) {
        Log.i(TAG, "event:" + event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
