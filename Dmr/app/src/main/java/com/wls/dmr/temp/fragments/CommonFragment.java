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

import com.google.gson.Gson;
import com.wls.dmr.R;
import com.wls.dmr.temp.adaptes.CheckInfoMainPagerAdapter;
import com.wls.dmr.temp.adaptes.CommonFragmentAdapter;
import com.wls.dmr.temp.domain.CommonFragData;
import com.wls.dmr.temp.domain.UpdateTimeData;
import com.wls.dmr.temp.utils.SpUtils;
import com.wls.dmr.temp.utils.UrlUtils;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.wls.dmr.temp.activitys.CheckInfoMainActivity.UPDATE_TIME_DATA;
import static com.wls.dmr.temp.utils.UrlUtils.URL_CHECK_USER;
import static com.wls.dmr.temp.utils.UrlUtils.URL_DAY;
import static com.wls.dmr.temp.utils.UrlUtils.URL_LASE_WEEK;
import static com.wls.dmr.temp.utils.UrlUtils.URL_MONTH;
import static com.wls.dmr.temp.utils.UrlUtils.URL_PAY_MENT;
import static com.wls.dmr.temp.utils.UrlUtils.URL_THIS_WEEK;


/**
 * Created by WKC on 2017/7/30.
 */

public class CommonFragment extends Fragment {
    private static final String TAG = "CommonFragment";
    private String urlTag = "";
    private int pageCount = 1;//当前页
    private View mainView = null;

    private LinearLayout llNoData;
    private Context mContext;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private CommonFragmentAdapter mRecyclerViewAdapter = new CommonFragmentAdapter();
    private ArrayList<CommonFragData> dataArrayList = new ArrayList<>();
    private int total = 0;
    private Timer timer = new Timer();
    private String mTime = String.valueOf(System.currentTimeMillis());

    private OkHttpClient mOkHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
            .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
            .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
            .build();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mainView == null) {

            mainView = inflater.inflate(R.layout.frag_display_data_common, container, false);
            Log.e("!!!!!!!!!!!", "144441414144114141144141》》》");
            initLocalData();
            initView();
            initNetData();
        }
        return mainView;
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
                initNetData();
            }

            @Override
            public void onLoadMore() {
                if (dataArrayList.size() < total) {
                    pageCount++;
                    initNetData();
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
     * 需要初始化的一些本地数据
     */
    private void initLocalData() {
        mContext = getActivity();
        EventBus.getDefault().register(this);
        if (getArguments().getString(CheckInfoMainPagerAdapter.URL_TAG) != null) {
            urlTag = getArguments().getString(CheckInfoMainPagerAdapter.URL_TAG);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 网络数据
     */
    private void initNetData() {

        Log.e("!!!!!!!!!!!", "9999999999999999999》》》");
        if (urlTag.length() < 2) {
            Toast.makeText(mContext, "数据出错，请重试", Toast.LENGTH_SHORT).show();
            llNoData.setVisibility(View.VISIBLE);
            return;
        }

        llNoData.setVisibility(View.VISIBLE);
        String url = UrlUtils.getCommonUrl(urlTag);
        Log.i(TAG, "url:" + url);
        RequestBody formBody = getRequestBodyData();
        Request request = new Request.Builder().url(url).post(formBody).build();
        Call mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "访问网络出错:" + url);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (result.endsWith("null") || result.endsWith("[]")) {
                        //在ui线程中更新ui
                        getActivity().runOnUiThread(() -> {
                                    //刷新结束
                                    mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                                    llNoData.setVisibility(View.VISIBLE);
                                }
                        );
                    } else {
                        try {
                            if (!TextUtils.isEmpty(result)) {
                                JSONArray array = new JSONArray(result);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = new JSONObject(array.get(i).toString());
                                    CommonFragData mData = new CommonFragData();
                                    total = object.getInt("total");
                                    mData.setTotal(total);
                                    mData.setData(object.getInt("data"));
                                    if (urlTag.equals(URL_MONTH) || urlTag.equals(URL_PAY_MENT) || urlTag.equals(URL_CHECK_USER)) {
                                        mData.setUserId(object.getInt("userid"));
                                    } else {
                                        mData.setUserId(object.getInt("UserId"));
                                    }
                                    mData.setTime(object.getString("time"));
                                    mData.setUrlTag(urlTag);
                                    dataArrayList.add(mData);
                                }
                                Log.i(TAG, "url:" + url + "dataArrayList ----->" + new Gson().toJson(dataArrayList));
                                getActivity().runOnUiThread(() -> {
                                            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                                            llNoData.setVisibility(View.GONE);
                                            mRecyclerViewAdapter.setData(dataArrayList);
                                        }
                                );
                            } else {
                                Log.i(TAG, "url:" + url + "---object -----> no data2");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.i(TAG, "访问响应出错:" + url);
                }
            }
        });
    }

    /**
     * 根据不同情况返回不同数据
     */
    private RequestBody getRequestBodyData() {
        String userId = SpUtils.getUserInfoId(mContext);
        RequestBody formBody;
        if (urlTag.equals(URL_DAY)) {
            formBody = new FormBody.Builder()
                    .add("userId", userId)
                    .add("startTime", mTime)
                    .add("accessm", "app")
                    .add("searchuid", "searchuid")
                    .add("endTime", mTime)
                    .add("showNumber", "20")
                    .add("pageNumber", pageCount + "")
                    .build();
        } else if (urlTag.equals(URL_MONTH) || urlTag.equals(URL_THIS_WEEK) || urlTag.equals(URL_LASE_WEEK)) {
            formBody = new FormBody.Builder()
                    .add("userId", userId)
                    .add("startTime", mTime)
                    .add("accessm", "app")
                    .add("searchuid", "searchuid")
                    .add("pageNumber", pageCount + "")
                    .build();
        } else {
            formBody = new FormBody.Builder()
                    .add("userId", userId)
                    .add("startTime", mTime)
                    .add("accessm", "app")
                    .add("searchuid", "searchuid")
                    .add("endTime", mTime)
                    .add("showNumber", "20")
                    .add("pageNumber", pageCount + "")
                    .build();
        }
        return formBody;
    }

    @Subscribe()
    public void Events(UpdateTimeData event) {
        Log.i(TAG, "event:" + event);
    }

}
