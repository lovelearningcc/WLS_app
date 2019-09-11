package com.wls.jzgy.functions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.wls.jzgy.R;

import com.wls.jzgy.entity.ConsTant;
import com.wls.jzgy.entity.ZhiShui;
import com.wls.jzgy.fragment.ConsActivity;
import com.wls.jzgy.fragment.RinseActivity;
import com.wls.jzgy.http.NewRequestManager;
import com.wls.jzgy.http.OkHttpUtils3;
import com.wls.jzgy.http.ReqCallBack;
import com.wls.jzgy.persions.SystimeActivity;
import com.wls.jzgy.utils.TC;
import com.wls.jzgy.utils.WebUrls;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class RTDActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_bar_title, myeid, mytype;

    private String aql, lks;
    private ImageView back;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TextView timed, times, timef, timey, timem, timen;

    private Button bzs, bxd, bxd1, bcx, bfc, byjcx, bjtzt, bkx, byjfs, bejfs, bsjfs, bysdd, byjnsdd, byjcsdd, bejcsdd, byjcsll, bejcsll, byjfsll, byjms, byjjg, byjsz, bxdxh, bxdjp, bxdcx;
    private EditText eysdd, eyjnsdd, eyjcsdd, eejcsdd, eyjcsll, eejcsll, eyjfsll, eyjjg, eyjsz, exdxh, exdjp, exdcx;
    private String sysdd, syjnsdd, syjcsdd, sejcsdd, syjcsll, sejcsll, syjfsll, syjjg, syjsz, sxdxh, sxdjp, sxdcx;

    private String ystr1, ystr2, ystr3, ystr4, ystr5, ystr6, ystr7, ystr8, ystr9 = "点击查看报警信息", ystr10, ystr11, ystr12, ystr13, ystr14, ystr15;

    private int count = 100;
    private boolean state = true;
    private boolean state1 = true;
    private boolean gflag, cflag, zflag, yflag;
    private boolean jflag, fflag, hflag;

    private int jby;

    private Button bxg_devtime;
    private static final String TAG = "RTDActivity";

    private int dbl;

    //gcc_add —— 下拉菜单
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    private Button bbjzt;

    //zy_and

    private Date date, date2;
    private String date1, date3, dtm;
    private int js1, js2;

    //gcc_add_报警声音
    private MediaPlayer mn;

    private Timer mTimer = null;
    private Timer nTimer = null;
    private TimerTask mTimerTask = null;
    private TimerTask nTimerTask = null;
    private static int delay = 0;  //1s
    private static int period = 6000;  //6s
    private boolean isPause = true;
    private boolean isStop = true;

    private boolean flag1, flag2;


    List<String> clist = new ArrayList<String>();

    //emd
    private String beid;

    private String phonenumber, imei, uid;
    private String bzw;

    private boolean isFlag1;

    //gcc_add —— 心跳包

    private View ll_sj, ll_jzgy;
    private Button bsj, bjzgy;
    public static boolean select = false;
    public static final int yasuo = 666666;


    private LinearLayout back1;

    private String eid, id, msg, timestamps, ms;

    private String value = null;
    private JSONObject jsonObject;
    private TextView zs, xd, cx, fc, yjcx, jtzt, kx, time, baojing, time1, update, yjfs, ejfs, sjfs, ysdd, yjnsdd, yjcsdd, ejcsdd, yjcsll, ejcsll, yjfsll;

    private int m, n, j, k, l, o, h, ab, ac, ad, ae, af, ag, ah, ai, aj, ak, al, am, an, ao, ap, aq, ar, as, at, au, av, aw, ax, ay, az, aaz, abc, abd, abe, abf, abg, abh, abi, abj, abk, abl, abm, abn, abo, abp, abq, abr, abs, abt, abu, abv, abw, abx, aby, abz;
    private int ya1, ya2, ya3, ya5, ya6, ya7, ya8, ya9, ya10, ya11, ya12, ya13, ya14, ya15, ya16, ya17;
    private String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, s18, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30, s31, s32, s33, s34, s35, s36, s37, s38, s39, s40, s41, s42, s43, s44, s45, s46, s47, s48, s49, s50, s51, s52, s53, s54, s55, s56, s57, s58, s59, s60;
    private String suo1, suo2, suo3, suo4, suo5, suo6, suo7, suo8, suo9, suo10, suo11, suo12, suo13, suo14;
    private String sbr, scr;

    //集中供液
    private Button bpy, bjb, bgz, bpkjbt, bxy, bhg, bgybzt, bgftlx, bpk, bqx, bqxjbt, bpkcyt;
    //gcc_end

    private LinearLayout peiy, qingx;

