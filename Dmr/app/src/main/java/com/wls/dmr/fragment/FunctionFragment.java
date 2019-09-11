package com.wls.dmr.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wls.dmr.R;

import com.wls.dmr.functions.MapActivity;
import com.wls.dmr.temp.activitys.CheckInfoMainActivity;


/**
 * 功能列表
 */

public class FunctionFragment extends Fragment implements XRecyclerView.LoadingListener,
        View.OnClickListener, TextView.OnEditorActionListener {
    private View view;


    private LinearLayout map;
    private LinearLayout rq;

    private TextView title_bar_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_function, container, false);
        initView();//初始化视图

        return view;
    }

    //    /**
//     * 初始化控件信息
//     */
    private void initView() {
        title_bar_title = view.findViewById(R.id.tv_title_bar_title1);
        title_bar_title.setText("功能列表");
        map = view.findViewById(R.id.layout_map);
        rq = view.findViewById(R.id.layout_recordsquery);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapActivity.class));
            }
        });

        rq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CheckInfoMainActivity.class));
            }
        });

    }

    @Override
    public void onRefresh() {
        //下拉刷新
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
    public void onDestroy() {
        super.onDestroy();
        /**
         * 清空静态变量
         */
    }
}
