package com.wls.dmr.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.wls.dmr.R;

/**
 * Author：chenjr .
 * UpdateTime：2018/6/20
 * Version：v1.0
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener{

    //zy_and   listview上拉刷新
    private boolean isScrollToBottom;//判断是不是滑到了底部
    private int footerViewHeight; //底部view的高度
    private boolean isLoadingMore = false; //判断是不是"加载更多"
    private View footerView; //底部的footer   view

    /**
     * listview的接口，监听listview的下来刷新和上拉加载更多
     */
    private OnRefreshListener mOnRefreshListener;
    //end

    // 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //zy_and 初始化底部view
        initFooterView();

        this.setOnScrollListener(this);
        //end
    }
    /**
     * zy_and 初始化底部view
     */
    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.my_footer_layout, null);
        footerView.measure(0, 0);    //设置（0，0）以便系统测量footerView的宽高
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);

        this.addFooterView(footerView);
    }
    //end

    //zy_and
    /**
     * 监听listview滚动的状态变化，如果滑到了底部，就“加载更多..."
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                footerView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());

                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onLoadingMore();
                }
            }
        }
    }

    /**
     * 监听listview滚动的状态变化，判断当前是不是滑到了底部
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }
    }

    /**
     * 设置监听接口，当为
     * @param listener
     */
//    public void setOnRefreshListener(OnRefreshListener listener) {
//        mOnRefreshListener = listener;
//    }

    /**
     * 为外界提供的方法，当Activity中的加载
     * 更多数据加载完后，就调用这个方法来隐藏底部的footerView
     */
//    public void loadMoreComplete() {
//        footerView.setPadding(0, -footerViewHeight, 0, 0);
//        isLoadingMore = false;
//    }

    /**
     * 设置接口，供外界实现，监听listview的刷新和加载更多的状态
     */
    public interface OnRefreshListener {
        //上拉加载更多
        void onLoadingMore();
    }
    //end


    public MyListView(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                Log.d("TAG","=====ACTION_MOVE");
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false;   //表示向下传递事件
                }
                break;
                case MotionEvent.ACTION_SCROLL:
                    Log.d("TAG","=====ACTION_SCROLL");
                    break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