//    private AlertDialogConfirmTime dialogConfirm;

    //gcc_add__逻辑判断
    private boolean pyflag;
    private boolean jbflag;
    private boolean gzflag;
    private boolean pkjbtflag;
    private boolean xyflag;
    private boolean hgztflag;
    private boolean gybflag;
    private boolean gftlxflag;
    private boolean pkflag;
    private boolean qxflag;
    private boolean xdflag;
    private boolean qxjbtflag;
    private boolean pkcytflag;


    private boolean pyflag1;
    private boolean jbflag1;
    private boolean gzflag1;
    private boolean pkjbtflag1;
    private boolean xyflag1;
    private boolean hgztflag1;
    private boolean gybflag1;
    private boolean gftlxflag1;
    private boolean pkflag1;
    private boolean qxflag1;
    private boolean xdflag1;
    private boolean qxjbtflag1;
    private boolean pkcytflag1;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd- HH:mm:ss");// HH:mm:ss
        date = new Date(System.currentTimeMillis());
        date1 = simpleDateFormat.format(date);


        mn = MediaPlayer.create(this, R.raw.dong);


        sp = getSharedPreferences("xtb_data", Context.MODE_PRIVATE);
        editor = sp.edit();

        lks = sp.getString("eid1", "");
        aql = sp.getString("type1", "");

        Log.e(TAG, "拉克丝的值为——————————》》》" + lks);

        phonenumber = sp.getString("akm", "");
        imei = sp.getString("ump9", "");
        uid = sp.getString("uid", "");
        isFlag1 = sp.getBoolean("sd", Boolean.parseBoolean(""));
        System.out.println("66666666666");
        Log.e(TAG, "标志位的值为----------》" + isFlag1);

        init();
        startTimer();

        //activity跳转到fragment
        peiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RTDActivity.this, ConsActivity.class));
            }
        });
        qingx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RTDActivity.this, RinseActivity.class));
            }
        });
    }

    public void saveAccount121() {
        //设备系统时间
//        editor.putString("", jby); //年

        editor.putBoolean("gflag", gflag);
        editor.putBoolean("cflag", cflag);
        editor.putBoolean("zflag", zflag);
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

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            Log.e("QQ------------->>", "开始请求数据");
            switch (msg.what) {

                case 1:
                    if (count >= 2) {

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

//            ystr10 = timed.getText().toString().trim();
//            ystr11 = times.getText().toString().trim();
//            ystr12 = timef.getText().toString().trim();
//            ystr13 = timey.getText().toString().trim();
//            ystr14 = timem.getText().toString().trim();
//            ystr15 = timen.getText().toString().trim();
        }
    };
    //gcc_end

    public void init() {

        peiy = findViewById(R.id.ll_peiy);
        qingx = findViewById(R.id.ll_qingx);

        myeid = findViewById(R.id.tv_my_eid);
        mytype = findViewById(R.id.tv_my_type);

        myeid.setText(lks);
        mytype.setText(aql);


        title_bar_title = findViewById(R.id.tv_title_bar_title);
        title_bar_title.setText("实时数据及远程控制");

        spinner = (Spinner) findViewById(R.id.spinner); // 下拉菜单
        bbjzt = findViewById(R.id.btn_bjzt);  //报警状态

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

        bpy = findViewById(R.id.btn_py); //配液
        bjb = findViewById(R.id.btn_jb); //搅拌
        bgz = findViewById(R.id.btn_gz); //灌注
        bpkjbt = findViewById(R.id.btn_pkjbt);  //排空搅拌桶
        bxy = findViewById(R.id.btn_xy); //消音
        bhg = findViewById(R.id.btn_hg); //回灌
        bgybzt = findViewById(R.id.btn_gybzt);   //供液泵状态
        bgftlx = findViewById(R.id.btn_gftlx);   //A/B 干粉桶类型
        bpk = findViewById(R.id.btn_pk); //排空
        bqx = findViewById(R.id.btn_qx); //清洗
        bxd = findViewById(R.id.btn_xd); //消毒
        bqxjbt = findViewById(R.id.btn_qxjbt);  //清洗搅拌桶
        bpkcyt = findViewById(R.id.btn_pkcyt);  //排空储液桶


        bpy.setOnClickListener(this);
        bjb.setOnClickListener(this);
        bgz.setOnClickListener(this);
        bpkjbt.setOnClickListener(this);
        bxy.setOnClickListener(this);
        bhg.setOnClickListener(this);
        bgybzt.setOnClickListener(this);
        bgftlx.setOnClickListener(this);

        bpk.setOnClickListener(this);
        bqx.setOnClickListener(this);
        bxd.setOnClickListener(this);
        bqxjbt.setOnClickListener(this);
        bpkcyt.setOnClickListener(this);

        bxg_devtime.setOnClickListener(this);
    }


    //gcc_add —— 获取心跳包数据并保存到sp里面
    public void getUserData1() {

        Log.e("大菠萝", "开始请求心跳包数据-111111111111111111111111111---------》");

        String url = "";
        url = WebUrls.getrealtimedata;


        HashMap<String, String> mapDay = new HashMap<>();
        mapDay.put("date", String.valueOf(new Date().getTime()));
        mapDay.put("eid", lks);
        mapDay.put("eypecode", ConsTant.ETYPECODE_JZGY);

        Log.e(TAG, "mapday的值为----------》" + mapDay);

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
                            msg = re.getString("msg");
                            timestamps = re.getString("timestamps");
                            ms = re.getString("data");
                            JSONObject jsonObject = new JSONObject(ms);
                            value = jsonObject.getString("data");
                            System.out.println("value1=" + value);

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

                            String str = tc.testStringBuilder(str0, str1); //年
                            String sttr = tc.testStringBuilder(str4, str5); //时

                            m = Integer.parseInt(str, 16);  //年
                            n = Integer.parseInt(str2, 16); //月
                            j = Integer.parseInt(str3, 16); //日
                            k = Integer.parseInt(sttr, 16); //时
                            o = Integer.parseInt(str6, 16); //分
                            h = Integer.parseInt(str7, 16); //秒


//                            s1 = String.valueOf(m); //年
//                            s2 = String.valueOf(n); //月
//                            s3 = String.valueOf(j); //日
//                            s4 = String.valueOf(k); //时
//                            s5 = String.valueOf(o); //分
//                            s6 = String.valueOf(h); //秒

                            s1 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 0), tc.test(value, 1)), 16));
                            s2 = String.valueOf(Integer.parseInt(tc.test(value, 2), 16));
                            s3 = String.valueOf(Integer.parseInt(tc.test(value, 3), 16));
                            s4 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 4), tc.test(value, 5)), 16));
                            s5 = String.format("%02d", Integer.parseInt(tc.test(value, 6), 16));
                            s6 = String.format("%02d", Integer.parseInt(tc.test(value, 7), 16));

//                            s5.format("0","00");

//                            time.setText(s1 + "年" + s2 + "月" + s3 + "日" + s4 + "时" + s5 + "分" + s6 + "秒");
                            //gcc_end

                            //gcc_add —— 设备工作模式解析
                            String str8 = tc.test(value, 10); // 工作模式
                            String str9 = tc.test(value, 11); // 工作模式

                            String sar = tc.testStringBuilder(str8, str9); // 工作模式
                            ab = Integer.parseInt(sar, 16);  // 工作模式

//                            s7 = String.valueOf(ab); // 运行模式
                            s7 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 10), tc.test(value, 11)), 16));
                            //end

                            //gcc_add —— 设备运行状态解析
                            String str10 = tc.test(value, 12); // 运行状态
                            String str11 = tc.test(value, 13); // 运行状态


                            sbr = tc.testStringBuilder(str10, str11); // 运行状态
                            ac = Integer.parseInt(sbr, 16);  // 运行状态
//                            s8 = String.valueOf(ac); // 运行状态
                            s8 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 12), tc.test(value, 13)), 16));

                            TC tc1 = new TC();
                            String rw = tc.hexString2binaryString(sbr);

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
                            ad = Integer.parseInt(scr, 16);  // 报警状态1
//                            s9 = String.valueOf(ad); // 报警状态1
                            s9 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 14), tc.test(value, 15)), 16));
                            String sdr = tc.testStringBuilder(str14, str15); // 报警状态2
                            ae = Integer.parseInt(sdr, 16);  // 报警状态2
//                            s10 = String.valueOf(ae); // 报警状态2
                            s10 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 16), tc.test(value, 17)), 16));
                            String ser = tc.testStringBuilder(str16, str17); // 报警状态3
                            af = Integer.parseInt(ser, 16);  // 报警状态3
//                            s11 = String.valueOf(af); // 报警状态3
                            s11 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 18), tc.test(value, 19)), 16));
                            String sfr = tc.testStringBuilder(str18, str19); // 报警状态4
                            ag = Integer.parseInt(sfr, 16);  // 报警状态4
