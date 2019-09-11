package com.wls.jzgy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wls.jzgy.R;

import com.wls.jzgy.functions.MapActivity;
import com.wls.jzgy.temp.activitys.CheckInfoMainActivity;


/**
 * 功能列表
 */

public class FunctionFragment extends Fragment implements XRecyclerView.LoadingListener,
        View.OnClickListener, TextView.OnEditorActionListener {
    private View view;
//    public static List<DateList> info_list;
    private int pagenum = 1;//当前页数


    private LinearLayout dl, map, col, rtd, rc, rq, stcs;

    private TextView title_bar_title;
    private ImageView back;

    private static final String TAG = "FunctionFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_function, container, false);

        Log.e(TAG, "程序进入了---FunctionFragment-------》");
        initView();//初始化视图
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().popBackStack();
//            }
//
//        });
        return view;
    }

    //
//    /**
//     * 初始化控件信息
//     */
    private void initView() {
        title_bar_title = view.findViewById(R.id.tv_title_bar_title1);
        title_bar_title.setText("功能列表");
//        back = view.findViewById(R.id.iv_back);

//        dl = view.findViewById(R.id.layout_devicelist);
        map = view.findViewById(R.id.layout_map);
//        col = view.findViewById(R.id.layout_collect);
//        rtd = view.findViewById(R.id.layout_realtimedata);
        rq = view.findViewById(R.id.layout_recordsquery);
//        stcs = view.findViewById(R.id.ll_test);

//        dl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), CheckInfoActivity.class));
//            }
//        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapActivity.class));
            }
        });

//        col.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), ColActivity.class));
//            }
//        });

//        rtd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                getUserData();
//                startActivity(new Intent(getActivity(), RTDActivity.class));
//            }
//        });

        rq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CheckInfoMainActivity.class));
            }
        });

//        stcs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), JfjhActivity.class));
//            }
//        });
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        pagenum = 1;
    }

    @Override
    public void onLoadMore() {
        //加载更多
//        initData();
    }

//    /**
//     * 结束上下拉刷新
//     */
//    private void onLoad() {
//        recyclerView.refreshComplete();
//        recyclerView.loadMoreComplete();
//    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        /**
         * 当点击搜索按钮时
         */
        return false;
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 清空静态变量
         */
//        info_list = null;
    }
}
