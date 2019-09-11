package com.wls.dmr.functions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wls.dmr.R;
import com.wls.dmr.entity.Contants;
import com.wls.dmr.entity.ZhiShui;
import com.wls.dmr.fragment.ConsActivity;
import com.wls.dmr.fragment.TimingActivity;
import com.wls.dmr.http.NewRequestManager;
import com.wls.dmr.http.OkHttpUtils3;
import com.wls.dmr.http.ReqCallBack;
import com.wls.dmr.persions.SystimeActivity;
import com.wls.dmr.utils.TC;
import com.wls.dmr.utils.WebUrls;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.wls.dmr.SplashActivity.isFirstStart;


public class RTDActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_bar_title, myeid, mytype;

    private String aql, lks;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TextView timed, times, timef, timey, timem, timen;

    private Button bzs, bxd, bxdzt, bcx, bfc, byjcx, bjtzt, bkx, byjfs, bejfs, bsjfs, bysdd, byjnsdd, byjcsdd, bejcsdd, byjcsll, bejcsll, byjfsll, byjms, byjjg, byjsz, bxdxh, bxdjp, bxdcx;
    private EditText eysdd, eyjnsdd, eyjcsdd, eejcsdd, eyjcsll, eejcsll, eyjfsll, eyjjg, eyjcx, exdxh, exdjp, exdcx;
    private String sysdd, syjnsdd, syjcsdd, sejcsdd, syjcsll, sejcsll, syjfsll, syjjg, syjcx, sxdxh, sxdjp, sxdcx;


    //报警信息
    private String ystr1;
    private String ystr2;
    private String ystr3;
    private String ystr4;
    private String ystr5;
    private String ystr6;
    private String ystr7;
    private String ystr8;
    private String ystr9 = "点击查看";

    //报警声音
    private MediaPlayer xjj;


    //调试中间状态集合1
    private String tstr1;
    private String tstr2;
    private String tstr3;
    private String tstr4;
    private String tstr5;
    private String tstr6;
    private String tstr7;
    private String tstr8;
    private String tstr9;
    private String tstr10;
    private String tstr11;
    private String tstr12;
    private String tstr13;
    private String tstr14;
    private String tstr15;
    private String tstr16 = "点击查看";


    private String zstr1;
    private String zstr2;
    private String zstr3;
    private String zstr4;
    private String zstr5;
    private String zstr6;
    private String zstr7;
    private String zstr8;
    private String zstr9;
    private String zstr10;
    private String zstr11;
    private String zstr12;
    private String zstr13;
    private String zstr14;
    private String zstr15;
    private String zstr16 = "点击查看调试中间状态集合2";

    private String jstr1;
    private String jstr2;
    private String jstr3;
    private String jstr4;
    private String jstr5;
    private String jstr6;
    private String jstr7;
    private String jstr8;
    private String jstr9;
    private String jstr10;
    private String jstr11;
    private String jstr12;
    private String jstr13;
    private String jstr14;
    private String jstr15;
    private String jstr16 = "点击查看调试中间状态集合6";


    private int count = 100;
    private boolean state = true;
    private boolean fstate = true;
    private boolean state1 = true;
    private boolean state2 = true;
    private boolean state6 = true;
    private boolean gflag;
    private boolean cflag;
    private boolean zflag;
    //gcc_add__逻辑判断
    private boolean zsflag;
    private boolean xdflag;
    private boolean cxflag;
    private boolean fcflag;
    private boolean yjcxflag;
    private boolean jtztflag;
    private boolean kxflag;

    private boolean yjfsflag;
    private boolean ejfsflag;
    private boolean sjfsflag;


    private boolean zsflag1;
    private boolean xdflag1;
    private boolean cxflag1;
    private boolean fcflag1;
    private boolean yjcxflag1;
    private boolean jtztflag1;
    private boolean kxflag1;

    private Button bxg_devtime;
    private static final String TAG = "RTDActivity";

    //gcc_add —— 下拉菜单
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    private Spinner spinner1;
    private List<String> data_list1;
    private ArrayAdapter<String> arr_adapter1;

    private Spinner spinner2;
    private List<String> data_list2;
    private ArrayAdapter<String> arr_adapter2;

    private Spinner spinner6;
    private List<String> data_list6;
    private ArrayAdapter<String> arr_adapter6;

    private Button bbjzt;

    private Button bzjzt1;
    private Button bzjzt2;
    private Button bzjzt6;
    private ListView listView;
    private boolean flag;

    //zy_and


    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private static int delay = 0;  //1s
    private static int period = 3000;  //3s

    private boolean flag1;


    private String phonenumber, imei, uid;

    private boolean isFlag1;

    //gcc_add —— 心跳包


    private String ms;

    private String value = null;
    private TextView time1;

    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String s8;
    private String s9;
    private String s10;
    private String s11;
    private String s12;
    private String s13;
    private String s14;
    private String s15;
    private String s16;
    private String s17;
    private String s18;
    private String s19;
    private String s20;
    private String s21;
    private String s22;
    private String s23;
    private String s24;
    private String s25;
    private String s26;
    private String s27;
    private String s28;
    private String s29;
    private String s30;
    private String s31;
    private String s32;
    private String s33;
    private String s34;
    private String s35;
    private String s36;
    private String s37;
    private String s38;
    private String s39;
    private String s40;
    private String s41;
    private String s42;
    private String s43;
    private String s44;
    private String s45;
    private String s46;
    private String s47;
    private String s48;
    private String s49;
    private String s50;
    private String s51;
    private String s52;
    private String s53;
    private String s54;
    private String suo1, suo2, suo3, suo4, suo5, suo6, suo7, suo8, suo9, suo10, suo11, suo12, suo13, suo14;
    private String sbr, scr, sdr, ser, sfr;
    //gcc_end

    private LinearLayout haoc, dszs;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list);

        sp = getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();

        xjj = MediaPlayer.create(this, R.raw.dong);

        lks = sp.getString("eid1", "");
        aql = sp.getString("type1", "");

        Log.e(TAG, "拉克丝的值为——————————》》》" + lks);

        phonenumber = sp.getString("akm", "");
        imei = sp.getString("ump9", "");
        uid = sp.getString("uid", "");

        if (!isFirstStart) {  //首次安装
            isFlag1 = sp.getBoolean("sd", Boolean.parseBoolean(""));
        } else {
            isFlag1 = sp.getBoolean("sd1", Boolean.parseBoolean(""));
        }

        System.out.println("66666666666");

        Log.e(TAG, "标志位的值为----------》" + isFlag1);

        init();
        startTimer();

        //activity跳转到fragment
        haoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RTDActivity.this, ConsActivity.class));
            }
        });
        dszs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RTDActivity.this, TimingActivity.class));
            }
        });
    }

    public void saveAccount121() {

        editor.putBoolean("yjfsflag", yjfsflag);
        editor.putBoolean("ejfsflag", ejfsflag);
        editor.putBoolean("sjfsflag", sjfsflag);

        editor.putBoolean("zsflag", zsflag);
        editor.putBoolean("cxflag", cxflag);
        editor.putBoolean("xdflag", xdflag);
        editor.putBoolean("fcflag", fcflag);
        editor.putBoolean("yjcxflag", yjcxflag);
        editor.putBoolean("jtztflag", jtztflag);
        editor.putBoolean("kxflag", kxflag);


        editor.commit();
    }

    //and
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

    //end
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            Log.e("QQ------------->>", "开始请求数据");
            switch (msg.what) {

                case 1:
                    if (count > 2) {

                        Log.e(TAG, "开始请求数据了——————————》》》" + count);

                        getUserData1();  //解析心跳包并保存心跳包数据到sp
//                        getUserData4();  //从sp里面取数据
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

            sysdd = eysdd.getText().toString().trim();
            syjnsdd = eyjnsdd.getText().toString().trim();
            syjcsdd = eyjcsdd.getText().toString().trim();
            sejcsdd = eejcsdd.getText().toString().trim();

            syjcsll = eyjcsll.getText().toString().trim();
            sejcsll = eejcsll.getText().toString().trim();
            syjfsll = eyjfsll.getText().toString().trim();

            syjjg = eyjjg.getText().toString().trim();
            syjcx = eyjcx.getText().toString().trim();

            sxdxh = exdxh.getText().toString().trim();
            sxdjp = exdjp.getText().toString().trim();
            sxdcx = exdcx.getText().toString().trim();

            syjjg = eyjjg.getText().toString().trim();
            syjcx = eyjcx.getText().toString().trim();
            sxdxh = exdxh.getText().toString().trim();
            sxdjp = exdjp.getText().toString().trim();
            sxdcx = exdcx.getText().toString().trim();

        }
    };
    //gcc_end

    public void init() {

        haoc = findViewById(R.id.ll_haoc);
        dszs = findViewById(R.id.ll_dszs);

        myeid = findViewById(R.id.tv_my_eid);
        mytype = findViewById(R.id.tv_my_type);

        myeid.setText(lks);
        mytype.setText(aql);


        title_bar_title = findViewById(R.id.tv_title_bar_title);
        title_bar_title.setText("实时数据及远程控制");

        spinner = (Spinner) findViewById(R.id.spinner); // 报警信息下拉菜单
//        spinner1 = findViewById(R.id.spinner1);  //调试中间状态集合1


//        listView = findViewById(R.id.list_view); //报警信息列表
//        listView.bringToFront();
        bbjzt = findViewById(R.id.btn_bjzt);  //报警状态

//        bzjzt1 = findViewById(R.id.btn_zjzt1);

        //设备系统时间
        timed = findViewById(R.id.et_timed);  //天
        timed.addTextChangedListener(textWatcher);

        times = findViewById(R.id.et_times);  //时
        times.addTextChangedListener(textWatcher);

        timef = findViewById(R.id.et_timef);  //分
        timef.addTextChangedListener(textWatcher);

        timey = findViewById(R.id.et_timey);  //月
        timey.addTextChangedListener(textWatcher);

        timem = findViewById(R.id.et_timem);  //秒
        timem.addTextChangedListener(textWatcher);

        timen = findViewById(R.id.et_timen);  //年
        timen.addTextChangedListener(textWatcher);

        bxg_devtime = findViewById(R.id.btn_xg_devtime); //修改设备系统时间按钮

        time1 = findViewById(R.id.tv_time1);  //设备运行时长

        //gcc_add
        bzs = findViewById(R.id.btn_zs);
        bxd = findViewById(R.id.btn_xd);
        bxdzt = findViewById(R.id.btn_xdzt);
        bcx = findViewById(R.id.btn_cx);
        bfc = findViewById(R.id.btn_fc);
        byjcx = findViewById(R.id.btn_yjcx);
        bjtzt = findViewById(R.id.btn_jtzt);
//        bkx = findViewById(R.id.btn_kx);

        bsjfs = findViewById(R.id.btn_sjfs);
        byjfs = findViewById(R.id.btn_yjfs);
        bejfs = findViewById(R.id.btn_ejfs);

        bysdd = findViewById(R.id.btn_ysdd);
        byjnsdd = findViewById(R.id.btn_yjnsdd);
        byjcsdd = findViewById(R.id.btn_yjcsdd);
        bejcsdd = findViewById(R.id.btn_ejcsdd);

        byjcsll = findViewById(R.id.btn_yjcsll);
        bejcsll = findViewById(R.id.btn_ejcsll);
        byjfsll = findViewById(R.id.btn_yjfsll);

        byjms = findViewById(R.id.btn_yjms);
        byjjg = findViewById(R.id.btn_yjjg);
        byjsz = findViewById(R.id.btn_yjsz);

        bxdxh = findViewById(R.id.btn_xdxh);
        bxdjp = findViewById(R.id.btn_xdjp);
        bxdcx = findViewById(R.id.btn_xdcx);

        bbjzt.setOnClickListener(this);    //报警状态
//        bzjzt1.setOnClickListener(this);   //调试中间状态集合1
        bzs.setOnClickListener(this);
        bxd.setOnClickListener(this);
        bxdzt.setOnClickListener(this);
        bcx.setOnClickListener(this);
        bfc.setOnClickListener(this);
        byjcx.setOnClickListener(this);
        bjtzt.setOnClickListener(this);
//        bkx.setOnClickListener(this);

        bysdd.setOnClickListener(this);
        byjnsdd.setOnClickListener(this);
        byjcsdd.setOnClickListener(this);
        bejcsdd.setOnClickListener(this);

        byjcsll.setOnClickListener(this);
        bejcsll.setOnClickListener(this);
        byjfsll.setOnClickListener(this);

        byjms.setOnClickListener(this);
        byjjg.setOnClickListener(this);
        byjsz.setOnClickListener(this);
        bjtzt.setOnClickListener(this);

        bxdxh.setOnClickListener(this);
        bxdjp.setOnClickListener(this);
        bxdcx.setOnClickListener(this);

        bxg_devtime.setOnClickListener(this);


        eysdd = findViewById(R.id.et_ysdd);
        eysdd.addTextChangedListener(textWatcher);

        eyjnsdd = findViewById(R.id.et_yjnsdd);
        eyjnsdd.addTextChangedListener(textWatcher);

        eyjcsdd = findViewById(R.id.et_yjcsdd);
        eyjcsdd.addTextChangedListener(textWatcher);

        eejcsdd = findViewById(R.id.et_ejcsdd);
        eejcsdd.addTextChangedListener(textWatcher);

        eyjcsll = findViewById(R.id.et_yjcsll);
        eyjcsll.addTextChangedListener(textWatcher);

        eejcsll = findViewById(R.id.et_ejcsll);
        eejcsll.addTextChangedListener(textWatcher);

        eyjfsll = findViewById(R.id.et_yjfsll);
        eyjfsll.addTextChangedListener(textWatcher);

        eyjjg = findViewById(R.id.et_yjjg);
        eyjjg.addTextChangedListener(textWatcher);
        eyjcx = findViewById(R.id.et_yjsz);
        eyjcx.addTextChangedListener(textWatcher);
        exdxh = findViewById(R.id.et_xdxh);
        exdxh.addTextChangedListener(textWatcher);
        exdjp = findViewById(R.id.et_xdjp);
        exdjp.addTextChangedListener(textWatcher);
        exdcx = findViewById(R.id.et_xdcx);
        exdcx.addTextChangedListener(textWatcher);

        initListen();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListen() {

        eysdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eysdd.setText("");
                }
            }
        });

        eyjnsdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eyjnsdd.setText("");
                }
            }
        });

        eyjcsdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eyjcsdd.setText("");
                }
            }
        });

        eejcsdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eejcsdd.setText("");
                }
            }
        });

        eyjcsll.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eyjcsll.setText("");
                }
            }
        });

        eejcsll.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eejcsll.setText("");
                }
            }
        });

        eyjfsll.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eyjfsll.setText("");
                }
            }
        });

        eyjjg.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eyjjg.setText("");
                }
            }
        });

        eyjcx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    eyjcx.setText("");
                }
            }
        });

        exdxh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    exdxh.setText("");
                }
            }
        });

        exdjp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    exdjp.setText("");
                }
            }
        });

        exdcx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    exdcx.setText("");
                }
            }
        });


        bzs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bzs.setFocusable(true);
                bzs.setFocusableInTouchMode(true);
                bzs.requestFocus();
                return false;
            }
        });
        bxd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bxd.setFocusable(true);
                bxd.setFocusableInTouchMode(true);
                bxd.requestFocus();
                return false;
            }
        });

        bxdzt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bxdzt.setFocusable(true);
                bxdzt.setFocusableInTouchMode(true);
                bxdzt.requestFocus();
                return false;
            }
        });

        bcx.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bcx.setFocusable(true);
                bcx.setFocusableInTouchMode(true);
                bcx.requestFocus();
                return false;
            }
        });

        byjfs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                byjfs.setFocusable(true);
                byjfs.setFocusableInTouchMode(true);
                byjfs.requestFocus();
                return false;
            }
        });

        bejfs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bejfs.setFocusable(true);
                bejfs.setFocusableInTouchMode(true);
                bejfs.requestFocus();
                return false;
            }
        });

        bsjfs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bsjfs.setFocusable(true);
                bsjfs.setFocusableInTouchMode(true);
                bsjfs.requestFocus();
                return false;
            }
        });

        bysdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bysdd.setFocusable(true);
                bysdd.setFocusableInTouchMode(true);
                bysdd.requestFocus();
                return false;
            }
        });

        byjnsdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                byjnsdd.setFocusable(true);
                byjnsdd.setFocusableInTouchMode(true);
                byjnsdd.requestFocus();
                return false;
            }
        });

        byjcsdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                byjcsdd.setFocusable(true);
                byjcsdd.setFocusableInTouchMode(true);
                byjcsdd.requestFocus();
                return false;
            }
        });

        bejcsdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bejcsdd.setFocusable(true);
                bejcsdd.setFocusableInTouchMode(true);
                bejcsdd.requestFocus();
                return false;
            }
        });
        byjcsll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                byjcsll.setFocusable(true);
                byjcsll.setFocusableInTouchMode(true);
                byjcsll.requestFocus();
                return false;
            }
        });

        bejcsll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bejcsll.setFocusable(true);
                bejcsll.setFocusableInTouchMode(true);
                bejcsll.requestFocus();
                return false;
            }
        });

        byjfsll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                byjfsll.setFocusable(true);
                byjfsll.setFocusableInTouchMode(true);
                byjfsll.requestFocus();
                return false;
            }
        });

        byjjg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                byjjg.setFocusable(true);
                byjjg.setFocusableInTouchMode(true);
                byjjg.requestFocus();
                return false;
            }
        });

        byjsz.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                byjsz.setFocusable(true);
                byjsz.setFocusableInTouchMode(true);
                byjsz.requestFocus();
                return false;
            }
        });

        bxdxh.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bxdxh.setFocusable(true);
                bxdxh.setFocusableInTouchMode(true);
                bxdxh.requestFocus();
                return false;
            }
        });

        bxdjp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bxdjp.setFocusable(true);
                bxdjp.setFocusableInTouchMode(true);
                bxdjp.requestFocus();
                return false;
            }
        });

        bxdcx.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bxdcx.setFocusable(true);
                bxdcx.setFocusableInTouchMode(true);
                bxdcx.requestFocus();
                return false;
            }
        });
    }


    //gcc_add —— 获取心跳包数据并保存到sp里面
    public void getUserData1() {

        Log.e("大菠萝", "开始请求心跳包数据-111111111111111111111111111---------》");

        String url = "";
        url = WebUrls.getrealtimedata;


        HashMap<String, String> mapDay = new HashMap<>();
        mapDay.put("date", String.valueOf(new Date().getTime()));
        mapDay.put("eid", lks);
        mapDay.put("eypecode", Contants.ETYPECODE_SJ);

        Log.e(TAG, "mapday的值为----------》" + mapDay);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isAvailable()) {
            //当前无可用网络
            Toast.makeText(this, "当前网络不可用！！！", Toast.LENGTH_SHORT).show();
        } else {
            //当前有可用网络
            NewRequestManager myNewRequestManager = new NewRequestManager(this);

            myNewRequestManager.requestAsyn(url, NewRequestManager.TYPE_POST_JSON, mapDay, new ReqCallBack<String>() {
                @Override
                public void onReqSuccess(String result) {

                    Log.e(TAG, "成功请求数据----------》");

                    Log.d("WLS", "onResponse回调结果------------>" + result);

                    try {
                        //gcc_add
                        JSONObject re = new JSONObject(result);
                        String code = re.getString("code");
                        switch (code) {
                            case "0":
                                Log.d("WLS：", "onResponse回调结果------------>" + code);
                                ms = re.getString("data");
                                JSONObject jsonObject = new JSONObject(ms);
                                value = jsonObject.getString("data");
                                System.out.println("value1=" + value);

                                if (value.equals(null)) {
                                    Toast.makeText(RTDActivity.this, "后台暂无数据", Toast.LENGTH_LONG).show();
                                } else {

                                    //gcc_add —— 设备系统时间解析
                                    TC tc = new TC();
                                    String str0 = tc.test(value, 0); //年
                                    String str1 = tc.test(value, 1); //年
                                    String str2 = tc.test(value, 2); //月
                                    String str3 = tc.test(value, 3); //日
                                    String str4 = tc.test(value, 4); //时
                                    String str5 = tc.test(value, 5); //时
                                    String str6 = tc.test(value, 6); //分
                                    String str7 = tc.test(value, 7); //秒

                                    s1 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 0), tc.test(value, 1)), 16));
                                    s2 = String.valueOf(Integer.parseInt(tc.test(value, 2), 16));
                                    s3 = String.valueOf(Integer.parseInt(tc.test(value, 3), 16));
                                    s4 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 4), tc.test(value, 5)), 16));
                                    s5 = String.format("%02d", Integer.parseInt(tc.test(value, 6), 16));
                                    s6 = String.format("%02d", Integer.parseInt(tc.test(value, 7), 16));


                                    //gcc_add —— 设备工作模式解析
                                    String str8 = tc.test(value, 10); // 工作模式
                                    String str9 = tc.test(value, 11); // 工作模式

                                    s7 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 10), tc.test(value, 11)), 16));

                                    //gcc_add —— 设备运行状态解析
                                    String str10 = tc.test(value, 12); // 运行状态
                                    String str11 = tc.test(value, 13); // 运行状态


                                    sbr = tc.testStringBuilder(str10, str11); // 运行状态

                                    s8 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 12), tc.test(value, 13)), 16));

                                    //gcc_add —— 设备报警状态解析
                                    String str12 = tc.test(value, 14); // 报警状态1
                                    String str13 = tc.test(value, 15); // 报警状态1
                                    String str14 = tc.test(value, 16); // 报警状态2
                                    String str15 = tc.test(value, 17); // 报警状态2
                                    String str16 = tc.test(value, 18); // 报警状态3
                                    String str17 = tc.test(value, 19); // 报警状态3
                                    String str18 = tc.test(value, 20); // 报警状态4
                                    String str19 = tc.test(value, 21); // 报警状态4

                                    scr = tc.testStringBuilder(str12, str13); // 报警状态1

                                    s9 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 14), tc.test(value, 15)), 16));

                                    s10 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 16), tc.test(value, 17)), 16));

                                    s11 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 18), tc.test(value, 19)), 16));

                                    s12 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 20), tc.test(value, 21)), 16));
                                    //end

                                    //gcc_add —— 设备运行时长解析
                                    String str20 = tc.test(value, 22); // 设备运行时长
                                    String str21 = tc.test(value, 23); // 设备运行时长
                                    String str22 = tc.test(value, 24); // 设备运行时长
                                    String str23 = tc.test(value, 25); // 设备运行时长

                                    String sgr = tc.testStringBuilder1(str20, str21, str22, str23); // 设备运行时长
                                    if (sgr.equals("") || sgr.equals(null)) {
                                        Log.e(TAG, "后台暂无数据------------->>>");
                                    } else {
                                        s13 = String.valueOf(Integer.parseInt(tc.testStringBuilder1(tc.test(value, 22), tc.test(value, 23), tc.test(value, 24), tc.test(value, 25)), 16));
                                        Log.e(TAG, "设备运行时长为" + s13);
                                    }

                                    //gcc_add —— 设备电导率数据显示解析
                                    String str24 = tc.test(value, 26);  //原水电导
                                    String str25 = tc.test(value, 27);  //原水电导
                                    String str26 = tc.test(value, 28);  //一级浓水电导
                                    String str27 = tc.test(value, 29);  //一级浓水电导
                                    String str28 = tc.test(value, 30);  //一级纯水电导
                                    String str29 = tc.test(value, 31);  //一级纯水电导
                                    String str30 = tc.test(value, 32);  //二级纯水电导
                                    String str31 = tc.test(value, 33);  //二级纯水电导

                                    s14 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 26), tc.test(value, 27)), 16));
                                    s15 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 28), tc.test(value, 29)), 16));
                                    s16 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 30), tc.test(value, 31)), 16));
                                    s17 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 32), tc.test(value, 33)), 16));

                                    //gcc_add —— 设备流速数据显示解析
                                    String str32 = tc.test(value, 34);  //一级纯水流量
                                    String str33 = tc.test(value, 35);  //一级纯水流量
                                    String str34 = tc.test(value, 36);  //二级纯水流量
                                    String str35 = tc.test(value, 37);  //二级纯水流量
                                    String str36 = tc.test(value, 38);  //一级废水流量
                                    String str37 = tc.test(value, 39);  //一级废水流量

                                    s18 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 34), tc.test(value, 35)), 16));
                                    s19 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 36), tc.test(value, 37)), 16));
                                    s20 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 38), tc.test(value, 39)), 16));


                                    String str38 = tc.test(value, 58);  //原水电导偏移量
                                    String str39 = tc.test(value, 59);  //原水电导偏移量
                                    String str40 = tc.test(value, 60);  //一级浓水电导偏移量
                                    String str41 = tc.test(value, 61);  //一级浓水电导偏移量
                                    String str42 = tc.test(value, 62);  //一级纯水电导偏移量
                                    String str43 = tc.test(value, 63);  //一级纯水电导偏移量
                                    String str44 = tc.test(value, 64);  //二级纯水电导偏移量
                                    String str45 = tc.test(value, 65);  //二级纯水电导偏移量

                                    String str46 = tc.test(value, 66);  //一级纯水流量偏移量
                                    String str47 = tc.test(value, 67);  //一级纯水流量偏移量
                                    String str48 = tc.test(value, 68);  //二级纯水流量偏移量
                                    String str49 = tc.test(value, 69);  //二级纯水流量偏移量
                                    String str50 = tc.test(value, 70);  //一级废水流量偏移量
                                    String str51 = tc.test(value, 71);  //一级废水流量偏移量


                                    //定时制水
                                    String str52 = tc.test(value, 90);  //周一使能
                                    String str53 = tc.test(value, 91);  //周一使能
                                    String str54 = tc.test(value, 92);  //周一时
                                    String str55 = tc.test(value, 93);  //周一时
                                    String str56 = tc.test(value, 94);  //周一分
                                    String str57 = tc.test(value, 95);  //周一分

                                    suo1 = tc.testStringBuilder(str52, str53); //周一使能
                                    s21 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 92), tc.test(value, 93)), 16));
                                    s22 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 94), tc.test(value, 95)), 16));

                                    String str58 = tc.test(value, 96);  //周二使能
                                    String str59 = tc.test(value, 97);  //周二使能
                                    String str60 = tc.test(value, 98);  //周二时
                                    String str61 = tc.test(value, 99);  //周二时
                                    String str62 = tc.test(value, 100);  //周二分
                                    String str63 = tc.test(value, 101);  //周二分

                                    suo2 = tc.testStringBuilder(str58, str59); //周二使能
                                    s23 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 98), tc.test(value, 99)), 16));
                                    s24 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 100), tc.test(value, 101)), 16));

                                    String str64 = tc.test(value, 102);  //周三使能
                                    String str65 = tc.test(value, 103);  //周三使能
                                    String str66 = tc.test(value, 104);  //周三时
                                    String str67 = tc.test(value, 105);  //周三时
                                    String str68 = tc.test(value, 106);  //周三分
                                    String str69 = tc.test(value, 107);  //周三分

                                    suo3 = tc.testStringBuilder(str64, str65); //周三使能
                                    s25 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 104), tc.test(value, 105)), 16));
                                    s26 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 106), tc.test(value, 107)), 16));


                                    String str71 = tc.test(value, 108);  //周四使能
                                    String str72 = tc.test(value, 109);  //周四使能
                                    String str73 = tc.test(value, 110);  //周四时
                                    String str74 = tc.test(value, 111);  //周四时
                                    String str75 = tc.test(value, 112);  //周四分
                                    String str76 = tc.test(value, 113);  //周四分

                                    suo4 = tc.testStringBuilder(str71, str72); //周四使能
                                    s27 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 110), tc.test(value, 111)), 16));
                                    s28 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 112), tc.test(value, 113)), 16));


                                    String str77 = tc.test(value, 114);  //周五使能
                                    String str78 = tc.test(value, 115);  //周五使能
                                    String str79 = tc.test(value, 116);  //周五时
                                    String str791 = tc.test(value, 117);  //周五时
                                    String str80 = tc.test(value, 118);  //周五分
                                    String str81 = tc.test(value, 119);  //周五分

                                    suo5 = tc.testStringBuilder(str77, str78); //周五使能
                                    s29 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 116), tc.test(value, 117)), 16));
                                    s30 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 118), tc.test(value, 119)), 16));

                                    String str103 = tc.test(value, 120);  //周六使能
                                    String str104 = tc.test(value, 121);  //周六使能
                                    String str105 = tc.test(value, 122);  //周六时
                                    String str106 = tc.test(value, 123);  //周六时
                                    String str107 = tc.test(value, 124);  //周六分
                                    String str108 = tc.test(value, 125);  //周六分

                                    suo6 = tc.testStringBuilder(str103, str104); //周六使能
                                    s31 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 122), tc.test(value, 123)), 16));
                                    s32 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 124), tc.test(value, 125)), 16));

                                    String str109 = tc.test(value, 126);  //周天使能
                                    String str110 = tc.test(value, 127);  //周天使能
                                    String str111 = tc.test(value, 128);  //周天时
                                    String str112 = tc.test(value, 129);  //周天时
                                    String str113 = tc.test(value, 130);  //周天分
                                    String str114 = tc.test(value, 131);  //周天分

                                    suo7 = tc.testStringBuilder(str109, str110); //周一使能
                                    s33 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 128), tc.test(value, 129)), 16));
                                    s34 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 130), tc.test(value, 131)), 16));

                                    String str115 = tc.test(value, 132);  //扩展
                                    String str116 = tc.test(value, 133);  //扩展
                                    String str117 = tc.test(value, 134);  //扩展
                                    String str118 = tc.test(value, 135);  //扩展
                                    String str119 = tc.test(value, 136);  //扩展
                                    String str120 = tc.test(value, 137);  //扩展


                                    //定时停止制水
                                    String str121 = tc.test(value, 138);  //周一使能
                                    String str122 = tc.test(value, 139);  //周一使能
                                    String str123 = tc.test(value, 140);  //周一时
                                    String str124 = tc.test(value, 141);  //周一时
                                    String str125 = tc.test(value, 142);  //周一分
                                    String str126 = tc.test(value, 143);  //周一分

                                    suo8 = tc.testStringBuilder(str121, str122); //周一使能
                                    s35 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 140), tc.test(value, 141)), 16));
                                    s36 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 142), tc.test(value, 143)), 16));


                                    String str127 = tc.test(value, 144);  //周二使能
                                    String str128 = tc.test(value, 145);  //周二使能
                                    String str129 = tc.test(value, 146);  //周二时
                                    String str130 = tc.test(value, 147);  //周二时
                                    String str131 = tc.test(value, 148);  //周二分
                                    String str132 = tc.test(value, 149);  //周二分

                                    suo9 = tc.testStringBuilder(str127, str128); //周二使能
                                    s37 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 146), tc.test(value, 147)), 16));
                                    s38 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 148), tc.test(value, 149)), 16));


                                    String str133 = tc.test(value, 150);  //周三使能
                                    String str134 = tc.test(value, 151);  //周三使能
                                    String str135 = tc.test(value, 152);  //周三时
                                    String str136 = tc.test(value, 153);  //周三时
                                    String str137 = tc.test(value, 154);  //周三分
                                    String str1371 = tc.test(value, 155);  //周三分

                                    suo10 = tc.testStringBuilder(str133, str134); //周三使能

                                    s39 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 152), tc.test(value, 153)), 16));
                                    s40 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 154), tc.test(value, 155)), 16));

                                    String str138 = tc.test(value, 156);  //周四使能
                                    String str139 = tc.test(value, 157);  //周四使能
                                    String str140 = tc.test(value, 158);  //周四时
                                    String str141 = tc.test(value, 159);  //周四时
                                    String str142 = tc.test(value, 160);  //周四分
                                    String str143 = tc.test(value, 161);  //周四分

                                    suo11 = tc.testStringBuilder(str138, str139); //周四使能

                                    s41 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 158), tc.test(value, 159)), 16));
                                    s42 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 160), tc.test(value, 161)), 16));

                                    String str144 = tc.test(value, 162);  //周五使能
                                    String str145 = tc.test(value, 163);  //周五使能
                                    String str146 = tc.test(value, 164);  //周五时
                                    String str147 = tc.test(value, 165);  //周五时
                                    String str148 = tc.test(value, 166);  //周五分
                                    String str149 = tc.test(value, 167);  //周五分

                                    suo12 = tc.testStringBuilder(str144, str145); //周五使能

                                    s43 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 164), tc.test(value, 165)), 16));
                                    s44 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 166), tc.test(value, 167)), 16));

                                    String str150 = tc.test(value, 168);  //周六使能
                                    String str151 = tc.test(value, 169);  //周六使能
                                    String str152 = tc.test(value, 170);  //周六时
                                    String str153 = tc.test(value, 171);  //周六时
                                    String str154 = tc.test(value, 172);  //周六分
                                    String str155 = tc.test(value, 173);  //周六分

                                    suo13 = tc.testStringBuilder(str150, str151); //周六使能

                                    s45 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 170), tc.test(value, 171)), 16));
                                    s46 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 172), tc.test(value, 173)), 16));

                                    String str156 = tc.test(value, 174);  //周天使能
                                    String str157 = tc.test(value, 175);  //周天使能
                                    String str158 = tc.test(value, 176);  //周天时
                                    String str159 = tc.test(value, 177);  //周天时
                                    String str160 = tc.test(value, 178);  //周天分
                                    String str161 = tc.test(value, 179);  //周天分


                                    suo14 = tc.testStringBuilder(str156, str157); //周天使能

                                    s47 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 176), tc.test(value, 177)), 16));
                                    s48 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 178), tc.test(value, 179)), 16));

                                    String str162 = tc.test(value, 180);  //扩展
                                    String str163 = tc.test(value, 181);  //扩展
                                    String str164 = tc.test(value, 182);  //扩展
                                    String str165 = tc.test(value, 183);  //扩展
                                    String str166 = tc.test(value, 184);  //扩展
                                    String str167 = tc.test(value, 185);  //扩展

                                    String str168 = tc.test(value, 186);  //调试中间状态1
                                    String str169 = tc.test(value, 187);  //调试中间状态1
                                    //gcc_add
                                    sdr = tc.testStringBuilder(str168, str169); // 调试中间状态1
                                    Log.e("调试中间状态", sdr);