//                            s12 = String.valueOf(ag); // 报警状态4
                            s12 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 20), tc.test(value, 21)), 16));
                            //end

                            //gcc_add —— 设备运行时长解析
                            String str20 = tc.test(value, 22); // 设备运行时长
                            String str21 = tc.test(value, 23); // 设备运行时长
                            String str22 = tc.test(value, 24); // 设备运行时长
                            String str23 = tc.test(value, 25); // 设备运行时长

                            String sgr = tc.testStringBuilder1(str20, str21, str22, str23); // 设备运行时长
                            if (sgr.equals("") || sgr.equals(null)) {
                                Log.e(TAG, "暂无在线设备------------->>>");
                            } else {
                                ah = Integer.parseInt(sgr, 16);  // 设备运行时长
                                s13 = String.valueOf(ah); // 设备运行时长
                                Log.e(TAG, "设备运行时长为" + s13);
//                            time1.setText(s13);
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

                            String shr = tc.testStringBuilder(str24, str25); //原水电导
                            String sir = tc.testStringBuilder(str26, str27); //一级浓水电导
                            String sjr = tc.testStringBuilder(str28, str29); //一级纯水电导
                            String skr = tc.testStringBuilder(str30, str31); //二级纯水电导

                            ai = Integer.parseInt(shr, 16); //原水电导
                            aj = Integer.parseInt(sir, 16); //一级浓水电导
                            ak = Integer.parseInt(sjr, 16); //一级纯水电导
                            al = Integer.parseInt(skr, 16); //二级纯水电导

//                            s14 = String.valueOf(ai); //原水电导
//                            s15 = String.valueOf(aj); //一级浓水电导
//                            s16 = String.valueOf(ak); //一级纯水电导
//                            s17 = String.valueOf(al); //二级纯水电导
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

                            String slr = tc.testStringBuilder(str32, str33); //一级纯水流量
                            String smr = tc.testStringBuilder(str34, str35); //二级纯水流量
                            String snr = tc.testStringBuilder(str36, str37); //一级废水流量

                            am = Integer.parseInt(slr, 16); //一级纯水流量
                            an = Integer.parseInt(smr, 16); //二级纯水流量
                            ao = Integer.parseInt(snr, 16); //一级废水流量

//                            s18 = String.valueOf(am); //一级纯水流量
//                            s19 = String.valueOf(an); //一二级纯水流量
//                            s20 = String.valueOf(ao); //一级废水流量
                            s18 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 34), tc.test(value, 35)), 16));
                            s19 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 36), tc.test(value, 37)), 16));
                            s20 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 38), tc.test(value, 39)), 16));

//                            yjcsll.setText(s18);
//                            ejcsll.setText(s19);
//                            yjfsll.setText(s20);
                            //end


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
//                            ya1 = Integer.parseInt(sn1, 16); //周一使能
//                            suo1 = String.valueOf(ya1); //周一使能

                            String sor = tc.testStringBuilder(str54, str55); //周一时
                            String spr = tc.testStringBuilder(str56, str57); //周一分
                            ap = Integer.parseInt(sor, 16); //周一时
                            aq = Integer.parseInt(spr, 16); //周一分
//                            s21 = String.valueOf(ap); //周一时
//                            s22 = String.valueOf(aq); //周一分
                            s21 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 92), tc.test(value, 93)), 16));
                            s22 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 94), tc.test(value, 95)), 16));


                            String str58 = tc.test(value, 96);  //周二使能
                            String str59 = tc.test(value, 97);  //周二使能
                            String str60 = tc.test(value, 98);  //周二时
                            String str61 = tc.test(value, 99);  //周二时
                            String str62 = tc.test(value, 100);  //周二分
                            String str63 = tc.test(value, 101);  //周二分

                            suo2 = tc.testStringBuilder(str58, str59); //周二使能

                            String sqr = tc.testStringBuilder(str60, str61); //周二时
                            String srr = tc.testStringBuilder(str62, str63); //周二分
                            ar = Integer.parseInt(sqr, 16); //周二时
                            as = Integer.parseInt(srr, 16); //周二分
//                            s23 = String.valueOf(ar); //周二时
//                            s24 = String.valueOf(as); //周二分
                            s23 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 98), tc.test(value, 99)), 16));
                            s24 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 100), tc.test(value, 101)), 16));


                            String str64 = tc.test(value, 102);  //周三使能
                            String str65 = tc.test(value, 103);  //周三使能
                            String str66 = tc.test(value, 104);  //周三时
                            String str67 = tc.test(value, 105);  //周三时
                            String str68 = tc.test(value, 106);  //周三分
                            String str69 = tc.test(value, 107);  //周三分

                            suo3 = tc.testStringBuilder(str64, str65); //周三使能

                            String ssr = tc.testStringBuilder(str66, str67); //周三时
                            String sur = tc.testStringBuilder(str68, str69); //周三分
                            at = Integer.parseInt(ssr, 16); //周三时
                            au = Integer.parseInt(sur, 16); //周三分
//                            s25 = String.valueOf(ar); //周三时
//                            s26 = String.valueOf(as); //周三分
                            s25 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 104), tc.test(value, 105)), 16));
                            s26 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 106), tc.test(value, 107)), 16));


                            String str71 = tc.test(value, 108);  //周四使能
                            String str72 = tc.test(value, 109);  //周四使能
                            String str73 = tc.test(value, 110);  //周四时
                            String str74 = tc.test(value, 111);  //周四时
                            String str75 = tc.test(value, 112);  //周四分
                            String str76 = tc.test(value, 113);  //周四分

                            suo4 = tc.testStringBuilder(str71, str72); //周四使能

                            String svr = tc.testStringBuilder(str73, str74); //周四时
                            String swr = tc.testStringBuilder(str75, str76); //周四分
                            av = Integer.parseInt(svr, 16); //周四时
                            aw = Integer.parseInt(swr, 16); //周四分
//                            s27 = String.valueOf(av); //周四时
//                            s28 = String.valueOf(aw); //周四分
                            s27 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 110), tc.test(value, 111)), 16));
                            s28 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 112), tc.test(value, 113)), 16));


                            String str77 = tc.test(value, 114);  //周五使能
                            String str78 = tc.test(value, 115);  //周五使能
                            String str79 = tc.test(value, 116);  //周五时
                            String str791 = tc.test(value, 117);  //周五时
                            String str80 = tc.test(value, 118);  //周五分
                            String str81 = tc.test(value, 119);  //周五分

                            suo5 = tc.testStringBuilder(str77, str78); //周五使能

                            String sxr = tc.testStringBuilder(str79, str791); //周五时
                            String syr = tc.testStringBuilder(str80, str81); //周五分
                            ax = Integer.parseInt(sxr, 16); //周五时
                            ay = Integer.parseInt(syr, 16); //周五分
//                            s29 = String.valueOf(ax); //周五时
//                            s30 = String.valueOf(ay); //周五分
                            s29 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 116), tc.test(value, 117)), 16));
                            s30 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 118), tc.test(value, 119)), 16));

                            String str103 = tc.test(value, 120);  //周六使能
                            String str104 = tc.test(value, 121);  //周六使能
                            String str105 = tc.test(value, 122);  //周六时
                            String str106 = tc.test(value, 123);  //周六时
                            String str107 = tc.test(value, 124);  //周六分
                            String str108 = tc.test(value, 125);  //周六分

                            suo6 = tc.testStringBuilder(str103, str104); //周六使能

                            String szr = tc.testStringBuilder(str105, str106); //周六时
                            String szzr = tc.testStringBuilder(str107, str108); //周六分
                            az = Integer.parseInt(szr, 16); //周六时
                            aaz = Integer.parseInt(szzr, 16); //周六分
