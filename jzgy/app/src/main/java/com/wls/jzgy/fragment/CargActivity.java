package com.wls.jzgy.fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.wls.jzgy.R;

import java.util.Timer;

/**
 * 清洗
 */
public class CargActivity extends AppCompatActivity implements View.OnClickListener {

    private View view;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private Timer timer;
    private int count = 1;

    private EditText timed, times, timef, timey, timem, timen;
    private TextView time1;

    private Button  bpy, bjb, bgz, bpkjbt, bhg, bgybzt, bgftlx, bxy, bxg_devtime;
    private TextView title_bar_title;
    private ImageView back;

    private boolean state = true;

    private String ystr10, ystr11, ystr12, ystr13, ystr14, ystr15;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_carg);

        initView();
    }

//    @SuppressLint("HandlerLeak")
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_carg, container, false);
//		LogUtils.i(TAG,"定时制水界面加载..........");
//        sp = this.getActivity().getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
//        editor = sp.edit();

//        initView();
//        initListen();

//        //每5秒请求一次服务器
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                message.what = 1;
//                handler.sendMessage(message);
//            }
//        }, 1000, 8000);

//        return view;
//    }


//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    getUserData3();
//
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//
//
//
//    //gcc_add
//    private TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void afterTextChanged(Editable s) {
//// TODO Auto-generated method stub
//            Log.d("TAG", "afterTextChanged--------------->");
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count,
//                                      int after) {
//// TODO Auto-generated method stub
//            Log.d("TAG", "beforeTextChanged--------------->");
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before,
//                                  int count) {
//            Log.d("TAG", "onTextChanged--------------->");
//
//            ystr10 = timed.getText().toString().trim();
//            ystr11 = times.getText().toString().trim();
//            ystr12 = timef.getText().toString().trim();
//            ystr13 = timey.getText().toString().trim();
//            ystr14 = timem.getText().toString().trim();
//            ystr15 = timen.getText().toString().trim();
//
//        }
//    };
//
//    @SuppressLint("ClickableViewAccessibility")
    private void initView() {

        title_bar_title = findViewById(R.id.tv_title_bar_title);
        title_bar_title.setText("配液");
        back = findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//
//        //设备系统时间
//        timed = view.findViewById(R.id.et_timed);  //天
//        timed.addTextChangedListener(textWatcher);
//
//        times = view.findViewById(R.id.et_times);  //时
//        times.addTextChangedListener(textWatcher);
//
//        timef = view.findViewById(R.id.et_timef);  //分
//        timef.addTextChangedListener(textWatcher);
//
//        timey = view.findViewById(R.id.et_timey);  //月
//        timey.addTextChangedListener(textWatcher);
//
//        timem = view.findViewById(R.id.et_timem);  //秒
//        timem.addTextChangedListener(textWatcher);
//
//        timen = view.findViewById(R.id.et_timen);  //年
//        timen.addTextChangedListener(textWatcher);
//
//
//        bxg_devtime = view.findViewById(R.id.btn_xg_devtime); //修改设备系统时间按钮
//
//
//        time1 = view.findViewById(R.id.tv_time1);  //设备运行时长
//
//
//
//        bpy = view.findViewById(R.id.btn_py); //配液
//        bjb = view.findViewById(R.id.btn_jb); //搅拌
//        bgz = view.findViewById(R.id.btn_gz); //灌注
//        bpkjbt = view.findViewById(R.id.btn_pkjbt);  //排空搅拌桶
//        bxy = view.findViewById(R.id.btn_xy); //消音
//        bhg = view.findViewById(R.id.btn_hg); //回灌
//        bgybzt = view.findViewById(R.id.btn_gybkz);   //供液泵状态
//        bgftlx = view.findViewById(R.id.btn_gftxz);   //A/B 干粉桶类型
//
//        bpy.setOnClickListener(this);
//        bjb.setOnClickListener(this);
//        bgz.setOnClickListener(this);
//        bpkjbt.setOnClickListener(this);
//        bxy.setOnClickListener(this);
//        bhg.setOnClickListener(this);
//        bgybzt.setOnClickListener(this);
//        bgftlx.setOnClickListener(this);
//
//
//
//        title_bar_title = view.findViewById(R.id.tv_title_bar_title);
//        title_bar_title.setText("清洗");
//        back = view.findViewById(R.id.iv_back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });
    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    private void initListen() {
//
//        bpy.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                bpy.setFocusable(true);
//                bpy.setFocusableInTouchMode(true);
//                bpy.requestFocus();
//                return false;
//            }
//        });
//
//        bjb.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                bjb.setFocusable(true);
//                bjb.setFocusableInTouchMode(true);
//                bjb.requestFocus();
//                return false;
//            }
//        });
//
//        bgz.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                bgz.setFocusable(true);
//                bgz.setFocusableInTouchMode(true);
//                bgz.requestFocus();
//                return false;
//            }
//        });
//
//        bxy.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                bxy.setFocusable(true);
//                bxy.setFocusableInTouchMode(true);
//                bxy.requestFocus();
//                return false;
//            }
//        });
//
//        bpkjbt.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                bpkjbt.setFocusable(true);
//                bpkjbt.setFocusableInTouchMode(true);
//                bpkjbt.requestFocus();
//                return false;
//            }
//        });
//
//        bhg.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                bhg.setFocusable(true);
//                bhg.setFocusableInTouchMode(true);
//                bhg.requestFocus();
//                return false;
//            }
//        });
//
////        bgybzt.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////
////                bgybzt.setFocusable(true);
////                bgybzt.setFocusableInTouchMode(true);
////                bgybzt.requestFocus();
////                return false;
////            }
////        });
//
//
//        bgftlx.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                bgftlx.setFocusable(true);
//                bgftlx.setFocusableInTouchMode(true);
//                bgftlx.requestFocus();
//                return false;
//            }
//        });
//    }
//
//
//
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//
//            case R.id.btn_xg_devtime:  //设备系统时间
//                if (state) {
//                    state = false;
//                    ysend(ystr10, ystr11, ystr12, ystr13, ystr14, ystr15);
//                    bxg_devtime.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bxg_devtime.setText("更新");
//                } else {
//                    state = true;
//                    bxg_devtime.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bxg_devtime.setText("更新");
//                }
//                break;
//
//            case R.id.btn_py:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0100");
//                    bpy.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bpy.setText("运行中");
////                    bpkjbt.setEnabled(false);
////                    bjb.setEnabled(false);
////                    bgz.setEnabled(false);
////                    bxy.setEnabled(false);
//                } else {
//                    state = true;
//                    dmrbell("0000", "0100");
//                    bpy.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bpy.setText("停止");
////                    bpkjbt.setEnabled(true);
////                    bjb.setEnabled(true);
////                    bgz.setEnabled(true);
////                    bxy.setEnabled(true);
//                }
//                break;
//            case R.id.btn_jb:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0101");
//                    bjb.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bjb.setText("运行中");
////                    bpkjbt.setEnabled(false);
////                    bpy.setEnabled(false);
////                    bgz.setEnabled(false);
////                    bxy.setEnabled(false);
//                } else {
//                    state = true;
//                    dmrbell("0000", "0101");
//                    bjb.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bjb.setText("停止");
////                    bpy.setEnabled(true);
////                    bpkjbt.setEnabled(true);
////                    bgz.setEnabled(true);
////                    bxy.setEnabled(true);
//                }
//                break;
//            case R.id.btn_gz:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0102");
//                    bgz.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bgz.setText("消毒暂停");
////                    bpkjbt.setEnabled(false);
////                    bjb.setEnabled(false);
////                    bpy.setEnabled(false);
////                    bxy.setEnabled(false);
//                } else {
//                    state = true;
//                    dmrbell("0000", "0102");
//                    bgz.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bgz.setText("停止");
////                    bpy.setEnabled(true);
////                    bjb.setEnabled(true);
////                    bpkjbt.setEnabled(true);
////                    bxy.setEnabled(true);
//                }
//                break;
//            case R.id.btn_pkjbt:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0103");
//                    bpkjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bpkjbt.setText("运行中");
////                    bpy.setEnabled(false);
////                    bjb.setEnabled(false);
////                    bgz.setEnabled(false);
////                    bxy.setEnabled(false);
//                } else {
//                    state = true;
//                    dmrbell("0000", "0103");
//                    bpkjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bpkjbt.setText("停止");
////                    bpy.setEnabled(true);
////                    bjb.setEnabled(true);
////                    bgz.setEnabled(true);
////                    bxy.setEnabled(true);
//                }
//                break;
//            case R.id.btn_xy:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0104");
//                    bxy.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bxy.setText("运行中");
//                } else {
//                    state = true;
//                    dmrbell("0000", "0104");
//                    bxy.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bxy.setText("停止");
//                }
//                break;
//
//            case R.id.btn_hg:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0105");
//                    bhg.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bhg.setText("运行中");
//                } else {
//                    state = true;
//                    dmrbell("0000", "0105");
//                    bhg.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bhg.setText("停止");
//                }
//                break;
//            case R.id.btn_gybzt:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0106");
//                    bgybzt.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bgybzt.setText("运行中");
//                } else {
//                    state = true;
//                    dmrbell("0000", "0106");
//                    bgybzt.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bgybzt.setText("停止");
//                }
//                break;
//            case R.id.btn_gftlx:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0107");
//                    bgftlx.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bgftlx.setText("更新");
//                } else {
//                    state = true;
//                    dmrbell("0000", "0107");
//                    bgftlx.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bgftlx.setText("更新");
//                }
//                break;
//
//
            default:
                break;
        }
    }