//                                    s9 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 14), tc.test(value, 15)), 16));
//
//                                    s10 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 16), tc.test(value, 17)), 16));
//
//                                    s11 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 18), tc.test(value, 19)), 16));
//
//                                    s12 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 20), tc.test(value, 21)), 16));
                                    //end
                                    //gcc_end

                                    String str170 = tc.test(value, 188);  //调试中间状态2
                                    String str171 = tc.test(value, 189);  //调试中间状态2
                                    ser = tc.testStringBuilder(str170, str171); // 调试中间状态2

                                    String str172 = tc.test(value, 190);  //调试中间状态3
                                    String str173 = tc.test(value, 191);  //调试中间状态3
                                    String str174 = tc.test(value, 192);  //调试中间状态4
                                    String str175 = tc.test(value, 193);  //调试中间状态4
                                    String str176 = tc.test(value, 194);  //调试中间状态5
                                    String str177 = tc.test(value, 195);  //调试中间状态5

                                    String str178 = tc.test(value, 196);  //调试中间状态6
                                    String str179 = tc.test(value, 197);  //调试中间状态6
                                    sfr = tc.testStringBuilder(str178, str179); // 调试中间状态6

                                    String str180 = tc.test(value, 198);  //调试中间状态7
                                    String str181 = tc.test(value, 199);  //调试中间状态7
                                    String str182 = tc.test(value, 200);  //调试中间状态8
                                    String str183 = tc.test(value, 201);  //调试中间状态8

                                    String str184 = tc.test(value, 202);  //夜间冲洗时间间隔
                                    String str185 = tc.test(value, 203);  //夜间冲洗时间间隔
                                    String str186 = tc.test(value, 204);  //夜间冲洗时间设置
                                    String str187 = tc.test(value, 205);  //夜间冲洗时间设置
                                    String str188 = tc.test(value, 206);  //消毒循环时间设置
                                    String str189 = tc.test(value, 207);  //消毒循环时间设置
                                    String str190 = tc.test(value, 208);  //消毒浸泡时间设置
                                    String str191 = tc.test(value, 209);  //消毒浸泡时间设置
                                    String str192 = tc.test(value, 210);  //消毒冲洗时间设置
                                    String str193 = tc.test(value, 211);  //消毒冲洗时间设置

                                    s49 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 202), tc.test(value, 203)), 16));

                                    s50 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 204), tc.test(value, 205)), 16));

                                    s51 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 206), tc.test(value, 207)), 16));

                                    s52 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 208), tc.test(value, 209)), 16));

                                    s53 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 210), tc.test(value, 211)), 16));

                                    String str194 = tc.test(value, 212);  //扩展
                                    String str195 = tc.test(value, 213);  //
                                    String str196 = tc.test(value, 214);  //
                                    String str197 = tc.test(value, 215);  //
                                    String str198 = tc.test(value, 216);  //
                                    String str199 = tc.test(value, 217);  //

                                    String str200 = tc.test(value, 218);  //碳
                                    String str201 = tc.test(value, 219);  //砂
                                    String str202 = tc.test(value, 220);  //树脂
                                    String str203 = tc.test(value, 221);  //反渗透膜

                                    s54 = String.valueOf(Integer.parseInt(tc.testStringBuilder1(tc.test(value, 218), tc.test(value, 219), tc.test(value, 220), tc.test(value, 221)), 16));

                                    Log.e("大菠萝", "耗材使用时间" + s54);
                                    String str204 = tc.test(value, 222);  //扩展
                                    String str205 = tc.test(value, 223);  //扩展
                                    String str206 = tc.test(value, 224);  //扩展
                                    String str207 = tc.test(value, 225);  //扩展
                                    String str208 = tc.test(value, 226);  //扩展
                                    String str209 = tc.test(value, 227);  //扩展
                                    String str210 = tc.test(value, 228);  //扩展
                                    String str211 = tc.test(value, 229);  //扩展
                                    String str212 = tc.test(value, 230);  //扩展
                                    String str213 = tc.test(value, 231);  //扩展
                                    String str214 = tc.test(value, 232);  //扩展
                                    String str215 = tc.test(value, 233);  //扩展


                                    Log.e("大菠萝", "心跳包数据请求完成-2222222222222222222---------》");
                                    saveAccount();
                                    getUserData4();  //从sp里面取数据
                                }
                                break;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onReqFailed(String errorMsg) {

                    Toast.makeText(RTDActivity.this, "心跳包数据请求失败,请检查网络" + errorMsg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    //保存心跳包数据到sp
    public void saveAccount() {

        Log.e("大菠萝", "开始保存心跳包数据-3333333333333333333333333333---------》");
        //设备系统时间
        editor.putString("s1", s1); //年
        editor.putString("s2", s2); //月
        editor.putString("s3", s3); //日
        editor.putString("s4", s4); //时
        editor.putString("s5", s5); //分
        editor.putString("s6", s6); //秒


        editor.putString("s7", s7); //工作模式

        editor.putString("s8", s8); //运行状态
        editor.putString("sbr", sbr); //运行状态

        editor.putString("s9", s9); //报警状态1
        editor.putString("scr", scr); //报警状态1

        editor.putString("s10", s10); //报警状态2
        editor.putString("s11", s11); //报警状态3
        editor.putString("s12", s12); //报警状态4

        editor.putString("sdr", sdr); //调试中间状态1

        Log.e("调试中间状态1", sdr);
        editor.putString("ser", ser); //调试中间状态2
        editor.putString("sfr", sfr); //调试中间状态6

        editor.putString("s13", s13); // 设备运行时长


        editor.putString("s14", s14); // 原水电导
        editor.putString("s15", s15); // 一级浓水电导
        editor.putString("s16", s16); // 一级纯水电导
        editor.putString("s17", s17); // 二级纯水电导

        editor.putString("s18", s18); //一级纯水流量
        editor.putString("s19", s19); //二级纯水流量
        editor.putString("s20", s20); //一级废水流量

        //定时开始制水
        editor.putString("suo1", suo1); //周一使能
        editor.putString("suo2", suo2); //周一使能
        editor.putString("suo3", suo3); //周一使能
        editor.putString("suo4", suo4); //周一使能
        editor.putString("suo4", suo5); //周一使能
        editor.putString("suo6", suo6); //周一使能
        editor.putString("suo7", suo7); //周一使能

        editor.putString("s21", s21); //周一时
        editor.putString("s22", s22); //周一分
        editor.putString("s23", s23); //周二时
        editor.putString("s24", s24); //周二分
        editor.putString("s25", s25); //周三时
        editor.putString("s26", s26); //周三分
        editor.putString("s27", s27); //周四时
        editor.putString("s28", s28); //周四分
        editor.putString("s29", s29); //周五时
        editor.putString("s30", s30); //周五分
        editor.putString("s31", s31); //周六时
        editor.putString("s32", s32); //周六分
        editor.putString("s33", s33); //周天时
        editor.putString("s34", s34); //周天分


        //定时停止制水
        editor.putString("suo8", suo8); //周一使能
        editor.putString("suo9", suo9); //周一使能
        editor.putString("suo10", suo10); //周一使能
        editor.putString("suo11", suo11); //周一使能
        editor.putString("suo12", suo12); //周一使能
        editor.putString("suo13", suo13); //周一使能
        editor.putString("suo14", suo14); //周一使能

        editor.putString("s35", s35); //周一时
        editor.putString("s36", s36); //周一分
        editor.putString("s37", s37); //周二时
        editor.putString("s38", s38); //周二分
        editor.putString("s39", s39); //周三时
        editor.putString("s40", s40); //周三分
        editor.putString("s41", s41); //周四时
        editor.putString("s42", s42); //周四分
        editor.putString("s43", s43); //周五时
        editor.putString("s44", s44); //周五分
        editor.putString("s45", s45); //周六时
        editor.putString("s46", s46); //周六分
        editor.putString("s47", s47); //周天时
        editor.putString("s48", s48); //周天分

        editor.putString("s49", s49); //夜间冲洗时间间隔
        editor.putString("s50", s50); //夜间冲洗时间设置
        editor.putString("s51", s51); //消毒循环时间设置
        editor.putString("s52", s52); //消毒浸泡时间设置
        editor.putString("s53", s53); //消毒冲洗时间设置

        editor.putString("s54", s54); //炭，砂，树脂，反渗透膜使用时间

        editor.commit();

        Log.e("大菠萝", "保存心跳包数据完成-444444444444444444444444444444---------》");
    }
//gcc_end

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_xg_devtime:
                if (isFlag1) {
                    startActivity(new Intent(this, SystimeActivity.class));
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.btn_ysdd:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(sysdd, "0130");

                        Log.e(TAG, "原水电导的值为——————————》  " + sysdd);
                        bysdd.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        bysdd.setText("保存");
                    } else {
                        state = true;
                        bysdd.setBackgroundResource(R.drawable.btn_bg_round_click);
                        bysdd.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.et_ysdd:
                count = 0;
                break;
            case R.id.et_yjnsdd:
                count = 0;
                break;
            case R.id.btn_yjnsdd:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(syjnsdd, "0131");
                        byjnsdd.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        byjnsdd.setText("保存");
                    } else {
                        state = true;
                        byjnsdd.setBackgroundResource(R.drawable.btn_bg_round_click);
                        byjnsdd.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_yjcsdd:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(syjcsdd, "0132");
                        byjcsdd.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        byjcsdd.setText("保存");
                    } else {
                        state = true;
                        byjcsdd.setBackgroundResource(R.drawable.btn_bg_round_click);
                        byjcsdd.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_ejcsdd:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(sejcsdd, "0133");
                        bejcsdd.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        bejcsdd.setText("保存");
                    } else {
                        state = true;
                        bejcsdd.setBackgroundResource(R.drawable.btn_bg_round_click);
                        bejcsdd.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_yjcsll:
                if (isFlag1) {
                    count = 0;
                    if (state)

                    {
                        state = false;
                        dmrbell(syjcsll, "0134");
                        byjcsll.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        byjcsll.setText("保存");
                    } else

                    {
                        state = true;
                        byjcsll.setBackgroundResource(R.drawable.btn_bg_round_click);
                        byjcsll.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_ejcsll:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(sejcsll, "0135");
                        bejcsll.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        bejcsll.setText("保存");
                    } else

                    {
                        state = true;
                        bejcsll.setBackgroundResource(R.drawable.btn_bg_round_click);
                        bejcsll.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_yjfsll:
                if (isFlag1) {
                    count = 0;
                    if (state)

                    {
                        state = false;
                        dmrbell(syjfsll, "0136");
                        byjfsll.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        byjfsll.setText("保存");
                    } else

                    {
                        state = true;
                        byjfsll.setBackgroundResource(R.drawable.btn_bg_round_click);
                        byjfsll.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_zs:  //制水

                zsflag1 = sp.getBoolean("zsflag", Boolean.parseBoolean(""));
                xdflag1 = sp.getBoolean("xdflag", Boolean.parseBoolean(""));
                cxflag1 = sp.getBoolean("cxflag", Boolean.parseBoolean(""));

                if (isFlag1) {
                    count = 0;
//                    if (xdflag1 || cxflag1 || fcflag1 || yjcxflag1 || jtztflag1 || kxflag1) {
                    if (xdflag1 || cxflag1) {
                        bzs.setEnabled(false);
                    } else {
                        if (state) {
                            state = false;
                            dmrbell("0001", "0140");
                            bzs.setBackgroundResource(R.drawable.btn_bg_round_click2);
                            bzs.setText("运行中");
//                            bzs.setEnabled(true);
                            bcx.setEnabled(false);
                            bxd.setEnabled(false);

                        } else {
                            bxd.setEnabled(true);
                            bcx.setEnabled(true);
                            state = true;
                            dmrbell("0000", "0140");
                            bzs.setBackgroundResource(R.drawable.btn_bg_round_click);
                            bzs.setText("停止");

                        }
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_xd:     //z

                zsflag1 = sp.getBoolean("zsflag", Boolean.parseBoolean(""));
                xdflag1 = sp.getBoolean("xdflag", Boolean.parseBoolean(""));
                cxflag1 = sp.getBoolean("cxflag", Boolean.parseBoolean(""));
                if (isFlag1) {
                    count = 0;

                    if (zsflag1 || cxflag1) {
                        bxd.setEnabled(false);
                    } else {
                        if (state) {
                            state = false;
                            dmrbell("0001", "0141");
                            bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
                            bxd.setText("运行中");
                            flag1 = true;
//                            bxd.setEnabled(true);
                            bxdzt.setEnabled(true);
                            bcx.setEnabled(false);
                            bzs.setEnabled(false);
                        } else {
                            state = true;
                            flag1 = false;
                            dmrbell("0000", "0141");
                            bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
                            bxdzt.setBackgroundResource(R.drawable.btn_bg_round_click);
                            bxd.setText("停止");
                            bxdzt.setText("停止");
//                            bxd.setEnabled(true);
                            bzs.setEnabled(true);
                            bcx.setEnabled(true);
                            bxdzt.setEnabled(false);

                        }
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_xdzt:
                zsflag1 = sp.getBoolean("zsflag", Boolean.parseBoolean(""));
                xdflag1 = sp.getBoolean("xdflag", Boolean.parseBoolean(""));
                cxflag1 = sp.getBoolean("cxflag", Boolean.parseBoolean(""));
                count = 0;
                if (isFlag1) {
                    if (!xdflag) {
                        bxdzt.setEnabled(false);
                    } else {
                        if (state) {
                            state = false;
                            dmrbell("0001", "0142");
                            bxdzt.setBackgroundResource(R.drawable.btn_bg_round_click2);
                            bxdzt.setText("运行中");

                            bxd.setEnabled(true);

                            bcx.setEnabled(false);
                            bzs.setEnabled(false);


                        } else {
                            state = true;
                            dmrbell("0000", "0142");
                            bxdzt.setBackgroundResource(R.drawable.btn_bg_round_click);
                            bxdzt.setText("停止");
                            bzs.setEnabled(true);
                            bcx.setEnabled(true);
                            bxd.setEnabled(true);

                        }
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cx:    //c
                zsflag1 = sp.getBoolean("zsflag", Boolean.parseBoolean(""));
                xdflag1 = sp.getBoolean("xdflag", Boolean.parseBoolean(""));
                cxflag1 = sp.getBoolean("cxflag", Boolean.parseBoolean(""));
                if (isFlag1) {
                    count = 0;
                    if (zsflag1 || xdflag1) {
                        bcx.setEnabled(false);
                    } else {
                        if (state) {
                            state = false;
                            dmrbell("0001", "0143");
                            bcx.setBackgroundResource(R.drawable.btn_bg_round_click2);
                            bcx.setText("运行中");
//                            bcx.setEnabled(true);
                            bzs.setEnabled(false);
                            bxd.setEnabled(false);

                        } else {
                            state = true;
                            dmrbell("0000", "0143");
                            bcx.setBackgroundResource(R.drawable.btn_bg_round_click);
                            bcx.setText("停止");

                            bzs.setEnabled(true);
                            bxd.setEnabled(true);

                        }
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_sjfs:
                if (isFlag1) {
                    count = 0;
                    dmrbell("0000", "0144");
                    bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    byjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bejfs.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bsjfs.setText("运行中");
                    byjfs.setText("关闭");
                    bejfs.setText("关闭");
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_yjfs:
                if (isFlag1) {
                    count = 0;
                    dmrbell("0001", "0144");
                    byjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    byjfs.setText("运行中");

                    bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
                    byjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bejfs.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bsjfs.setText("关闭");
                    byjfs.setText("运行中");
                    bejfs.setText("关闭");
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_ejfs:
                if (isFlag1) {
                    count = 0;
                    dmrbell("0002", "0144");
                    bejfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bejfs.setText("运行中");

                    bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
                    byjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bejfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bsjfs.setText("关闭");
                    byjfs.setText("关闭");
                    bejfs.setText("运行中");
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_yjms:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        byjms.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        dmrbell("0001", "0145");
                        byjms.setText("运行中");
                        state = false;
                    } else {
                        state = true;
                        byjms.setBackgroundResource(R.drawable.btn_bg_round_click);
                        byjms.setText("停止");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_yjjg:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(syjjg, "0146");
                        byjjg.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        byjjg.setText("保存");
                    } else {
                        state = true;
                        byjjg.setBackgroundResource(R.drawable.btn_bg_round_click);
                        byjjg.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_yjsz:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(syjcx, "0147");
                        byjsz.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        byjsz.setText("保存");
                    } else {
                        state = true;
                        byjsz.setBackgroundResource(R.drawable.btn_bg_round_click);
                        byjsz.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_xdxh:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(sxdxh, "0148");
                        bxdxh.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        bxdxh.setText("保存");
                    } else {
                        state = true;
                        bxdxh.setBackgroundResource(R.drawable.btn_bg_round_click);
                        bxdxh.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_xdjp:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(sxdjp, "0149");
                        bxdjp.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        bxdjp.setText("保存");
                    } else {
                        state = true;
                        bxdjp.setBackgroundResource(R.drawable.btn_bg_round_click);
                        bxdjp.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_xdcx:
                if (isFlag1) {
                    count = 0;
                    if (state) {
                        state = false;
                        dmrbell(sxdcx, "0150");
                        bxdcx.setBackgroundResource(R.drawable.btn_bg_round_click2);
                        bxdcx.setText("保存");
                    } else {
                        state = true;
                        bxdcx.setBackgroundResource(R.drawable.btn_bg_round_click);
                        bxdcx.setText("保存");
                    }
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;


            default:
                break;
        }
    }

    //gcc_add —— 从sp里面取数据
    public void getUserData4() {

//        getUserData1();  //解析心跳包并保存心跳包数据到sp

        Log.e("大菠萝", "开始从sp取心跳包数据-5555555555555555555555555555555555---------》");
        String strd1 = sp.getString("s14", ""); //原水电导
        String strd2 = sp.getString("s15", ""); //一级浓水电导
        String strd3 = sp.getString("s16", ""); //一级纯水电导
        String strd4 = sp.getString("s17", ""); //二级纯水电导
        String strd5 = sp.getString("s18", ""); //一级纯水流量
        String strd6 = sp.getString("s19", ""); //二级纯水流量
        String strd7 = sp.getString("s20", ""); //一级废水流量

        eysdd.setText(strd1);
        eyjnsdd.setText(strd2);
        eyjcsdd.setText(strd3);
        eejcsdd.setText(strd4);
        eyjcsll.setText(strd5);
        eejcsll.setText(strd6);
        eyjfsll.setText(strd7);

        String strtime1 = sp.getString("s1", ""); //年
        String strtime2 = sp.getString("s2", ""); //月
        String strtime3 = sp.getString("s3", ""); //日
        String strtime4 = sp.getString("s4", ""); //时
        String strtime5 = sp.getString("s5", ""); //分
        String strtime6 = sp.getString("s6", ""); //秒

//        time.setText(strtime1 + "年" + strtime2 + "月" + strtime3 + "日" + strtime4 + "时" + strtime5 + "分" + strtime6 + "秒");
        timed.setText(strtime3);
        times.setText(strtime4);
        timef.setText(strtime5);
        timey.setText(strtime2);
        timem.setText(strtime6);
        timen.setText(strtime1);

        String strtime0 = sp.getString("s13", ""); //设备运行时长
        if (strtime0.equals("") || strtime0.equals(null)) {
            Log.e(TAG, "设备运行时间为空");

//            time1.setText("123456");
        } else {
            Float value0 = Float.intBitsToFloat(Integer.valueOf(strtime0));

            String value1 = String.format("%1.2f", value0);
            time1.setText(String.valueOf(value1));
        }
        String str11 = sp.getString("s7", ""); //设备工作模式

        if (str11.equals("0")) {
            sjfsflag = true;
            bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bsjfs.setText("运行中");
            byjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
            bejfs.setBackgroundResource(R.drawable.btn_bg_round_click);
            byjfs.setText("停止");
            bejfs.setText("停止");
        }

        if (str11.equals("1")) {
            yjfsflag = true;
            byjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
            byjfs.setText("运行中");
            bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
            bejfs.setBackgroundResource(R.drawable.btn_bg_round_click);
            bejfs.setText("停止");
            bsjfs.setText("停止");
        }

        if (str11.equals("2")) {
            ejfsflag = true;
            bejfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bejfs.setText("运行中");
            bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
            byjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
            byjfs.setText("停止");
            bsjfs.setText("停止");
        }


        //gcc_add —— 设备运行状态解析
        String str22 = sp.getString("s8", ""); //设备运行状态
        String str33 = sp.getString("sbr", "");

        TC tc1 = new TC();
        String rw = tc1.hexString2binaryString(str33);
        String ns = StringUtils.trim(rw);
        System.out.println(ns);
        String ns0 = StringUtils.substring(ns, 1, 2); //bit15
        String ns1 = StringUtils.substring(ns, 2, 3); //bit14
        String ns2 = StringUtils.substring(ns, 3, 4); //bit13
        String ns3 = StringUtils.substring(ns, 4, 5); //bit12
        String ns4 = StringUtils.substring(ns, 5, 6); //bit11
        String ns5 = StringUtils.substring(ns, 6, 7); //bit10
        String ns6 = StringUtils.substring(ns, 7, 8); //bit9
        String ns7 = StringUtils.substring(ns, 8, 9); //bit8
        String ns8 = StringUtils.substring(ns, 9, 10); //bit7
        String ns9 = StringUtils.substring(ns, 10, 11); //bit6
        String ns10 = StringUtils.substring(ns, 11, 12); //bit5
        String ns11 = StringUtils.substring(ns, 12, 13); //bit4
        String ns12 = StringUtils.substring(ns, 13, 14); //bit3
        String ns13 = StringUtils.substring(ns, 14, 15); //bit2
        String ns14 = StringUtils.substring(ns, 15, 16); //bit1

        if (ns14.equals("1")) {
            zsflag = true;
            bzs.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bzs.setText("运行中");
//            bzs.setEnabled(true);
            bcx.setEnabled(false);
            bxd.setEnabled(false);

        } else {
            zsflag = false;
            bzs.setBackgroundResource(R.drawable.btn_bg_round_click);
            bzs.setText("停止");

            bzs.setEnabled(true);
            bcx.setEnabled(true);
            bxd.setEnabled(true);
        }

        if (ns13.equals("1")) {
            cxflag = true;
            bcx.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bcx.setText("运行中");

            bzs.setEnabled(false);
            bxd.setEnabled(false);
        } else {
            cxflag = false;
            bcx.setBackgroundResource(R.drawable.btn_bg_round_click);
            bcx.setText("停止");

            bcx.setEnabled(true);
            bzs.setEnabled(true);
            bxd.setEnabled(true);

        }

        if (ns12.equals("1")) {
            xdflag = true;
            bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bxd.setText("运行中");
            bcx.setEnabled(false);
            bzs.setEnabled(false);
            bxdzt.setEnabled(true);
        } else {
            xdflag = false;
            bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
            bxd.setText("停止");
            bxdzt.setBackgroundResource(R.drawable.btn_bg_round_click);
            bxdzt.setText("停止");
            bcx.setEnabled(true);
            bzs.setEnabled(true);
            bxd.setEnabled(true);
            bxdzt.setEnabled(false);
        }

        if (ns11.equals("1")) {
            fcflag = true;
            bfc.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bfc.setText("运行中");
        } else {
            fcflag = false;
            bfc.setBackgroundResource(R.drawable.btn_bg_round_click);
            bfc.setText("停止");
        }

        if (ns10.equals("1")) {
            yjcxflag = true;
            byjcx.setBackgroundResource(R.drawable.btn_bg_round_click2);
        } else {
            yjcxflag = false;
            byjcx.setBackgroundResource(R.drawable.btn_bg_round_click);
        }

        if (ns9.equals("1")) {
            jtztflag = true;
            bjtzt.setBackgroundResource(R.drawable.btn_bg_round_click2);
        } else {
            jtztflag = false;
            bjtzt.setBackgroundResource(R.drawable.btn_bg_round_click);
        }

//        if (ns8.equals("1")) {
//            kxflag = true;
//            bkx.setBackgroundResource(R.drawable.btn_bg_round_click2);
//        } else {
//            kxflag = false;
//            bkx.setBackgroundResource(R.drawable.btn_bg_round_click);
//        }

        saveAccount121();

        //gcc_add —— 设备报警状态1解析

        String str44 = sp.getString("scr", "");
        TC tc2 = new TC();
        String rw1 = tc2.hexString2binaryString(str44);
        String bj = StringUtils.trim(rw1);
        System.out.println(bj);

        String bj0 = StringUtils.substring(bj, 1, 2); //bit14
        String bj1 = StringUtils.substring(bj, 2, 3); //bit13
        String bj2 = StringUtils.substring(bj, 3, 4); //bit12
        String bj3 = StringUtils.substring(bj, 4, 5); //bit11
        String bj4 = StringUtils.substring(bj, 5, 6); //bit10
        String bj5 = StringUtils.substring(bj, 6, 7); //bit9
        String bj6 = StringUtils.substring(bj, 7, 8); //bit8
        String bj7 = StringUtils.substring(bj, 8, 9); //bit7
        String bj8 = StringUtils.substring(bj, 9, 10); //bit6
        String bj9 = StringUtils.substring(bj, 10, 11); //bit5
        String bj10 = StringUtils.substring(bj, 11, 12); //bit4
        String bj11 = StringUtils.substring(bj, 12, 13); //bit3
        String bj12 = StringUtils.substring(bj, 13, 14); //bit2
        String bj13 = StringUtils.substring(bj, 14, 15); //bit1
        String bj14 = StringUtils.substring(bj, 15, 16); //bit0

        if (fstate) {
            if (bj14.equals("1")) {
                ystr1 = "与 PLC 通讯故障";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                xjj.start();
            }
        }
        if (fstate) {
            if (bj13.equals("1")) {
                ystr2 = "平衡器水位过低";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                xjj.start();
            }
        }
        if (fstate) {
            if (bj12.equals("1")) {
                ystr3 = "  高压泵 1 出口压力过高";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                xjj.start();
            }
        }
        if (fstate) {
            if (bj11.equals("1")) {
                ystr4 = " 高压泵 2 出口压力过低";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                xjj.start();
            }
        }
        if (fstate) {
            if (bj10.equals("1")) {
                ystr5 = "高压泵 2 出口压力过高";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
            }
        }
        if (fstate) {
            if (bj9.equals("1")) {
                ystr6 = "反冲洗动作";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                xjj.start();
            }
        }
        if (fstate) {
            if (bj8.equals("1")) {
                ystr7 = " 原水进水下限";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
            }
        }
        if (fstate) {
            if (bj7.equals("1")) {
                ystr8 = " 二级产水电导异常";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                xjj.start();
            }
        }

        if (fstate) {
            if (bj14.equals("0") & bj13.equals("0") & bj12.equals("0") & bj11.equals("0") & bj10.equals("0") & bj9.equals("0") & bj8.equals("0") & bj7.equals("0")) {
                fstate = false;
            }
        } else {
            ystr1 = "";
            ystr2 = "";
            ystr3 = "";
            ystr4 = "";
            ystr5 = "";
            ystr6 = "";
            ystr7 = "";
            ystr8 = "";
            bbjzt.setText("无报警");
            bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click);
        }


        //gcc_add —— 调试中间状态1
        String str45 = sp.getString("sdr", "");
        TC tc5 = new TC();
        String rw2 = tc5.hexString2binaryString(str45);
        String bg = StringUtils.trim(rw2);
        System.out.println(bg);

        String bk0 = StringUtils.substring(bg, 0, 1); //bit15
        String bk1 = StringUtils.substring(bg, 1, 2); //bit14
        String bk2 = StringUtils.substring(bg, 2, 3); //bit13
        String bk3 = StringUtils.substring(bg, 3, 4); //bit12
        String bk4 = StringUtils.substring(bg, 4, 5); //bit11
        String bk5 = StringUtils.substring(bg, 5, 6); //bit10
        String bk6 = StringUtils.substring(bg, 6, 7); //bit9
        String bk7 = StringUtils.substring(bg, 7, 8); //bit8
        String bk8 = StringUtils.substring(bg, 8, 9); //bit7
        String bk9 = StringUtils.substring(bg, 9, 10); //bit6
        String bk10 = StringUtils.substring(bg, 10, 11); //bit5
        String bk11 = StringUtils.substring(bg, 11, 12); //bit4
        String bk12 = StringUtils.substring(bg, 12, 13); //bit3
        String bk13 = StringUtils.substring(bg, 13, 14); //bit2
        String bk14 = StringUtils.substring(bg, 14, 15); //bit1
        String bk15 = StringUtils.substring(bg, 15, 16); //bit0

//        if (state1) {
//            if (bk15.equals("1")) {
//                tstr1 = "双级反渗模式";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk14.equals("1")) {
//                tstr2 = "一级反渗模式";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk13.equals("1")) {
//                tstr3 = "二级反渗模式";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk12.equals("1")) {
//                tstr4 = "制    水";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk11.equals("1")) {
//                tstr5 = "冲    洗";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk10.equals("1")) {
//                tstr6 = "消    毒";
//                bbjzt.setText("有状态");
//                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk9.equals("1")) {
//                tstr7 = "消毒暂停";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk8.equals("1")) {
//                tstr8 = "添加消毒液标志";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk7.equals("1")) {
//                tstr9 = "定时反冲";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk6.equals("1")) {
//                tstr10 = "夜间工作模式";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk5.equals("1")) {
//                tstr11 = "添加消毒液提示";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk4.equals("1")) {
//                tstr12 = "工作中";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk3.equals("1")) {
//                tstr13 = "原水泵控制";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk2.equals("1")) {
//                tstr14 = "一级高压泵控制";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk1.equals("1")) {
//                tstr15 = "二级高压泵控制";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//        }
//        if (state1) {
//            if (bk0.equals("1")) {
//                tstr15 = " 二级高压泵控制";
//                bzjzt1.setText("有状态");
//                bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click3);
//            }
//
//        }

//        if (state1) {
//            if (bk15.equals("0") & bk14.equals("0") & bk13.equals("0") & bk12.equals("0") & bk11.equals("0") & bk10.equals("0") & bk9.equals("0") & bk8.equals("0") & bk7.equals("0") & bk6.equals("0") & bk5.equals("0") & bk4.equals("0") & bk3.equals("0") & bk2.equals("0") & bk1.equals("0")) {
//                state1 = false;
//            }
//        } else {
//            tstr1 = "";
//            tstr2 = "";
//            tstr3 = "";
//            tstr4 = "";
//            tstr5 = "";
//            tstr6 = "";
//            tstr7 = "";
//            tstr8 = "";
//            tstr9 = "";
//            tstr10 = "";
//            tstr11 = "";
//            tstr12 = "";
//            tstr13 = "";
//            tstr14 = "";
//            tstr15 = "";
//            bzjzt1.setText("无状态");
//            bzjzt1.setBackgroundResource(R.drawable.btn_bg_round_click);
//        }

        //gcc_end


        //数据 —— 报警状态
        data_list = new ArrayList<String>();
        data_list.add(ystr9);

        if (ystr1 == null) {
            data_list.add("");
        } else {
            data_list.add(ystr1);
        }
        if (ystr2 == null) {
            data_list.add("");
        } else {
            data_list.add(ystr2);
        }
        if (ystr3 == null) {
            data_list.add("");
        } else {
            data_list.add(ystr3);
        }
        if (ystr4 == null) {
            data_list.add("");
        } else {
            data_list.add(ystr4);
        }
        if (ystr5 == null) {
            data_list.add("");
        } else {
            data_list.add(ystr5);
        }
        if (ystr6 == null) {
            data_list.add("");
        } else {
            data_list.add(ystr6);
        }
        if (ystr7 == null) {
            data_list.add("");
        } else {
            data_list.add(ystr7);
        }
        if (ystr8 == null) {
            data_list.add("");
        } else {
            data_list.add(ystr8);
        }

        arr_adapter = new ArrayAdapter<String>(RTDActivity.this, android.R.layout.simple_spinner_item, data_list);
//        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器
        spinner.setAdapter(arr_adapter);
//
//        //gcc_add —— 调试中间状态集合1
//        //数据
//        data_list1 = new ArrayList<String>();
//        data_list1.add(tstr16);
//
//        if (tstr1 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr1);
//        }
//        if (tstr2 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr2);
//        }
//        if (tstr3 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr3);
//        }
//        if (tstr4 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr4);
//        }
//        if (tstr5 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr5);
//        }
//        if (tstr6 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr6);
//        }
//        if (tstr7 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr7);
//        }
//        if (tstr8 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr8);
//        }
//        if (tstr9 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr9);
//        }
//        if (tstr10 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr10);
//        }
//        if (tstr11 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr11);
//        }
//        if (tstr12 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr12);
//        }
//        if (tstr13 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr13);
//        }
//        if (tstr14 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr14);
//        }
//        if (tstr15 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr15);
//        }
//        if (tstr16 == null) {
//            data_list1.add("");
//        } else {
//            data_list1.add(tstr16);
//        }

//        arr_adapter1 = new ArrayAdapter<String>(RTDActivity.this, android.R.layout.simple_spinner_item, data_list1);
////        //设置样式
//        arr_adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        //加载适配器
//        spinner1.setAdapter(arr_adapter1);
        //gcc_end


//        //gcc_add —— 调试中间状态集合2
//        //数据
//        data_list2 = new ArrayList<String>();
//        data_list2.add(zstr9);
//
//        if (zstr1 == null) {
//            data_list2.add("");
//        } else {
//            data_list2.add(zstr1);
//        }
//        if (zstr2 == null) {
//            data_list2.add("");
//        } else {
//            data_list2.add(zstr2);
//        }
//        if (zstr3 == null) {
//            data_list2.add("");
//        } else {
//            data_list2.add(zstr3);
//        }
//        if (zstr4 == null) {
//            data_list2.add("");
//        } else {
//            data_list2.add(zstr4);
//        }
//        if (zstr5 == null) {
//            data_list2.add("");
//        } else {
//            data_list2.add(zstr5);
//        }
//        if (zstr6 == null) {
//            data_list2.add("");
//        } else {
//            data_list2.add(zstr6);
//        }
//        if (zstr7 == null) {
//            data_list2.add("");
//        } else {
//            data_list2.add(zstr7);
//        }
//        if (zstr8 == null) {
//            data_list2.add("");
//        } else {
//            data_list2.add(zstr8);
//        }
//
//        arr_adapter2 = new ArrayAdapter<String>(RTDActivity.this, android.R.layout.simple_spinner_item, data_list2);
////        //设置样式
//        arr_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        //加载适配器
//        spinner2.setAdapter(arr_adapter2);
//        //gcc_end
//
//        //gcc_add —— 调试中间状态集合6
//        //数据
//        data_list6 = new ArrayList<String>();
//        data_list6.add(jstr9);
//
//        if (jstr1 == null) {
//            data_list6.add("");
//        } else {
//            data_list6.add(jstr1);
//        }
//        if (jstr2 == null) {
//            data_list6.add("");
//        } else {
//            data_list6.add(jstr2);
//        }
//        if (jstr3 == null) {
//            data_list6.add("");
//        } else {
//            data_list6.add(jstr3);
//        }
//        if (jstr4 == null) {
//            data_list6.add("");
//        } else {
//            data_list6.add(jstr4);
//        }
//        if (jstr5 == null) {
//            data_list6.add("");
//        } else {
//            data_list6.add(jstr5);
//        }
//        if (jstr6 == null) {
//            data_list6.add("");
//        } else {
//            data_list6.add(jstr6);
//        }
//        if (jstr7 == null) {
//            data_list6.add("");
//        } else {
//            data_list6.add(jstr7);
//        }
//        if (jstr8 == null) {
//            data_list6.add("");
//        } else {
//            data_list6.add(jstr8);
//        }
//
//        arr_adapter6 = new ArrayAdapter<String>(RTDActivity.this, android.R.layout.simple_spinner_item, data_list6);
////        //设置样式
//        arr_adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////        //加载适配器
//        spinner6.setAdapter(arr_adapter6);
//        //gcc_end

        //gcc_add
        String stry1 = sp.getString("s49", ""); //夜间冲洗时间间隔
        String stry2 = sp.getString("s50", ""); //夜间冲洗时间设置
        String stry3 = sp.getString("s51", ""); //消毒循环时间设置
        String stry4 = sp.getString("s52", ""); //消毒浸泡时间设置
        String stry5 = sp.getString("s53", ""); //消毒冲洗时间设置

        eyjjg.setText(stry1);
        eyjcx.setText(stry2);
        exdxh.setText(stry3);
        exdjp.setText(stry4);
        exdcx.setText(stry5);

        Log.e("大菠萝", "显示心跳包数据完成-6666666666666666666666666666---------》");
    }

    //通用接口
    public void dmrbell(String content, String startadd) {

//        TC tc3 = new TC();

//        String content1 = tc3.string2HexString(content);

        ZhiShui zhiShui1 = new ZhiShui();
        zhiShui1.setCommond("0110"); //固定指令，当前远程控制0110
//        zhiShui1.setContent(tc3.string2HexString(content)); //远程控制内容
        zhiShui1.setContent(content);
        zhiShui1.setDate(String.valueOf(new Date().getTime()));
        zhiShui1.setDeviceNum(lks); // 设备号
        zhiShui1.setEtypecode(Contants.ETYPECODE_SJ); //设备类型
        zhiShui1.setIemi(imei);
        zhiShui1.setNum("0001"); //修改寄存器数量
        zhiShui1.setNumLen("02"); //修改字节长度
        zhiShui1.setPhone(phonenumber);
        zhiShui1.setStartAdd(startadd); //远程修改寄存器起始地址
        zhiShui1.setUid(uid);
//        String json = JSON.toJSONString(zhiShui1);
        Gson gson = new Gson();
        String obj2 = gson.toJson(zhiShui1);

        Log.e(TAG, "M416+AWM——————————》  " + obj2);
        OkHttpUtils3 okHttpUtils = new OkHttpUtils3(this);
        okHttpUtils.post(WebUrls.getcustomurl, obj2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xjj.stop();
        mTimer.cancel();//取消任务
        handler.removeCallbacks(mTimerTask);//取消任务

    }

}