//                            s31 = String.valueOf(az); //周六时
//                            s32 = String.valueOf(aaz); //周六分
                            s31 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 122), tc.test(value, 123)), 16));
                            s32 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 124), tc.test(value, 125)), 16));


                            String str109 = tc.test(value, 126);  //周天使能
                            String str110 = tc.test(value, 127);  //周天使能
                            String str111 = tc.test(value, 128);  //周天时
                            String str112 = tc.test(value, 129);  //周天时
                            String str113 = tc.test(value, 130);  //周天分
                            String str114 = tc.test(value, 131);  //周天分

                            suo7 = tc.testStringBuilder(str109, str110); //周一使能

                            String saar = tc.testStringBuilder(str111, str112); //周天时
                            String sabr = tc.testStringBuilder(str113, str114); //周天分
                            abc = Integer.parseInt(saar, 16); //周天时
                            abd = Integer.parseInt(sabr, 16); //周天分
//                            s33 = String.valueOf(abc); //周天时
//                            s34 = String.valueOf(abd); //周天分

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

                            String sacr = tc.testStringBuilder(str123, str124); //周一时
                            String sadr = tc.testStringBuilder(str125, str126); //周一分
                            abe = Integer.parseInt(sacr, 16); //周一时
                            abf = Integer.parseInt(sadr, 16); //周一分
//                            s35 = String.valueOf(abe); //周一时
//                            s36 = String.valueOf(abf); //周一分

//                            s36 = String.format("%02d", abf);

                            s35 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 140), tc.test(value, 141)), 16));
                            s36 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 142), tc.test(value, 143)), 16));

                            String str127 = tc.test(value, 144);  //周二使能
                            String str128 = tc.test(value, 145);  //周二使能
                            String str129 = tc.test(value, 146);  //周二时
                            String str130 = tc.test(value, 147);  //周二时
                            String str131 = tc.test(value, 148);  //周二分
                            String str132 = tc.test(value, 149);  //周二分

                            suo9 = tc.testStringBuilder(str127, str128); //周二使能

                            String saer = tc.testStringBuilder(str129, str130); //周二时
                            String safr = tc.testStringBuilder(str131, str132); //周二分
                            abg = Integer.parseInt(saer, 16); //周二时
                            abh = Integer.parseInt(safr, 16); //周二分
//                            s37 = String.valueOf(abg); //周二时
//                            s38 = String.valueOf(abh); //周二分
//                            s38 = String.format("%02d", abh);
                            s37 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 146), tc.test(value, 147)), 16));
                            s38 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 148), tc.test(value, 149)), 16));

                            String str133 = tc.test(value, 150);  //周三使能
                            String str134 = tc.test(value, 151);  //周三使能
                            String str135 = tc.test(value, 152);  //周三时
                            String str136 = tc.test(value, 153);  //周三时
                            String str137 = tc.test(value, 154);  //周三分
                            String str1371 = tc.test(value, 155);  //周三分

                            suo10 = tc.testStringBuilder(str133, str134); //周三使能

                            String sagr = tc.testStringBuilder(str135, str136); //周三时
                            String sahr = tc.testStringBuilder(str137, str1371); //周三分
                            abi = Integer.parseInt(sagr, 16); //周三时
                            abj = Integer.parseInt(sahr, 16); //周三分
//                            s39 = String.valueOf(abi); //周三时
//                            s40 = String.valueOf(abj); //周三分
//                            s40 = String.format("%02d", abj);
                            s39 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 152), tc.test(value, 153)), 16));
                            s40 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 154), tc.test(value, 155)), 16));

                            String str138 = tc.test(value, 156);  //周四使能
                            String str139 = tc.test(value, 157);  //周四使能
                            String str140 = tc.test(value, 158);  //周四时
                            String str141 = tc.test(value, 159);  //周四时
                            String str142 = tc.test(value, 160);  //周四分
                            String str143 = tc.test(value, 161);  //周四分

                            suo11 = tc.testStringBuilder(str138, str139); //周四使能

                            String sair = tc.testStringBuilder(str140, str141); //周四时
                            String sajr = tc.testStringBuilder(str142, str143); //周四分
                            abk = Integer.parseInt(sair, 16); //周四时
                            abl = Integer.parseInt(sajr, 16); //周四分
//                            s41 = String.valueOf(abk); //周四时
//                            s42 = String.valueOf(abl); //周四分
//                            s42 = String.format("%02d", abl);
                            s41 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 158), tc.test(value, 159)), 16));
                            s42 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 160), tc.test(value, 161)), 16));
                            String str144 = tc.test(value, 162);  //周五使能
                            String str145 = tc.test(value, 163);  //周五使能
                            String str146 = tc.test(value, 164);  //周五时
                            String str147 = tc.test(value, 165);  //周五时
                            String str148 = tc.test(value, 166);  //周五分
                            String str149 = tc.test(value, 167);  //周五分

                            suo12 = tc.testStringBuilder(str144, str145); //周五使能

                            String sakr = tc.testStringBuilder(str146, str147); //周五时
                            String salr = tc.testStringBuilder(str148, str149); //周五分
                            abm = Integer.parseInt(sakr, 16); //周五时
                            abn = Integer.parseInt(salr, 16); //周五分
//                            s43 = String.valueOf(abm); //周五时
//                          s44 = String.valueOf(abn); //周五分
//                            s44 = String.format("%02d", abn);
                            s43 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 164), tc.test(value, 165)), 16));
                            s44 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 166), tc.test(value, 167)), 16));
                            String str150 = tc.test(value, 168);  //周六使能
                            String str151 = tc.test(value, 169);  //周六使能
                            String str152 = tc.test(value, 170);  //周六时
                            String str153 = tc.test(value, 171);  //周六时
                            String str154 = tc.test(value, 172);  //周六分
                            String str155 = tc.test(value, 173);  //周六分

                            suo13 = tc.testStringBuilder(str150, str151); //周六使能

                            String samr = tc.testStringBuilder(str152, str153); //周六时
                            String sanr = tc.testStringBuilder(str154, str155); //周六分
                            abo = Integer.parseInt(samr, 16); //周六时
                            abp = Integer.parseInt(sanr, 16); //周六分