//
//    //gcc_add
//    public void getUserData3() {
//
//        String strtime1 = sp.getString("s1", ""); //年
//        String strtime2 = sp.getString("s2", ""); //月
//        String strtime3 = sp.getString("s3", ""); //日
//        String strtime4 = sp.getString("s4", ""); //时
//        String strtime5 = sp.getString("s5", ""); //分
//        String strtime6 = sp.getString("s6", ""); //秒
//
////        time.setText(strtime1 + "年" + strtime2 + "月" + strtime3 + "日" + strtime4 + "时" + strtime5 + "分" + strtime6 + "秒");
//        timed.setText(strtime3);
//        times.setText(strtime4);
//        timef.setText(strtime5);
//        timey.setText(strtime2);
//        timem.setText(strtime6);
//        timen.setText(strtime1);
//
//        String strtime0 = sp.getString("s13", ""); //设备运行时长
//        Float value = Float.intBitsToFloat(Integer.valueOf(strtime0));
//        String value1 = String.format("%1.2f", value);
//        time1.setText(String.valueOf(value1));
//
//        //gcc_add —— 设备运行状态解析
//        String str22 = sp.getString("s8", ""); //设备运行状态
//        String str33 = sp.getString("sbr", "");
//
//        TC tc1 = new TC();
//        String rw = tc1.hexString2binaryString(str33);
//        String ns = StringUtils.trim(rw);
//        System.out.println(ns);
//        String ns0 = StringUtils.substring(ns, 1, 2); //bit15
//        String ns1 = StringUtils.substring(ns, 2, 3); //bit14
//        String ns2 = StringUtils.substring(ns, 3, 4); //bit13
//        String ns3 = StringUtils.substring(ns, 4, 5); //bit12
//        String ns4 = StringUtils.substring(ns, 5, 6); //bit11
//        String ns5 = StringUtils.substring(ns, 6, 7); //bit10
//        String ns6 = StringUtils.substring(ns, 7, 8); //bit9
//        String ns7 = StringUtils.substring(ns, 8, 9); //bit8
//        String ns8 = StringUtils.substring(ns, 9, 10); //bit7
//        String ns9 = StringUtils.substring(ns, 10, 11); //bit6
//        String ns10 = StringUtils.substring(ns, 11, 12); //bit5
//        String ns11 = StringUtils.substring(ns, 12, 13); //bit4
//        String ns12 = StringUtils.substring(ns, 13, 14); //bit3
//        String ns13 = StringUtils.substring(ns, 14, 15); //bit2
//        String ns14 = StringUtils.substring(ns, 15, 16); //bit1
//
//        if (ns14.equals("1")) {
//            bpy.setBackgroundResource(R.drawable.btn_bg_round_click2);
//            bpy.setText("运行中");
////            bpkjbt.setEnabled(false);
////            bjb.setEnabled(false);
////            bgz.setEnabled(false);
////            bxy.setEnabled(false);
//
//        } else {
//            bpy.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bpy.setText("停止");
//        }
//
//        if (ns11.equals("1")) {
//            bpkjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
////            bpy.setEnabled(false);
////            bjb.setEnabled(false);
////            bgz.setEnabled(false);
////            bxy.setEnabled(false);
//            bpkjbt.setText("运行中");
//        } else {
//            bpkjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bpkjbt.setText("停止");
//        }
//
//        if (ns13.equals("1")) {
//            bjb.setBackgroundResource(R.drawable.btn_bg_round_click2);
////            bpkjbt.setEnabled(false);
////            bpy.setEnabled(false);
////            bgz.setEnabled(false);
////            bxy.setEnabled(false);
//            bjb.setText("运行中");
//        } else {
//            bjb.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bjb.setText("停止");
//        }
//
//        if (ns12.equals("1")) {
//            bxy.setBackgroundResource(R.drawable.btn_bg_round_click2);
////            bpkjbt.setEnabled(false);
////            bjb.setEnabled(false);
////            bgz.setEnabled(false);
////            bpy.setEnabled(false);
//            bxy.setText("运行中");
//        } else {
//            bxy.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bxy.setText("停止");
//        }
////
//        if (ns10.equals("1")) {
//            bhg.setBackgroundResource(R.drawable.btn_bg_round_click2);
//        } else {
//            bhg.setBackgroundResource(R.drawable.btn_bg_round_click);
//        }
//
//        if (ns9.equals("1")) {
//            bgybzt.setBackgroundResource(R.drawable.btn_bg_round_click2);
//        } else {
//            bgybzt.setBackgroundResource(R.drawable.btn_bg_round_click);
//        }
//
//        if (ns8.equals("1")) {
//            bgftlx.setBackgroundResource(R.drawable.btn_bg_round_click2);
//        } else {
//            bgftlx.setBackgroundResource(R.drawable.btn_bg_round_click);
//        }
//
////        if (ns7.equals("1")) {
////            bpk.setBackgroundResource(R.drawable.btn_bg_round_click2);
////        } else {
////            bpk.setBackgroundResource(R.drawable.btn_bg_round_click);
////        }
////
////        if (ns6.equals("1")) {
////            bqx.setBackgroundResource(R.drawable.btn_bg_round_click2);
//////            bpkjbt.setEnabled(false);
//////            bjb.setEnabled(false);
//////            bgz.setEnabled(false);
//////            bpy.setEnabled(false);
////            bqx.setText("运行中");
////        } else {
////            bqx.setBackgroundResource(R.drawable.btn_bg_round_click);
////            bqx.setText("停止");
////        }
////
////        if (ns5.equals("1")) {
////            bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
//////            bpkjbt.setEnabled(false);
//////            bjb.setEnabled(false);
//////            bgz.setEnabled(false);
//////            bpy.setEnabled(false);
////            bxd.setText("运行中");
////        } else {
////            bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
////            bxd.setText("停止");
////        }
////
////        if (ns4.equals("1")) {
////            bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
//////            bpkjbt.setEnabled(false);
//////            bjb.setEnabled(false);
//////            bgz.setEnabled(false);
//////            bpy.setEnabled(false);
////            bqxjbt.setText("运行中");
////        } else {
////            bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
////            bqxjbt.setText("停止");
////        }
////
////        if (ns3.equals("1")) {
////            bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click2);
//////            bpkjbt.setEnabled(false);
//////            bjb.setEnabled(false);
//////            bgz.setEnabled(false);
//////            bpy.setEnabled(false);
////            bpkcyt.setText("运行中");
////        } else {
////            bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click);
////            bpkcyt.setText("停止");
////        }
//
//    }
//
//
//    //gcc_add —— 设置设备系统时间
//    public void ysend(String day, String hour, String min, String month, String second, String year) {
//        HashMap<String, String> map = new HashMap<>();   //zy: HashMap是接口Map的实现类
//        ZhiShui zhiShui = new ZhiShui();
//        zhiShui.setDeviceNum("0002E704");
//        zhiShui.setDay(Integer.parseInt(day));
//        zhiShui.setHour(Integer.parseInt(hour));
//        zhiShui.setMinute(Integer.parseInt(min));
//        zhiShui.setMonth(Integer.parseInt(month));
//        zhiShui.setSecond(Integer.parseInt(second));
//        zhiShui.setYear(Integer.parseInt(year));
//        String json = JSON.toJSONString(zhiShui);
//        OkHttpUtils2 okHttpUtils = new OkHttpUtils2();
//        okHttpUtils.post(WebUrls.getsetsystimeurl, json);
//    }
//
//    //通用接口
//    public void dmrbell(String content, String startadd) {
//        HashMap<String, String> map = new HashMap<>();     //zy: HashMap是接口Map的实现类
//        ZhiShui zhiShui1 = new ZhiShui();
//        zhiShui1.setCommond("0110"); //固定指令，当前远程控制0110
//        zhiShui1.setContent(content); //远程控制内容
//        zhiShui1.setDeviceNum("0002E704"); // 设备号
//        zhiShui1.setDeviceType(1); //设备类型
//        zhiShui1.setNum("0001"); //修改寄存器数量
//        zhiShui1.setNumLen("02"); //修改字节长度
//        zhiShui1.setStartAdd(startadd); //远程修改寄存器起始地址
//        String json = JSON.toJSONString(zhiShui1);
//        OkHttpUtils2 okHttpUtils = new OkHttpUtils2();
//        okHttpUtils.post(WebUrls.getcustomurl, json);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("疾风剑豪2：", "onDestroy被调用了------------>");

    }
}



























