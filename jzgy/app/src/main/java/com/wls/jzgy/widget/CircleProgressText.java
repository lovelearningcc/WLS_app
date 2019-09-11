package com.wls.jzgy.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wls.jzgy.R;

/**
 * Author：chenjr .
 * UpdateTime：2018/6/7
 * Version：v2.0 文本显示进度条
 */
public class CircleProgressText extends RelativeLayout {
    private CircleProgress circleProgress;
    private final TextView tvContent;
    private final TextView tvPercent;
    private final int headColor;
    private final int subHeadColor;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public CircleProgressText(Context context) {
        this(context, null);
    }

    public CircleProgressText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextCircleProgress);
        headColor = ta.getColor(R.styleable.TextCircleProgress_headColor, Color.BLACK);
        subHeadColor = ta.getColor(R.styleable.TextCircleProgress_subHeadColor, Color.GRAY);
        ta.recycle();
        View contentView = View.inflate(context, R.layout.circleprogress_data, this);
        circleProgress = (CircleProgress) contentView.findViewById(R.id.circleProgress);


        tvContent = (TextView) contentView.findViewById(R.id.tv_content);
        tvPercent = (TextView) contentView.findViewById(R.id.tv_percent);

        sp = getContext().getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();

        String qwe = sp.getString("province", ""); //
        String wer = sp.getString("city", ""); //

//        tvContent.setText(qwe);
//
//        tvPercent.setText(wer);


    }
    /**
     * 设置主进度条进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress > 100) {
            progress = 100;
            tvPercent.setVisibility(View.INVISIBLE);
        }else{
            tvPercent.setVisibility(View.VISIBLE);
        }
        circleProgress.setProgress(progress);
    }
    /**
     * 设置内容
     *
     * @param text
     */
    public void setHead(String text) {
        tvContent.setText(text);
    }

    /**
     * 百分比
     *
     */
    public void setSubHead(int percent) {
        tvPercent.setText(percent+"%");
    }
    public void setProgressColor(int start,int end){
        circleProgress.setProColor(start,end);
    }
}