//                            s45 = String.valueOf(abo); //周六时
//                            s46 = String.valueOf(abp); //周六分
//                            s46 = String.format("%02d", abp);
                            s45 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 170), tc.test(value, 171)), 16));
                            s46 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 172), tc.test(value, 173)), 16));

                            String str156 = tc.test(value, 174);  //周天使能
                            String str157 = tc.test(value, 175);  //周天使能
                            String str158 = tc.test(value, 176);  //周天时
                            String str159 = tc.test(value, 177);  //周天时
                            String str160 = tc.test(value, 178);  //周天分
                            String str161 = tc.test(value, 179);  //周天分

                            suo14 = tc.testStringBuilder(str156, str157); //周天使能

                            String saor = tc.testStringBuilder(str158, str159); //周天时
                            String sapr = tc.testStringBuilder(str160, str161); //周天分
                            abq = Integer.parseInt(saor, 16); //周天时
                            abr = Integer.parseInt(sapr, 16); //周天分
//                            s47 = String.valueOf(abq); //周天时
//                            s48 = String.valueOf(abr); //周天分
//                            s48 = String.format("%02d", abr);
                            s47 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 176), tc.test(value, 177)), 16));
                            s48 = String.format("%02d", Integer.parseInt(tc.testStringBuilder(tc.test(value, 178), tc.test(value, 179)), 16));

                            String str168 = tc.test(value, 186);  //调试中间状态1
                            String str169 = tc.test(value, 187);  //调试中间状态1
                            String str170 = tc.test(value, 188);  //调试中间状态2
                            String str171 = tc.test(value, 189);  //调试中间状态2
                            String str172 = tc.test(value, 190);  //调试中间状态3
                            String str173 = tc.test(value, 191);  //调试中间状态3
                            String str174 = tc.test(value, 192);  //调试中间状态4
                            String str175 = tc.test(value, 193);  //调试中间状态4
                            String str176 = tc.test(value, 194);  //调试中间状态5
                            String str177 = tc.test(value, 195);  //调试中间状态5
                            String str178 = tc.test(value, 196);  //调试中间状态6
                            String str179 = tc.test(value, 197);  //调试中间状态6
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

                            String saqr = tc.testStringBuilder(str184, str185); //夜间冲洗时间间隔
                            abs = Integer.parseInt(saqr, 16); //夜间冲洗时间间隔
//                            s49 = String.valueOf(abs); //夜间冲洗时间间隔
//                            s49 = String.format("%02d",abs);
                            s49 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 202), tc.test(value, 203)), 16));

                            String sarr = tc.testStringBuilder(str186, str187); //夜间冲洗时间设置
                            abt = Integer.parseInt(sarr, 16); //夜间冲洗时间设置
//                            s50 = String.valueOf(abt); //夜间冲洗时间设置
//                            s49 = String.format("%02d",abs);
                            s50 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 204), tc.test(value, 205)), 16));

                            String sasr = tc.testStringBuilder(str188, str189); //消毒循环时间设置
                            abu = Integer.parseInt(sasr, 16); //消毒循环时间设置
//                            s51 = String.valueOf(abu); //消毒循环时间设置
                            s51 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 206), tc.test(value, 207)), 16));
                            String satr = tc.testStringBuilder(str190, str191); //消毒浸泡时间设置
                            abv = Integer.parseInt(satr, 16); //消毒浸泡时间设置
//                            s52 = String.valueOf(abv); //消毒浸泡时间设置
                            s52 = String.valueOf(Integer.parseInt(tc.testStringBuilder(tc.test(value, 208), tc.test(value, 209)), 16));
                            String saur = tc.testStringBuilder(str192, str193); //消毒冲洗时间设置
                            abw = Integer.parseInt(saur, 16); //消毒冲洗时间设置
//                            s53 = String.valueOf(abw); //消毒冲洗时间设置
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

                            String savr = tc.testStringBuilder1(str200, str201, str202, str203); // 耗材使用时长
//
                            abx = Integer.parseInt(savr, 16);  // 耗材使用时长
//                            s54 = String.valueOf(abx); // 耗材使用时长
                            s54 = String.valueOf(Integer.parseInt(tc.testStringBuilder1(tc.test(value, 218), tc.test(value, 219), tc.test(value, 220), tc.test(value, 221)), 16));
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

