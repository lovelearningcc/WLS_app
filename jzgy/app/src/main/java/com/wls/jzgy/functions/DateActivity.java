package com.wls.jzgy.functions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wls.jzgy.R;
import com.wls.jzgy.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author：  wls-gcc
 * Date on： 2019\1\7 0007 9:46
 * Version： v1.0
 */
public class DateActivity extends Activity implements View.OnClickListener {

    private LinearLayout selectDate1, selectDate2;
    private TextView startdata, enddate;
    private CustomDatePicker customDatePicker1, customDatePicker2;

    private static int START_YEAR = 1990, END_YEAR = 2100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.records_query1);

//        selectTime = (LinearLayout) findViewById(R.id.selectTime);
//        selectTime.setOnClickListener(this);

        selectDate1 = (LinearLayout) findViewById(R.id.selectDate1);
        selectDate2 = (LinearLayout) findViewById(R.id.selectDate2);
        selectDate1.setOnClickListener(this);
        selectDate2.setOnClickListener(this);
        startdata = (TextView) findViewById(R.id.startDate);
        enddate = findViewById(R.id.endDate);

//        currentTime = (TextView) findViewById(R.id.currentTime);

        initDatePicker();
    }

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
