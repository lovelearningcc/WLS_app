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
public class RinseActivity extends AppCompatActivity implements View.OnClickListener {

    private View view;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private Timer timer;
    private int count = 1;

    private EditText timed, times, timef, timey, timem, timen;
    private TextView time1;

    private Button  bpk, bqx, bxd, bxy, bqxjbt, bpkcyt, bxg_devtime;
    private TextView title_bar_title;
    private ImageView back;

    private boolean state = true;

    private String ystr10, ystr11, ystr12, ystr13, ystr14, ystr15;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rinse);
        initView();
    }

//    @SuppressLint("HandlerLeak")
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_rinse, container, false);
////		LogUtils.i(TAG,"定时制水界面加载..........");
////        sp = this.getActivity().getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
////        editor = sp.edit();
////
////        initView();
////        initListen();
//
////        //每8秒请求一次服务器
////        timer = new Timer();
////        timer.scheduleAtFixedRate(new TimerTask() {
////            @Override
////            public void run() {
////                Message message = new Message();
////                message.what = 1;
////                handler.sendMessage(message);
////            }
////        }, 1000, 8000);
//
//        return view;
//    }

//
//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    getUserData2();
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
        title_bar_title.setText("清洗");
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
//        bpk = view.findViewById(R.id.btn_pk);   //排空
//        bqx = view.findViewById(R.id.btn_qx);   //清洗
//
//        bxd = view.findViewById(R.id.btn_xd);   //消毒
//        bxy = view.findViewById(R.id.btn_xy);   //消音
//
//        bqxjbt = view.findViewById(R.id.btn_qxjbt);   //清洗搅拌桶
//        bpkcyt = view.findViewById(R.id.btn_pkcyt);   //排空储液桶
//
//        bpk.setOnClickListener(this);
//        bqx.setOnClickListener(this);
//        bxd.setOnClickListener(this);
//        bxy.setOnClickListener(this);
//        bqxjbt.setOnClickListener(this);
//        bpkcyt.setOnClickListener(this);
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
////    @SuppressLint("ClickableViewAccessibility")
////    private void initListen() {
////
////        bpk.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////
////                bpk.setFocusable(true);
////                bpk.setFocusableInTouchMode(true);
////                bpk.requestFocus();
////                return false;
////            }
////        });
////
////        bqx.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////
////                bqx.setFocusable(true);
////                bqx.setFocusableInTouchMode(true);
////                bqx.requestFocus();
////                return false;
////            }
////        });
////
////        bxd.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////
////                bxd.setFocusable(true);
////                bxd.setFocusableInTouchMode(true);
////                bxd.requestFocus();
////                return false;
////            }
////        });
////
////        bxy.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////
////                bxy.setFocusable(true);
////                bxy.setFocusableInTouchMode(true);
////                bxy.requestFocus();
////                return false;
////            }
////        });
////
////        bqxjbt.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////
////                bqxjbt.setFocusable(true);
////                bqxjbt.setFocusableInTouchMode(true);
////                bqxjbt.requestFocus();
////                return false;
////            }
////        });
////
////        bpkcyt.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////
////                bpkcyt.setFocusable(true);
////                bpkcyt.setFocusableInTouchMode(true);
////                bpkcyt.requestFocus();
////                return false;
////            }
////        });
////    }
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
//            case R.id.btn_pk:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0108");
//                    bpk.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bpk.setText("运行中");
//                } else {
//                    state = true;
//                    dmrbell("0000", "0108");
//                    bpk.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bpk.setText("停止");
//                }
//                break;
//            case R.id.btn_qx:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "0109");
//                    bqx.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bqx.setText("运行中");
//                } else {
//                    state = true;
//                    dmrbell("0000", "0109");
//                    bqx.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bqx.setText("停止");
//                }
//                break;
//            case R.id.btn_xd:
//                if (state) {
//                    bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    dmrbell("0001", "010a");
//                    bxd.setText("运行中");
//                    state = false;
//                } else {
//                    state = true;
//                    dmrbell("0000", "010a");
//                    bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bxd.setText("停止");
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
//            case R.id.btn_qxjbt:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "010b");
//                    bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bqxjbt.setText("运行中");
//                } else {
//                    state = true;
//                    dmrbell("0000", "010b");
//                    bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bqxjbt.setText("停止");
//                }
//                break;
//            case R.id.btn_pkcyt:
//                if (state) {
//                    state = false;
//                    dmrbell("0001", "010c");
//                    bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                    bpkcyt.setText("运行中");
//                } else {
//                    state = true;
//                    dmrbell("0000", "010c");
//                    bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click);
//                    bpkcyt.setText("停止");
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
//    public void getUserData2() {
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
//
//
//        time1.setText(String.valueOf(value1));
//
////        timed.setText("1.66");
////        System.out.println(time1);
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
//
//        if (ns7.equals("1")) {
//            bpk.setBackgroundResource(R.drawable.btn_bg_round_click2);
//        } else {
//            bpk.setBackgroundResource(R.drawable.btn_bg_round_click);
//        }
//
//        if (ns6.equals("1")) {
//            bqx.setBackgroundResource(R.drawable.btn_bg_round_click2);
////            bpkjbt.setEnabled(false);
////            bjb.setEnabled(false);
////            bgz.setEnabled(false);
////            bpy.setEnabled(false);
//            bqx.setText("运行中");
//        } else {
//            bqx.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bqx.setText("停止");
//        }
//
//        if (ns5.equals("1")) {
//            bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
////            bpkjbt.setEnabled(false);
////            bjb.setEnabled(false);
////            bgz.setEnabled(false);
////            bpy.setEnabled(false);
//            bxd.setText("运行中");
//        } else {
//            bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bxd.setText("停止");
//        }
//
//        if (ns4.equals("1")) {
//            bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
////            bpkjbt.setEnabled(false);
////            bjb.setEnabled(false);
////            bgz.setEnabled(false);
////            bpy.setEnabled(false);
//            bqxjbt.setText("运行中");
//        } else {
//            bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bqxjbt.setText("停止");
//        }
//
//        if (ns3.equals("1")) {
//            bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click2);
////            bpkjbt.setEnabled(false);
////            bjb.setEnabled(false);
////            bgz.setEnabled(false);
////            bpy.setEnabled(false);
//            bpkcyt.setText("运行中");
//        } else {
//            bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bpkcyt.setText("停止");
//        }
//
//
//    }
//
//
//
//
//        //gcc_add —— 设置设备系统时间
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



