//                            count++;
                            //gcc_end
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onReqFailed(String errorMsg) {

                Toast.makeText(RTDActivity.this, "请求数据失败55555！" + errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
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

//        jflag = sp.getBoolean("gflag", Boolean.parseBoolean(""));
//        fflag = sp.getBoolean("cflag", Boolean.parseBoolean(""));
//        hflag = sp.getBoolean("zflag", Boolean.parseBoolean(""));
//        if (isFlag1) {
        switch (v.getId()) {

            case R.id.btn_xg_devtime:
                if (isFlag1) {
                    startActivity(new Intent(this, SystimeActivity.class));
                } else {
                    Toast.makeText(this, "请先获取授权码", Toast.LENGTH_SHORT).show();
                }
                break;

//                case R.id.btn_ysdd:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0130");
//                        bysdd.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        bysdd.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        bysdd.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        bysdd.setText("更新");
//                    }
//                    break;
//                case R.id.btn_yjnsdd:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0131");
//                        byjnsdd.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        byjnsdd.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        byjnsdd.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        byjnsdd.setText("更新");
//                    }
//                    break;
//                case R.id.btn_yjcsdd:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0132");
//                        byjcsdd.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        byjcsdd.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        byjcsdd.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        byjcsdd.setText("更新");
//                    }
//                    break;
//                case R.id.btn_ejcsdd:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0133");
//                        bejcsdd.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        bejcsdd.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        bejcsdd.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        bejcsdd.setText("更新");
//                    }
//                    break;
//                case R.id.btn_yjcsll:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0134");
//                        byjcsll.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        byjcsll.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        byjcsll.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        byjcsll.setText("更新");
//                    }
//                    break;
//                case R.id.btn_ejcsll:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0135");
//                        bejcsll.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        bejcsll.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        bejcsll.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        bejcsll.setText("更新");
//                    }
//                    break;
//                case R.id.btn_yjfsll:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0136");
//                        byjfsll.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        byjfsll.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        byjfsll.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        byjfsll.setText("更新");
//                    }
//                    break;
//                case R.id.btn_zs:        //制水
//                    count = 0;
////                    if (gflag) {
//                    if (fflag || hflag)
//
//                    {
//                        bzs.setEnabled(false);
//                    } else
//
//                    {
//                        if (state) {
//                            state = false;
//                            dmrbell("0001", "0140");
//                            bzs.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                            bzs.setText("运行中");
//                            bcx.setEnabled(false);
//                            bxd.setEnabled(false);
//                            bxd1.setEnabled(false);
////                        bfc.setEnabled(false);
//                        } else {
//
//                            state = true;
//                            dmrbell("0000", "0140");
//                            bzs.setBackgroundResource(R.drawable.btn_bg_round_click);
//                            bzs.setText("停止");
//                            bcx.setEnabled(true);
//                            bxd.setEnabled(true);
//                            bxd1.setEnabled(true);
////                        bfc.setEnabled(true);
//                        }
//                    }
//                    break;
//
//                case R.id.btn_xd:     //z
//                    count = 0;
////                    if (!gflag || !zflag) {
//                    if (jflag || hflag)
//
//                    {
//                        bxd.setEnabled(false);
//                    } else
//
//                    {
//                        if (state) {
//                            state = false;
//                            dmrbell("0001", "0141");
//                            bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                            bxd.setText("运行中");
//                            flag1 = true;
//                            bxd1.setEnabled(true);
//                            bcx.setEnabled(false);
//                            bzs.setEnabled(false);
//
//                        } else {
//                            state = true;
//                            flag1 = false;
//                            dmrbell("0000", "0141");
//                            bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
//                            bxd1.setBackgroundResource(R.drawable.btn_bg_round_click);
//                            bxd.setText("停止");
//                            bxd1.setText("停止");
//                            bzs.setEnabled(true);
//                            bcx.setEnabled(true);
//                            bxd1.setEnabled(false);
//                        }
//                    }
//                    break;
//                case R.id.btn_xd1:
//                    count = 0;
//                    if (flag1 || fflag)
//
//                    {
//                        if (state) {
//                            state = false;
//                            dmrbell("0001", "0142");
//                            bxd1.setBackgroundResource(R.drawable.btn_bg_round_click2);
//
//                            bxd1.setText("运行中");
//                            bcx.setEnabled(false);
//                            bzs.setEnabled(false);
//
//                        } else {
//                            state = true;
//                            dmrbell("0000", "0142");
//                            bxd1.setBackgroundResource(R.drawable.btn_bg_round_click);
//                            bxd1.setText("停止");
//                            bzs.setEnabled(true);
//                            bcx.setEnabled(true);
////                            isStop = true;
//                        }
//                    }
//                    break;
//                case R.id.btn_cx:    //c
//                    count = 0;
////                    if (!gflag || !cflag) {
//
//                    if (jflag || fflag)
//
//                    {
//                        bcx.setEnabled(false);
//                    } else
//
//                    {
//                        if (state) {
//                            state = false;
//                            dmrbell("0001", "0143");
//                            bcx.setBackgroundResource(R.drawable.btn_bg_round_click2);
//
//                            bcx.setText("运行中");
//                            bzs.setEnabled(false);
//                            bxd.setEnabled(false);
//                            bxd1.setEnabled(false);
//
//                        } else {
//                            state = true;
//                            dmrbell("0000", "0143");
//                            bcx.setBackgroundResource(R.drawable.btn_bg_round_click);
//                            bcx.setText("停止");
//                            bzs.setEnabled(true);
//                            bxd.setEnabled(true);
//                            bxd1.setEnabled(true);
//                        }
//                    }
//                    break;
//                case R.id.btn_sjfs:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("0000", "0144");
//                        bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        bsjfs.setText("运行中");
//                    } else
//
//                    {
//                        state = true;
//                        bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        bsjfs.setText("停止");
//                    }
//                    break;
//                case R.id.btn_yjfs:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("0001", "0144");
//                        byjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        byjfs.setText("运行中");
//                    } else
//
//                    {
//                        state = true;
//                        byjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        byjfs.setText("停止");
//                    }
//                    break;
//                case R.id.btn_ejfs:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("0002", "0144");
//                        bejfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        bejfs.setText("运行中");
//                    } else
//
//                    {
//                        state = true;
//                        bejfs.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        bejfs.setText("停止");
//                    }
//                    break;
//                case R.id.btn_yjms:
//                    count = 0;
//                    if (state)
//
//                    {
//                        byjms.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        dmrbell("0001", "0145");
//                        byjms.setText("运行中");
//                        state = false;
//                    } else
//
//                    {
//                        state = true;
//                        byjms.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        byjms.setText("停止");
//                    }
//                    break;
//                case R.id.btn_yjjg:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0146");
//                        byjjg.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        byjjg.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        byjjg.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        byjjg.setText("更新");
//                    }
//                    break;
//                case R.id.btn_yjsz:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0147");
//                        byjsz.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        byjsz.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        byjsz.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        byjsz.setText("更新");
//                    }
//                    break;
//                case R.id.btn_xdxh:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0148");
//                        bxdxh.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        bxdxh.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        bxdxh.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        bxdxh.setText("更新");
//                    }
//                    break;
//                case R.id.btn_xdjp:
//                    count = 0;
//                    if (state) {
//                        state = false;
//                        dmrbell("03E8", "0149");
//                        bxdjp.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        bxdjp.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        bxdjp.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        bxdjp.setText("更新");
//                    }
//                    break;
//                case R.id.btn_xdcx:
//                    count = 0;
//                    if (state)
//
//                    {
//                        state = false;
//                        dmrbell("03E8", "0150");
//                        bxdcx.setBackgroundResource(R.drawable.btn_bg_round_click2);
//                        bxdcx.setText("更新");
//                    } else
//
//                    {
//                        state = true;
//                        bxdcx.setBackgroundResource(R.drawable.btn_bg_round_click);
//                        bxdcx.setText("更新");
//                    }
//                    break;
            case R.id.btn_py:
                if (state) {
                    state = false;
                    dmrbell("0001", "0140");
                    bpy.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bpy.setText("运行中");
                    bpkjbt.setEnabled(false);
                    bjb.setEnabled(false);
                    bgz.setEnabled(false);
                    bxy.setEnabled(false);
                } else {
                    state = true;
                    dmrbell("0000", "0140");
                    bpy.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bpy.setText("停止");
                    bpkjbt.setEnabled(true);
                    bjb.setEnabled(true);
                    bgz.setEnabled(true);
                    bxy.setEnabled(true);
                }
                break;
            case R.id.btn_jb:
                if (state) {
                    state = false;
                    dmrbell("0001", "0141");
                    bjb.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bjb.setText("运行中");
                    bpkjbt.setEnabled(false);
                    bpy.setEnabled(false);
                    bgz.setEnabled(false);
                    bxy.setEnabled(false);
                } else {
                    state = true;
                    dmrbell("0000", "0141");
                    bjb.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bjb.setText("停止");
                    bpy.setEnabled(true);
                    bpkjbt.setEnabled(true);
                    bgz.setEnabled(true);
                    bxy.setEnabled(true);
                }
                break;
            case R.id.btn_gz:
                if (state) {
                    state = false;
                    dmrbell("0001", "0142");
                    bgz.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bgz.setText("运行中");
                    bpkjbt.setEnabled(false);
                    bjb.setEnabled(false);
                    bpy.setEnabled(false);
                    bxy.setEnabled(false);
                } else {
                    state = true;
                    dmrbell("0000", "0142");
                    bgz.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bgz.setText("停止");
                    bpy.setEnabled(true);
                    bjb.setEnabled(true);
                    bpkjbt.setEnabled(true);
                    bxy.setEnabled(true);
                }
                break;
            case R.id.btn_pkjbt:
                if (state) {
                    state = false;
                    dmrbell("0001", "0143");
                    bpkjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bpkjbt.setText("运行中");
                    bpy.setEnabled(false);
                    bjb.setEnabled(false);
                    bgz.setEnabled(false);
                    bxy.setEnabled(false);
                } else {
                    state = true;
                    dmrbell("0000", "0143");
                    bpkjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bpkjbt.setText("停止");
                    bpy.setEnabled(true);
                    bjb.setEnabled(true);
                    bgz.setEnabled(true);
                    bxy.setEnabled(true);
                }
                break;
            case R.id.btn_xy:
                if (state) {
                    state = false;
                    dmrbell("0001", "0104");
                    bxy.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bxy.setText("运行中");
                } else {
                    state = true;
                    dmrbell("0000", "0104");
                    bxy.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bxy.setText("停止");
                }
                break;

            case R.id.btn_hg:
                if (state) {
                    state = false;
                    dmrbell("0001", "0105");
                    bhg.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bhg.setText("运行中");
                } else {
                    state = true;
                    dmrbell("0000", "0105");
                    bhg.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bhg.setText("停止");
                }
                break;
            case R.id.btn_gybzt:
                if (state) {
                    state = false;
                    dmrbell("0001", "0106");
                    bgybzt.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bgybzt.setText("运行中");
                } else {
                    state = true;
                    dmrbell("0000", "0106");
                    bgybzt.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bgybzt.setText("停止");
                }
                break;
            case R.id.btn_gftlx:
                if (state) {
                    state = false;
                    dmrbell("0001", "0107");
                    bgftlx.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bgftlx.setText("B");
                } else {
                    state = true;
                    dmrbell("0000", "0107");
                    bgftlx.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bgftlx.setText("A");
                }
                break;
            case R.id.btn_pk:
                if (state) {
                    state = false;
                    dmrbell("0001", "0108");
                    bpk.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bpk.setText("运行中");
                } else {
                    state = true;
                    dmrbell("0000", "0108");
                    bpk.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bpk.setText("停止");
                }
                break;
            case R.id.btn_qx:
                if (state) {
                    state = false;
                    dmrbell("0001", "0109");
                    bqx.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bqx.setText("运行中");
                } else {
                    state = true;
                    dmrbell("0000", "0109");
                    bqx.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bqx.setText("停止");
                }
                break;
            case R.id.btn_xd:
                if (state) {
                    bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    dmrbell("0001", "010a");
                    bxd.setText("运行中");
                    state = false;
                } else {
                    state = true;
                    dmrbell("0000", "010a");
                    bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bxd.setText("停止");
                }
                break;
            case R.id.btn_qxjbt:
                if (state) {
                    state = false;
                    dmrbell("0001", "010b");
                    bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bqxjbt.setText("运行中");
                } else {
                    state = true;
                    dmrbell("0000", "010b");
                    bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bqxjbt.setText("停止");
                }
                break;
            case R.id.btn_pkcyt:
                if (state) {
                    state = false;
                    dmrbell("0001", "010c");
                    bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click2);
                    bpkcyt.setText("运行中");
                } else {
                    state = true;
                    dmrbell("0000", "010c");
                    bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click);
                    bpkcyt.setText("停止");
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
//        String strd1 = sp.getString("s14", ""); //原水电导
//        String strd2 = sp.getString("s15", ""); //一级浓水电导
//        String strd3 = sp.getString("s16", ""); //一级纯水电导
//        String strd4 = sp.getString("s17", ""); //二级纯水电导
//        String strd5 = sp.getString("s18", ""); //一级纯水流量
//        String strd6 = sp.getString("s19", ""); //二级纯水流量
//        String strd7 = sp.getString("s20", ""); //一级废水流量
//
//        eysdd.setText(strd1);
//        eyjnsdd.setText(strd2);
//        eyjcsdd.setText(strd3);
//        eejcsdd.setText(strd4);
//        eyjcsll.setText(strd5);
//        eejcsll.setText(strd6);
//        eyjfsll.setText(strd7);

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
//        String str11 = sp.getString("s7", ""); //设备工作模式
//
//        if (str11.equals("0")) {
//            bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
//            bsjfs.setText("运行中");
//        } else {
//            bsjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bsjfs.setText("停止");
//        }
//        if (str11.equals("1")) {
//            byjfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
//            byjfs.setText("运行中");
//        } else {
//            byjfs.setBackgroundResource(R.drawable.btn_bg_round_click);
//            byjfs.setText("停止");
//        }
//        if (str11.equals("2")) {
//            bejfs.setBackgroundResource(R.drawable.btn_bg_round_click2);
//            bejfs.setText("运行中");
//        } else {
//            bejfs.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bejfs.setText("停止");
//        }


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
            bpy.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bpy.setText("运行中");
            bpkjbt.setEnabled(false);
            bjb.setEnabled(false);
            bgz.setEnabled(false);
            bxy.setEnabled(false);

        } else {
            bpy.setBackgroundResource(R.drawable.btn_bg_round_click);
            bpy.setText("停止");
        }

        if (ns11.equals("1")) {
            bpkjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bpy.setEnabled(false);
            bjb.setEnabled(false);
            bgz.setEnabled(false);
            bxy.setEnabled(false);
            bpkjbt.setText("运行中");
        } else {
            bpkjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
            bpkjbt.setText("停止");
        }

        if (ns13.equals("1")) {
            bjb.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bpkjbt.setEnabled(false);
            bpy.setEnabled(false);
            bgz.setEnabled(false);
            bxy.setEnabled(false);
            bjb.setText("运行中");
        } else {
            bjb.setBackgroundResource(R.drawable.btn_bg_round_click);
            bjb.setText("停止");
        }

        if (ns12.equals("1")) {
            bxy.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bpkjbt.setEnabled(false);
            bjb.setEnabled(false);
            bgz.setEnabled(false);
            bpy.setEnabled(false);
            bxy.setText("运行中");
        } else {
            bxy.setBackgroundResource(R.drawable.btn_bg_round_click);
            bxy.setText("停止");
        }

        if (ns10.equals("1")) {
            bhg.setBackgroundResource(R.drawable.btn_bg_round_click2);
        } else {
            bhg.setBackgroundResource(R.drawable.btn_bg_round_click);
        }

        if (ns9.equals("1")) {
            bgybzt.setBackgroundResource(R.drawable.btn_bg_round_click2);
        } else {
            bgybzt.setBackgroundResource(R.drawable.btn_bg_round_click);
        }

        if (ns8.equals("1")) {
            bgftlx.setBackgroundResource(R.drawable.btn_bg_round_click2);
        } else {
            bgftlx.setBackgroundResource(R.drawable.btn_bg_round_click);
        }

        if (ns7.equals("1")) {
            bpk.setBackgroundResource(R.drawable.btn_bg_round_click2);
        } else {
            bpk.setBackgroundResource(R.drawable.btn_bg_round_click);
        }

        if (ns6.equals("1")) {
            bqx.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bpkjbt.setEnabled(false);
            bjb.setEnabled(false);
            bgz.setEnabled(false);
            bpy.setEnabled(false);
            bqx.setText("运行中");
        } else {
            bqx.setBackgroundResource(R.drawable.btn_bg_round_click);
            bqx.setText("停止");
        }

        if (ns5.equals("1")) {
            bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bpkjbt.setEnabled(false);
            bjb.setEnabled(false);
            bgz.setEnabled(false);
            bpy.setEnabled(false);
            bxd.setText("运行中");
        } else {
            bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
            bxd.setText("停止");
        }

        if (ns4.equals("1")) {
            bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bpkjbt.setEnabled(false);
            bjb.setEnabled(false);
            bgz.setEnabled(false);
            bpy.setEnabled(false);
            bqxjbt.setText("运行中");
        } else {
            bqxjbt.setBackgroundResource(R.drawable.btn_bg_round_click);
            bqxjbt.setText("停止");
        }

        if (ns3.equals("1")) {
            bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click2);
            bpkjbt.setEnabled(false);
            bjb.setEnabled(false);
            bgz.setEnabled(false);
            bpy.setEnabled(false);
            bpkcyt.setText("运行中");
        } else {
            bpkcyt.setBackgroundResource(R.drawable.btn_bg_round_click);
            bpkcyt.setText("停止");
        }
