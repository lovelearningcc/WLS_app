package com.wls.dmr.persions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wls.dmr.R;
import com.wls.dmr.entity.Contants;
import com.wls.dmr.entity.ZhiShui;
import com.wls.dmr.http.OkHttpUtils3;
import com.wls.dmr.utils.Hex;
import com.wls.dmr.utils.TC;
import com.wls.dmr.utils.WebUrls;


import java.text.DecimalFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SystimeActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "SystimeActivity";
    private static int delay = 0;  //1s
    private static int period = 6000;  //6s
    TC tc2 = new TC();
    private TextView title_bar_title;
    private LinearLayout back;
    private ImageView back1;
    private boolean mstate = true;
    private Button bxg_devtime1;
    private SharedPreferences sp;
    private EditText timen1, timey1, timed1, times1, timef1, timem1;
    private String seid;
    private String sphonenumber;
    private String simei;
    private String suid;
    private String tystr0, tystr1, tystr2, tystr3, tystr4, tystr5;
    private String slstr0, slstr1, slstr2, slstr3, slstr4, slstr5;
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private int count = 100;

    @Override
    protected void onCreate(Bundle savedInstancemmstate) {
        super.onCreate(savedInstancemmstate);
        setContentView(R.layout.systime);

        sp = getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
//        editor = sp.edit();

        seid = sp.getString("eid1", "");
//        stype = sp.getString("type1", "");

        sphonenumber = sp.getString("akm", "");  //手机号
        simei = sp.getString("ump9", "");    //imei
        suid = sp.getString("uid", "");

        init();
        startTimer();

        title_bar_title = findViewById(R.id.tv_title_bar_title);
        back = findViewById(R.id.lin_back);
        back1 = findViewById(R.id.iv_back);
        title_bar_title.setText("设置系统时间");
        back.setOnClickListener(v -> finish());
        back1.setOnClickListener(v -> finish());
    }

    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }

        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                    count++;

                    Log.i(TAG, "定时器启动------------------》" + count);
                }
            };
        }

        if (mTimer != null && mTimerTask != null)
            mTimer.schedule(mTimerTask, delay, period);
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            Log.e("QQ------------->>", "开始请求数据");
            switch (msg.what) {

                case 1:
                    if (count >= 2) {

                        Log.e(TAG, "开始请求数据了——————————》》》" + count);


                        getUserDataSp();  //从sp里面取数据
                        count = 0;
                    } else {
                        Log.e(TAG, "现在count的值为——————————》》》" + count);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    //gcc_add
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
// TODO Auto-generated method stub
            Log.d("TAG", "afterTextChanged--------------->");
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
// TODO Auto-generated method stub
            Log.d("TAG", "beforeTextChanged--------------->");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            Log.d("TAG", "onTextChanged--------------->");


            tystr0 = timed1.getText().toString().trim();
            tystr1 = times1.getText().toString().trim();
            tystr2 = timef1.getText().toString().trim();
            tystr3 = timey1.getText().toString().trim();
            tystr4 = timem1.getText().toString().trim();
            tystr5 = timen1.getText().toString().trim();

        }
    };

    public void init() {
        bxg_devtime1 = findViewById(R.id.btn_xg_devtime1); //修改设备系统时间按钮
        bxg_devtime1.setOnClickListener(this);

        timen1 = findViewById(R.id.et_timen1);
        timen1.addTextChangedListener(textWatcher);
        timey1 = findViewById(R.id.et_timey1);
        timey1.addTextChangedListener(textWatcher);
        timed1 = findViewById(R.id.et_timed1);
        timed1.addTextChangedListener(textWatcher);
        times1 = findViewById(R.id.et_times1);
        times1.addTextChangedListener(textWatcher);
        timef1 = findViewById(R.id.et_timef1);
        timef1.addTextChangedListener(textWatcher);
        timem1 = findViewById(R.id.et_timem1);
        timem1.addTextChangedListener(textWatcher);

        initListen();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListen() {

        timen1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    timen1.setText("");
                }
            }
        });

        timey1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    timey1.setText("");
                }
            }
        });
        timed1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    timed1.setText("");
                }
            }
        });
        times1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    times1.setText("");
                }
            }
        });
        timef1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    timef1.setText("");
                }
            }
        });
        timem1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    timem1.setText("");
                }
            }
        });

        bxg_devtime1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bxg_devtime1.setFocusable(true);
                bxg_devtime1.setFocusableInTouchMode(true);
                bxg_devtime1.requestFocus();
                return false;
            }
        });
    }

    public void getUserDataSp() {


        Log.e("大菠萝", "开始从sp取心跳包数据-5555555555555555555555555555555555---------》");
        String strtm1 = sp.getString("s1", ""); //年
        String strtm2 = sp.getString("s2", ""); //月
        String strtm3 = sp.getString("s3", ""); //日
        String strtm4 = sp.getString("s4", ""); //时
        String strtm5 = sp.getString("s5", ""); //分
        String strtm6 = sp.getString("s6", ""); //秒

        timen1.setText(strtm1);
        timey1.setText(strtm2);
        timed1.setText(strtm3);
        times1.setText(strtm4);
        timef1.setText(strtm5);
        timem1.setText(strtm6);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_xg_devtime1:

                count = 0;
                Log.e(TAG, "点击修改设备时间=------------>>>>");
                if (tystr0.equals("") || tystr1.equals("") || tystr2.equals("") || tystr3.equals("") || tystr4.equals("") || tystr5.equals("")) {

                    Toast.makeText(SystimeActivity.this, "输入为空！！！", Toast.LENGTH_SHORT).show();
                } else {
                    if (mstate) {
                        mstate = false;
                        DecimalFormat df = new DecimalFormat("0000");

                        String str = "100";
                        int i = Integer.valueOf(str, 10);
                        String s = Integer.toHexString(i);
                        Hex hex = new Hex();
                        hex.codeAddOne(s, 4);
                        Log.e(TAG, "测试值为---------->>>" + str);
                        Log.e(TAG, "测试值为---------->>>" + s);
                        Log.e(TAG, "测试值为---------->>>" + hex.codeAddOne(s, 4));


                        slstr0 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(tystr0, 10)), 4);
                        slstr1 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(tystr1, 10)), 4);
                        slstr2 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(tystr2, 10)), 4);
                        slstr3 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(tystr3, 10)), 4);
                        slstr4 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(tystr4, 10)), 4);
                        slstr5 = hex.codeAddOne(Integer.toHexString(Integer.valueOf(tystr5, 10)), 4);

                        String yspj1 = tc2.testStringBuilder2(slstr5, slstr3, slstr0, slstr1, slstr2, slstr4);

                        Log.e(TAG, "yspj1yspj1------------>>>" + yspj1);
                        dmrbell2(yspj1, "0150");

                        bxg_devtime1.setBackgroundResource(R.drawable.btn_bg_round_click2);

                        Log.e(TAG, "修改后的时间为：" + tystr5 + "年" + tystr3 + "月" + tystr0 + "日" + tystr1 + "时" + tystr2 + "分" + tystr4 + "秒");

                        Log.e(TAG, "修改后的十六进制时间为：" + slstr5 + slstr3 + slstr0 + slstr1 + slstr2 + slstr4);
                        bxg_devtime1.setText("更新");
                    } else {
                        mstate = true;
                        bxg_devtime1.setBackgroundResource(R.drawable.btn_bg_round_click);
                        bxg_devtime1.setText("更新");
                    }
                }
                break;
        }
    }

    //天时分月秒年

    //通用协议接口
    public void dmrbell2(String content, String startadd) {

        ZhiShui zhiShui1 = new ZhiShui();
        zhiShui1.setCommond("0110"); //固定指令，当前远程控制0110
        zhiShui1.setContent(content); //远程控制内容
        zhiShui1.setDate(String.valueOf(new Date().getTime()));
        zhiShui1.setDeviceNum(seid); // 设备号
        zhiShui1.setEtypecode(Contants.ETYPECODE_SJ);
        zhiShui1.setIemi(simei);
        zhiShui1.setNum("0006"); //修改寄存器数量
        zhiShui1.setNumLen("12"); //修改字节长度
        zhiShui1.setPhone(sphonenumber);
        zhiShui1.setStartAdd(startadd); //远程修改寄存器起始地址
        zhiShui1.setUid(suid);

        Gson gson = new Gson();
        String obj2 = gson.toJson(zhiShui1);
        Log.e(TAG, "jsonjsonjson----->>>>" + obj2);
        OkHttpUtils3 okHttpUtils = new OkHttpUtils3(this);
        okHttpUtils.post(WebUrls.getcustomurl, obj2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();//取消任务
        handler.removeCallbacks(mTimerTask);//取消任务
        Log.d("疾风剑豪2：", "onDestroy被调用了------------>");
    }
}