//        if (ns14.equals("1")) {
//            bzs.setBackgroundResource(R.drawable.btn_bg_round_click2);
//            bzs.setText("运行中");
//            gflag = true;
//
//        } else {
//            bzs.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bzs.setText("停止");
//
////            jby = 0;
//            gflag = false;
//            bzs.setEnabled(false);
//            bcx.setEnabled(true);
//            bxd.setEnabled(true);
//            bxd1.setEnabled(true);
//        }
//
//        if (ns13.equals("1")) {
//            bcx.setBackgroundResource(R.drawable.btn_bg_round_click2);
//            bcx.setText("运行中");
////            jby = 1;
//            zflag = true;
//            bcx.setEnabled(true);
//            bzs.setEnabled(false);
//            bxd.setEnabled(false);
//            bxd1.setEnabled(false);
////            bfc.setEnabled(false);
//
//        } else {
//            bcx.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bcx.setText("停止");
////            jby = 0;
//            zflag = false;
//            bcx.setEnabled(false);
//            bzs.setEnabled(true);
//            bxd.setEnabled(true);
//            bxd1.setEnabled(true);
//        }
//
//        if (ns12.equals("1")) {
//            bxd.setBackgroundResource(R.drawable.btn_bg_round_click2);
//            bxd.setText("运行中");
////            jby = 1;
//            cflag = true;
//            bcx.setEnabled(false);
//            bzs.setEnabled(false);
//            bxd1.setEnabled(true);
////            bfc.setEnabled(false);
//
//        } else {
//            bxd.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bxd.setText("停止");
//            bxd1.setBackgroundResource(R.drawable.btn_bg_round_click);
//            bxd1.setText("停止");
//            jby = 0;
//            cflag = false;
//            bcx.setEnabled(true);
//            bzs.setEnabled(true);
//            bxd1.setEnabled(false);
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

        if (state1) {
            if (bj14.equals("1")) {
                ystr1 = "与 PLC 通讯故障";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                mn.start();
            }
        }
        if (state1) {
            if (bj13.equals("1")) {
                ystr2 = "平衡器水位过低";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                mn.start();
            }
        }
        if (state1) {
            if (bj12.equals("1")) {
                ystr3 = "  高压泵 1 出口压力过高";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                mn.start();
            }
        }
        if (state1) {
            if (bj11.equals("1")) {
                ystr4 = " 高压泵 2 出口压力过低";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                mn.start();
            }
        }
        if (state1) {
            if (bj10.equals("1")) {
                ystr5 = "高压泵 2 出口压力过高";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
            }
        }
        if (state1) {
            if (bj9.equals("1")) {
                ystr6 = "反冲洗动作";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                mn.start();
            }
        }
        if (state1) {
            if (bj8.equals("1")) {
                ystr7 = " 原水进水下限";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                mn.start();
            }
        }
        if (state1) {
            if (bj7.equals("1")) {
                ystr8 = " 二级产水电导异常";
                bbjzt.setText("有报警");
                bbjzt.setBackgroundResource(R.drawable.btn_bg_round_click3);
                mn.start();
            }
        }

        if (state1) {
            if (bj14.equals("0") & bj13.equals("0") & bj12.equals("0") & bj11.equals("0") & bj10.equals("0") & bj9.equals("0") & bj8.equals("0") & bj7.equals("0")) {
                state1 = false;
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


        //数据
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
//        data_list.add(ystr1);
//        data_list.add(ystr2);
//        data_list.add(ystr3);
//        data_list.add(ystr4);
//        data_list.add(ystr5);
//        data_list.add(ystr6);
//        data_list.add(ystr7);
//        data_list.add(ystr8);

        arr_adapter = new ArrayAdapter<String>(RTDActivity.this, android.R.layout.simple_spinner_item, data_list);
//        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器
        spinner.setAdapter(arr_adapter);

        //gcc_add
//        String stry1 = sp.getString("s49", ""); //夜间冲洗时间间隔
//        String stry2 = sp.getString("s50", ""); //夜间冲洗时间设置
//        String stry3 = sp.getString("s51", ""); //消毒循环时间设置
//        String stry4 = sp.getString("s52", ""); //消毒浸泡时间设置
//        String stry5 = sp.getString("s53", ""); //消毒冲洗时间设置

//        eyjjg.setText(stry1);
//        eyjsz.setText(stry2);
//        exdxh.setText(stry3);
//        exdjp.setText(stry4);
//        exdcx.setText(stry5);

        Log.e("大菠萝", "显示心跳包数据完成-6666666666666666666666666666---------》");
    }

    //通用接口
    public void dmrbell(String content, String startadd) {

        ZhiShui zhiShui1 = new ZhiShui();
        zhiShui1.setCommond("0110"); //固定指令，当前远程控制0110
        zhiShui1.setContent(content); //远程控制内容
        zhiShui1.setDate(String.valueOf(new Date().getTime()));
        zhiShui1.setDeviceNum(lks); // 设备号
        zhiShui1.setEtypecode(ConsTant.ETYPECODE_JZGY); //设备类型
        zhiShui1.setIemi(imei);
        zhiShui1.setNum("0001"); //修改寄存器数量
        zhiShui1.setNumLen("02"); //修改字节长度
        zhiShui1.setPhone(phonenumber);
        zhiShui1.setStartAdd(startadd); //远程修改寄存器起始地址
        zhiShui1.setUid(uid);
        String json = JSON.toJSONString(zhiShui1);

        Log.e(TAG, "M416+AWM——————————》  " + json);
        OkHttpUtils3 okHttpUtils = new OkHttpUtils3(this);
        okHttpUtils.post(WebUrls.getcustomurl, json);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mn.stop();
        mTimer.cancel();//取消任务
        handler.removeCallbacks(mTimerTask);//取消任务
    }

}
